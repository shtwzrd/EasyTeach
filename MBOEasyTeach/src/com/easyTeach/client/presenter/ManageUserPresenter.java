package com.easyTeach.client.presenter;

import java.util.UUID;

import javax.swing.JOptionPane;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.easyTeach.client.network.EasyTeachClient;
import com.easyTeach.client.ui.AdminManagerUI;
import com.easyTeach.client.ui.MainFrame;
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
 * The ManageUserPresenter communicates with the domain logic on behalf of the
 * ManageUserUI, providing all of its logic.
 * <p>
 * The Listeners in the UI class call the relevant methods in the Presenter in
 * order to update and retrieve information from the domain logic. This is an
 * implementation of the Model View Presenter pattern.
 * </p>
 * 
 * @author Tonni Hyldgaard, Oliver Nielsen
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

	private String[] tableColumnHeaders = { "Class", "Year" };

	private User editUser;

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
		this.editUser = selectedUser;

		this.classesEnrolledSet = new ResourceSet();
		this.classSelectionSet = new ResourceSet();

		this.enrolledClasses = new UserTableModel(this.tableColumnHeaders,
				this.classesEnrolledSet);

		this.allClasses = new UserTableModel(this.tableColumnHeaders,
				this.classesEnrolledSet);

		refreshClassTable();
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

		Action toDo = new Action(ActionType.READ, "all");
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

		if (this.editUser != null) {
			Action readRelations = new Action(ActionType.READ, "classes");
			Request getRelations = new Request(Session.getInstance(),
					readRelations, this.editUser);

			this.client = new EasyTeachClient(getRelations);
			this.client.run();
			this.client.getResponse();

			if (this.client.getResponse().getStatus() != ResponseStatus.FAILURE) {
				this.classesEnrolledSet = (ResourceSet) this.client
						.getResponse().getResponse();
			}
			this.enrolledClasses.refreshData(this.classesEnrolledSet);
			this.enrolledClasses.fireTableDataChanged();

		}
		this.allClasses.refreshData(this.classSelectionSet);

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
		if (!userFirstName.equals("") && !userLastName.equals("")
				&& email.contains("@")) {

			// Make the user...
			String userNo;
			if (this.editUser == null) {
				userNo = UUID.randomUUID().toString();
			} else {
				userNo = this.editUser.getUserNo();
			}
			String password = pEncrypt.encryptPassword(UUID.randomUUID()
					.toString().substring(0, 6));
			this.user = new User(userNo, email, userType, userFirstName,
					userLastName, password, getCurrentDate());

			// Send it to the Server
			Action toDo;

			if (this.editUser != null) {
				toDo = new Action(ActionType.UPDATE);
			} else {
				toDo = new Action(ActionType.CREATE);
			}

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
			toDo = new Action(ActionType.UPDATE, "class");
			out = new Request(Session.getInstance(), toDo, this.relations);
			this.client = new EasyTeachClient(out);
			this.client.run();
			in = this.client.getResponse();
			System.out.println(in.getStatus() + ": " + in.getResponseMessage());

			if (in.getStatus() == ResponseStatus.SUCCESS) {
				JOptionPane.showMessageDialog(null, "Saved succesfully!");
				MainFrame.updateFrame(new AdminManagerUI().getAdminManagerUI(),
						"Admin Manager");
			} else {
				JOptionPane.showMessageDialog(null,
						"Something went wrong. Try again!");
			}
		} else {
			if (userFirstName.equals("")) {
				JOptionPane.showMessageDialog(null, "Invalid first name!");
			} else if (userLastName.equals("")) {
				JOptionPane.showMessageDialog(null, "Invalid last name!");
			} else if (!email.contains("@")) {
				JOptionPane.showMessageDialog(null, "Invalid email!");
			}
		}

	}

	/**
	 * The method getCurrentDate returns the current day in which a user is
	 * created. This is done by parsing a {@link java.util.Date} as an
	 * {@link java.sql.Date}
	 * 
	 * @return the the date in which the user was created.
	 */
	private static java.sql.Date getCurrentDate() {
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
		if (this.currentlySelectedClassInSelection != null) {
			this.classesEnrolledSet.add(this.currentlySelectedClassInSelection);
			this.enrolledClasses.refreshData(this.classesEnrolledSet);
			this.enrolledClasses.fireTableDataChanged();
		} else {
			JOptionPane.showMessageDialog(null, "Select a class to add!");
		}
	}

	/**
	 * Removes a {@link Class} from the EnrolledTable.
	 */
	public void remove() {
		if (this.currentlySelectedClassInEnrolled != null) {
			this.classesEnrolledSet
					.remove(this.currentlySelectedClassInEnrolled);
			this.enrolledClasses.refreshData(this.classesEnrolledSet);
			this.enrolledClasses.fireTableDataChanged();
		} else {
			JOptionPane.showMessageDialog(null, "Select a class to remove!");
		}
	}

	public String getEditUserFirstName() {
		if (this.editUser != null) {
			return this.editUser.getFirstName();
		}
		return "";
	}

	public String getEditUserLastName() {
		if (this.editUser != null) {
			return this.editUser.getLastName();
		}
		return "";
	}

	public String getEditUserEmail() {
		if (this.editUser != null) {
			return this.editUser.getEmail();
		}
		return "";
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