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

	private DisplayTableModel enrolledStudents;
	private DisplayTableModel allStudents;
	private ResourceSet studentSelectionSet;
	private Class currentClass;
	private ResourceSet studentsEnrolledSet;
	private ResourceSet filteredStudentsSelection; private ResourceSet relations;
	private EasyTeachClient client;
	private User user;
	private User currentlySelectedUserInSelection;
	private User currentlySelectedUserInEnrolled;
	private boolean isFiltered;

	private String[] tableColumnHeaders = { "Email", "First Name", "Last Name",
			"Date added" };

	public ManageClassPresenter() {
		this.enrolledStudents = new DisplayTableModel();
		this.allStudents = new DisplayTableModel();

		this.allStudents.setColumnIdentifiers(this.tableColumnHeaders);

		this.enrolledStudents.setColumnIdentifiers(this.tableColumnHeaders);

		refreshStudentTable();
	}

	public DisplayTableModel getEnrolledStudentsModel() {
		return this.enrolledStudents;
	}

	public DisplayTableModel getAllStudentsModel() {
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

	private void refreshStudentTable() {

		Action toDo = new Action(ActionType.READ, "students");
		this.user = new User();

		Request getStudents = new Request(Session.getInstance(), toDo, this.user);
		this.client = new EasyTeachClient(getStudents);
		this.client.run();
		this.client.getResponse();
		if (this.client.getResponse().getStatus() != ResponseStatus.FAILURE) {
			this.studentSelectionSet = (ResourceSet) this.client.getResponse()
					.getResponse();
			this.filteredStudentsSelection = this.studentSelectionSet;
			if (this.isFiltered) {
				for (Resource r : this.filteredStudentsSelection) {
					User u = (User) r;
					Object[] o = new Object[4];
					o[0] = u.getEmail();
					o[1] = u.getFirstName();
					o[2] = u.getLastName();
					o[3] = u.getDateAdded();
					this.allStudents.addRow(o);
				}
			} else {
				for (Resource r : this.studentSelectionSet) {
					User u = (User) r;
					Object[] o = new Object[4];
					o[0] = u.getEmail();
					o[1] = u.getFirstName();
					o[2] = u.getLastName();
					o[3] = u.getDateAdded();
					this.allStudents.addRow(o);
				}
			}
		}
	}

	private void refreshEnrolledTable() {
		for (Resource r : this.studentsEnrolledSet) {
			User u = (User) r;
			Object[] o = new Object[4];
			o[0] = u.getEmail();
			o[1] = u.getFirstName();
			o[2] = u.getLastName();
			o[3] = u.getDateAdded();
			this.allStudents.addRow(o);
		}
	}

	public void save(String className, int classYear) {
		if (className != null && classYear != 0) {
			// Make the class...
			String classId = UUID.randomUUID().toString();
			this.currentClass = new Class(classId, classYear, className);

			// Send it to the Server
			Action toDo = new Action(ActionType.CREATE);
			Request out = new Request(Session.getInstance(), toDo, this.currentClass);
			this.client = new EasyTeachClient(out);
			this.client.run();
			Response in = this.client.getResponse();
			System.out.println(in.getStatus() + ": " + in.getResponseMessage());

			// Make all the relations...
			this.relations = new ResourceSet();
			for (Resource r : this.studentsEnrolledSet) {
				User u = (User) r;
				this.relations.add(new ClassUserRelation(classId, u.getUserNo()));
			}

			// Send those to the server
			toDo = new Action(ActionType.UPDATE, "class");
			out = new Request(Session.getInstance(), toDo, this.relations);
			this.client = new EasyTeachClient(out);
			this.client.run();
			in = this.client.getResponse();
			System.out.println(in.getStatus() + ": " + in.getResponseMessage());
		}
	}

	public void filter(String column, String by) {
		if (column == "" && by == "") {
			this.isFiltered = false;
			this.refreshStudentTable();
		} else {
			this.isFiltered = true;
			for (Resource r : this.studentSelectionSet) {
				User u = (User) r;
				switch (column) {
				case "Email":
					if (u.getEmail().equals(by)) {
						this.filteredStudentsSelection.add(u);
					}
					break;
				case "First name":
					if (u.getFirstName().equals(by)) {
						this.filteredStudentsSelection.add(u);
					}
					break;
				case "Last name":
					if (u.getFirstName().equals(by)) {
						this.filteredStudentsSelection.add(u);
					}
				}
			}

		}

	}

	public void discard() {
		this.studentSelectionSet = null;
		this.refreshEnrolledTable();
		this.refreshStudentTable();
	}

	public void add() {
		this.studentsEnrolledSet.add(this.currentlySelectedUserInSelection);
		this.refreshEnrolledTable();
	}

	public void remove() {
		this.studentsEnrolledSet.remove(this.currentlySelectedUserInEnrolled);
		this.refreshEnrolledTable();
	}

}
