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
	private ResourceSet filteredStudentsSelection;
	private ResourceSet relations;
	private EasyTeachClient client;
	private User user;
	private User currentlySelectedUserInSelection;
	private User currentlySelectedUserInEnrolled;
	private boolean isFiltered;

	private String[] tableColumnHeaders = { "Email", "First Name", "Last Name",
			"Date added" };

	public ManageClassPresenter() {
		enrolledStudents = new DisplayTableModel();
		allStudents = new DisplayTableModel();

		allStudents.setColumnIdentifiers(this.tableColumnHeaders);

		enrolledStudents.setColumnIdentifiers(this.tableColumnHeaders);

		refreshStudentTable();
	}

	public DisplayTableModel getEnrolledStudentsModel() {
		return enrolledStudents;
	}

	public DisplayTableModel getAllStudentsModel() {
		return allStudents;
	}

	public void setSelectedUserInEnrolled(Object email) {
		for (Resource r : studentsEnrolledSet) {
			User u = (User) r;
			if (u.getEmail().equals(email)) {
				currentlySelectedUserInEnrolled = u;
			}
		}
	}

	public void setSelectedUserInSelection(Object email) {
		for (Resource r : studentsEnrolledSet) {
			User u = (User) r;
			if (u.getEmail().equals(email)) {
				currentlySelectedUserInSelection = u;
			}
		}
	}

	private void refreshStudentTable() {

		Action toDo = new Action(ActionType.READ, "students");
		user = new User();

		Request getStudents = new Request(Session.getInstance(), toDo, user);
		client = new EasyTeachClient(getStudents);
		client.run();
		client.getResponse();
		if (client.getResponse().getStatus() != ResponseStatus.FAILURE) {
			studentSelectionSet = (ResourceSet) client.getResponse()
					.getResponse();
			filteredStudentsSelection = studentSelectionSet;
			if (isFiltered) {
				for (Resource r : filteredStudentsSelection) {
					User u = (User) r;
					Object[] o = new Object[4];
					o[0] = u.getEmail();
					o[1] = u.getFirstName();
					o[2] = u.getLastName();
					o[3] = u.getDateAdded();
					allStudents.addRow(o);
				}
			} else {
				for (Resource r : studentSelectionSet) {
					User u = (User) r;
					Object[] o = new Object[4];
					o[0] = u.getEmail();
					o[1] = u.getFirstName();
					o[2] = u.getLastName();
					o[3] = u.getDateAdded();
					allStudents.addRow(o);
				}
			}
		}
	}

	private void refreshEnrolledTable() {
		for (Resource r : studentsEnrolledSet) {
			User u = (User) r;
			Object[] o = new Object[4];
			o[0] = u.getEmail();
			o[1] = u.getFirstName();
			o[2] = u.getLastName();
			o[3] = u.getDateAdded();
			allStudents.addRow(o);
		}
	}

	public void save(String className, int classYear) {
		if (className != null && classYear != 0) {
			// Make the class...
			String classId = UUID.randomUUID().toString();
			currentClass = new Class(classId, classYear, className);

			// Send it to the Server
			Action toDo = new Action(ActionType.CREATE);
			Request out = new Request(Session.getInstance(), toDo, currentClass);
			client = new EasyTeachClient(out);
			client.run();
			Response in = client.getResponse();
			System.out.println(in.getStatus() + ": " + in.getResponseMessage());

			// Make all the relations...
			relations = new ResourceSet();
			for (Resource r : this.studentsEnrolledSet) {
				User u = (User) r;
				relations.add(new ClassUserRelation(classId, u.getUserNo()));
			}

			// Send those to the server
			toDo = new Action(ActionType.UPDATE, "class");
			out = new Request(Session.getInstance(), toDo, relations);
			client = new EasyTeachClient(out);
			client.run();
			in = client.getResponse();
			System.out.println(in.getStatus() + ": " + in.getResponseMessage());
		}
	}

	public void filter(String column, String by) {
		if (column == "" && by == "") {
			isFiltered = false;
			this.refreshStudentTable();
		} else {
			isFiltered = true;
			for (Resource r : studentSelectionSet) {
				User u = (User) r;
				switch (column) {
				case "Email":
					if (u.getEmail().equals(by)) {
						filteredStudentsSelection.add(u);
					}
					break;
				case "First name":
					if (u.getFirstName().equals(by)) {
						filteredStudentsSelection.add(u);
					}
					break;
				case "Last name":
					if (u.getFirstName().equals(by)) {
						filteredStudentsSelection.add(u);
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
