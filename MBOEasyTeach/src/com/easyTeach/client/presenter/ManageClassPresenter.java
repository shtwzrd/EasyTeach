package com.easyTeach.client.presenter;

import java.util.UUID;

import com.easyTeach.client.network.EasyTeachClient;
import com.easyTeach.common.entity.Class;
import com.easyTeach.common.entity.ClassUserRelation;
import com.easyTeach.common.entity.Resource;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.entity.User;
import com.easyTeach.common.network.Action;
import com.easyTeach.common.network.Action.ActionType;
import com.easyTeach.common.network.Request;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.common.network.Session;

/**
 * The ManageClassPresenter communicates with the domain logic on behalf of the
 * ManageClassUI, providing all of its logic.
 * <p>
 * The Listeners in the UI class call the relevant methods in the Presenter in
 * order to update and retrieve information from the domain logic. This is an
 * implementation of the Model View Presenter pattern.
 * </p>
 * 
 * @author Brandon Lucas
 * @version 1.0
 * @date 12. December, 2013
 */
public class ManageClassPresenter {

	private ClassTableModel enrolledStudents;
	private ClassTableModel allStudents;
	protected ResourceSet studentSelectionSet;
	protected ResourceSet filteredSelectionSet;
	protected ResourceSet studentsEnrolledSet;
	private Class currentClass;
	private ResourceSet relations;
	private EasyTeachClient client;
	private User user;
	private User currentlySelectedUserInSelection;
	private User currentlySelectedUserInEnrolled;
	private boolean isFiltered;

	private String[] tableColumnHeaders = { "Email", "First Name", "Last Name",
			"Date added" };

	/**
	 * Initializes sets and table models
	 */
	public ManageClassPresenter() {
		this.studentsEnrolledSet = new ResourceSet();

		this.enrolledStudents = new ClassTableModel(this.tableColumnHeaders,
				this.studentsEnrolledSet);
		this.allStudents = new ClassTableModel(this.tableColumnHeaders,
				this.studentsEnrolledSet);

		refreshStudentTable();
	}

	public ClassTableModel getEnrolledStudentsModel() {
		return this.enrolledStudents;
	}

	public ClassTableModel getAllStudentsModel() {
		return this.allStudents;
	}

	public void setSelectedUserInEnrolled(Object email) {
		for (Resource r : this.studentsEnrolledSet) {
			User u = (User) r;
			if (u.getEmail().equals(email)) {
				this.currentlySelectedUserInEnrolled = u;
			}
		}
	}

	public void setSelectedUserInSelection(String email) {
		for (Resource r : this.studentSelectionSet) {
			User u = (User) r;
			if (u.getEmail().equals(email)) {
				this.currentlySelectedUserInSelection = u;
			}
		}
	}

	/**
	 * Sends a {@link Request} to the Server, updating the Presenter's set of
	 * available Users.
	 * 
	 * <p>
	 * It checks if the data in the table should be filtered, and updates the
	 * Table Model accordingly, before firing the dataChanged event and forcing
	 * the table in the UI to update.
	 * </p>
	 */
	private void refreshStudentTable() {

		Action toDo = new Action(ActionType.READ, "students");
		this.user = new User();

		Request getStudents = new Request(Session.getInstance(), toDo,
				this.user);
		this.client = new EasyTeachClient(getStudents);
		this.client.run();
		this.client.getResponse();
		if (this.client.getResponse().getStatus() != ResponseStatus.FAILURE) {
			this.studentSelectionSet = (ResourceSet) this.client.getResponse()
					.getResponse();
		}
		if (this.isFiltered) {
			this.allStudents.refreshData(this.filteredSelectionSet);
		} else {
			this.allStudents.refreshData(this.studentSelectionSet);
		}

		this.allStudents.fireTableDataChanged();
	}

	/**
	 * Method for committing the new class and its users to the database.
	 * 
	 * <p>
	 * This operation requires sending two separate requests to the Server.
	 * First, a CREATE is sent to make the Class itself, and then an UPDATE
	 * follows, containing the set of ClassUser relations for the newly created
	 * class.
	 * </p>
	 * 
	 * @param className
	 *            Human-readable name for the class, such as DAT13W or Biology
	 *            101
	 * @param classYear
	 *            The year for which the class is active.
	 */
	public void save(String className, int classYear) {
		if (className != null && classYear != 0) {
			// Make the class...
			String classId = UUID.randomUUID().toString();
			this.currentClass = new Class(classId, classYear, className);

			// Send it to the Server
			Action toDo = new Action(ActionType.CREATE);
			Request out = new Request(Session.getInstance(), toDo,
					this.currentClass);
			this.client = new EasyTeachClient(out);
			this.client.run();
			Response in = this.client.getResponse();
			System.out.println(in.getStatus() + ": " + in.getResponseMessage());

			// Make all the relations...
			this.relations = new ResourceSet();
			for (Resource r : this.studentsEnrolledSet) {
				User u = (User) r;
				this.relations
						.add(new ClassUserRelation(classId, u.getUserNo()));
			}

			// Send those to the server
			toDo = new Action(ActionType.UPDATE, "class");
			out = new Request(Session.getInstance(), toDo, this.relations);
			this.client = new EasyTeachClient(out);
			this.client.run();
			in = this.client.getResponse();
			System.out.println(in.getStatus() + ": " + in.getResponseMessage());

			this.allStudents.refreshData(this.studentSelectionSet);
			this.enrolledStudents.refreshData(this.studentsEnrolledSet);
		}
	}

	/**
	 * Logic for filtering the table containing the selection of students by a
	 * particular string, in a particular column.
	 * 
	 * <p>
	 * Right now, this consists of maintaining a separate, filtered set. This is
	 * somewhat slow. Later, new logic should be added, using RowFilter.
	 * </p>
	 * 
	 * @param column
	 *            The column in the table which should be considered for
	 *            filtering
	 * @param by
	 *            The string by which the aforementioned column should be
	 *            filtered.
	 */
	public void filter(String column, String by) {
		if (by.equals("")) {
			this.isFiltered = false;
		} else {
			this.isFiltered = true;
			this.filteredSelectionSet = new ResourceSet();
			switch (column) {
			case "Email":
				for (Resource r : this.studentSelectionSet) {
					User u = (User) r;
					if (u.getEmail().equals(by)) {
						this.filteredSelectionSet.add(u);
					}
				}
				break;
			case "First name":
				for (Resource r : this.studentSelectionSet) {
					User u = (User) r;
					if (u.getFirstName().equals(by)) {
						this.filteredSelectionSet.add(u);
					}
				}
				break;
			case "Last name":
				for (Resource r : this.studentSelectionSet) {
					User u = (User) r;
					if (u.getLastName().equals(by)) {
						this.filteredSelectionSet.add(u);
					}
				}
				break;
			}
		}
		this.refreshStudentTable();
	}

	/**
	 * Clear all fields without saving anything
	 */
	public void discard() {
		this.studentSelectionSet = null;
		this.refreshStudentTable();
	}

	/**
	 * Adds a User from the SelectionTable to the EnrolledTable.
	 */
	public void add() {
		this.studentsEnrolledSet.add(this.currentlySelectedUserInSelection);
		this.enrolledStudents.refreshData(this.studentsEnrolledSet);
		this.enrolledStudents.fireTableDataChanged();
	}

	/**
	 * Removes a User from the EnrolledTable.
	 */
	public void remove() {
		this.studentsEnrolledSet.remove(this.currentlySelectedUserInEnrolled);
		this.enrolledStudents.refreshData(this.studentsEnrolledSet);
		this.enrolledStudents.fireTableDataChanged();
	}

	/**
	 * 
	 * @author Brandon Lucas
	 * @version 1.0
	 * @date 12 December, 2013
	 * 
	 * Presenter-specific implementation of the abstract DisplayTableModel.
	 * 
	 * <p>
	 * Implementation of the DisplayTableModel on a per-Presenter basis allows
	 * us to interact rows in the table model as actual domain objects, specific
	 * to the demands of the particular user-interface.
	 * </p>
	 *
	 */
	private class ClassTableModel extends DisplayTableModel {
		public ClassTableModel(String[] columnHeaders, ResourceSet resources) {
			super(columnHeaders, resources);
		}

		private static final long serialVersionUID = -2996998684634631118L;

		@Override
		public String getValueAt(int row, int column) {
			User u = (User) this.tableData.get(row);
			switch (this.getColumnName(column)) {
			case "Email":
				return u.getEmail();
			case "First Name":
				return u.getFirstName();
			case "Last Name":
				return u.getLastName();
			case "Date added":
				return u.getDateAdded().toString();
			default:
				return new String();
			}
		}
	}
}
