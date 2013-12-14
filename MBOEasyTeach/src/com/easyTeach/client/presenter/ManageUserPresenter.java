package com.easyTeach.client.presenter;

import java.util.UUID;

import org.jasypt.util.password.StrongPasswordEncryptor;

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
 * The ManageUserPresenter communicates with the domain logic on behalf of
 * the ManageUserUI, providing all of its logic.
 * <p>
 * The Listeners in the UI class call the relevant methods in the Presenter
 * in order to update and retrieve information from the domain logic. This
 * is an implementation of the Model View Presenter pattern.
 * </p>
 * 
 * @author Tonni Hyldgaard
 * @version 1.0
 * @date 13. December, 2013
 */

public class ManageUserPresenter {

	private UserTableModel enrolledClasses;
	private UserTableModel allClasses;
	protected ResourceSet classSelectionSet;
	protected ResourceSet filteredSelectionSet;
	protected ResourceSet classesEnrolledSet;
	private User user;
	private ResourceSet relations;
	private EasyTeachClient client;
	private Class curClass;
	private Class currentlySelectedClassInSelection;
	private Class currentlySelectedClassInEnrolled;
	private boolean isFiltered;

	private String[] tableColumnHeaders = { "Class", "Year" };

	/**
	 * Initializes sets and table models
	 */
	public ManageUserPresenter() {
		this.classesEnrolledSet = new ResourceSet();
		this.classSelectionSet = new ResourceSet();

		this.enrolledClasses = new UserTableModel(this.tableColumnHeaders,
				this.classesEnrolledSet);

		this.allClasses = new UserTableModel(this.tableColumnHeaders,
				this.classesEnrolledSet);
		

		refreshClassTable();
	}

	public ManageUserPresenter(User selectedUser) {
		// TODO Auto-generated constructor stub
	}

	public UserTableModel getEnrolledClassesModel() {
		return this.enrolledClasses;
	}

	public UserTableModel getAllClassesModel() {
		return this.allClasses;
	}

	public void setSelectedClassInEnrolled(Object className) {
		for (Resource r : this.classesEnrolledSet) {
			Class c = (Class) r;
			if (c.getClassName().equals(className)) {
				this.currentlySelectedClassInEnrolled = c;
			}
		}
	}

	public void setSelectedClassInSelection(String className) {
		for (Resource r : this.classSelectionSet) {
			Class c = (Class) r;
			if (c.getClassName().equals(className)) {
				this.currentlySelectedClassInSelection = c;
			}
		}
	}

	/**
	 * Sends a {@link Request} to the Server, updating the Presenter's set of
	 * available {@link Class}es.
	 * 
	 * <p>
	 * It checks if the data in the table should be filtered, and updates the
	 * Table Model accordingly, before firing the dataChanged event and forcing
	 * the table in the UI to update.
	 * </p>
	 */
	private void refreshClassTable() {

		Action toDo = new Action(ActionType.READ, "classes");
		this.curClass = new Class();

		Request getClasses = new Request(Session.getInstance(), toDo,
				this.curClass);
		this.client = new EasyTeachClient(getClasses);
		this.client.run();
		this.client.getResponse();
		if (this.client.getResponse().getStatus() != ResponseStatus.FAILURE) {
			this.classSelectionSet = (ResourceSet) this.client.getResponse()
					.getResponse();
		}
//		if (this.isFiltered) {
//			this.allClasses.refreshData(this.filteredSelectionSet);
//		} 
//		else {
//			this.allClasses.refreshData(this.classSelectionSet);
//		}

		this.allClasses.fireTableDataChanged();
	}

	/**
	 * Method for committing the new User and its Class to the database.
	 * 
	 * <p>
	 * This operation requires sending two separate requests to the Server.
	 * First, a CREATE is sent to make the User itself, and then an UPDATE
	 * follows, containing the set of ClassUser relations for the newly created
	 * User.
	 * </p>
	 * 
	 * @param userName
	 *            Human-readable name for the user, such as David or Emma
	 * @param classYear
	 *            The year for which the class is active.
	 */
	public void save(String userType, String userFirstName,
			String userLastName, String email) {
		// pEncrypt creates a random generated password which is encrypted
		StrongPasswordEncryptor pEncrypt = new StrongPasswordEncryptor();
		if (userType != null && userFirstName != null && userLastName != null
				&& email != null) {
			// Make the user...
			String userNo = UUID.randomUUID().toString();
			String password = pEncrypt.encryptPassword(UUID.randomUUID()
					.toString().substring(0, 6));
			this.user = new User(userNo, email, userType, userFirstName,
					userLastName, password, getCurrentDate());

			// Send it to the Server
			Action toDo = new Action(ActionType.CREATE);
			Request out = new Request(Session.getInstance(), toDo, this.user);
			this.client = new EasyTeachClient(out);
			this.client.run();
			Response in = this.client.getResponse();
			System.out.println(in.getStatus() + ": " + in.getResponseMessage());

			// Make all the relations...
			this.relations = new ResourceSet();
			for (Resource r : this.classesEnrolledSet) {
				Class c = (Class) r;
				this.relations
						.add(new ClassUserRelation(c.getClassNo(), userNo));
			}

			// Send those to the server
			toDo = new Action(ActionType.UPDATE, "user");
			out = new Request(Session.getInstance(), toDo, this.relations);
			this.client = new EasyTeachClient(out);
			this.client.run();
			in = this.client.getResponse();
			System.out.println(in.getStatus() + ": " + in.getResponseMessage());

			this.allClasses.refreshData(this.classSelectionSet);
			this.enrolledClasses.refreshData(this.classesEnrolledSet);
		}
	}

	/**
	 * The method getCurrentDate returns the current day in which a user is
	 * created. This is done by parsing a {@link java.util.Date} as an
	 * {@link java.sql.Date}
	 * 
	 * @return the the date in which the user was created.
	 */
	private java.sql.Date getCurrentDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}

	/**
	 * Clear all fields without saving anything
	 */
	public void discard() {
		this.classSelectionSet = null;
		this.refreshClassTable();
	}

	/**
	 * Adds a {@link Class} from the SelectionTable to the EnrolledTable.
	 */
	public void add() {
		this.classesEnrolledSet.add(this.currentlySelectedClassInSelection);
		this.enrolledClasses.refreshData(this.classesEnrolledSet);
		this.enrolledClasses.fireTableDataChanged();
	}

	/**
	 * Removes a {@link Class} from the EnrolledTable.
	 */
	public void remove() {
		this.classesEnrolledSet.remove(this.currentlySelectedClassInEnrolled);
		this.enrolledClasses.refreshData(this.classesEnrolledSet);
		this.enrolledClasses.fireTableDataChanged();
	}

	/**
	 * 
	 * @author Tonni Hyldgaard
	 * @version 1.0
	 * @date 13. December, 2013
	 * 
	 *       Presenter-specific implementation of the abstract
	 *       DisplayTableModel.
	 * 
	 *       <p>
	 *       Implementation of the DisplayTableModel on a per-Presenter basis
	 *       allows us to interact rows in the table model as actual domain
	 *       objects, specific to the demands of the particular user-interface.
	 *       </p>
	 * 
	 */
	private class UserTableModel extends DisplayTableModel {
		public UserTableModel(String[] columnHeaders, ResourceSet resources) {
			super(columnHeaders, resources);
		}

		private static final long serialVersionUID = -2996998684634631118L;

		@Override
		public String getValueAt(int row, int column) {
			Class c = (Class) this.tableData.get(row);
			String year = (Integer.toString(c.getYear()));

			switch (this.getColumnName(column)) {
			case "Class":
				return c.getClassName();
			case "Year":
				return year;
			default:
				return new String();
			}
		}
	}

}