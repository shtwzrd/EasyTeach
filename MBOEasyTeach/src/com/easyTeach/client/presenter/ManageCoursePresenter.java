package com.easyTeach.client.presenter;

import java.util.UUID;

import javax.swing.JOptionPane;

import com.easyTeach.client.network.EasyTeachClient;
import com.easyTeach.client.ui.AdminManagerUI;
import com.easyTeach.client.ui.MainFrame;
import com.easyTeach.common.entity.Class;
import com.easyTeach.common.entity.ClassCourseRelation;
import com.easyTeach.common.entity.Course;
import com.easyTeach.common.entity.Resource;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.network.Action;
import com.easyTeach.common.network.Action.ActionType;
import com.easyTeach.common.network.Request;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.common.network.Session;

/**
 * TODO Create the edit button.O
 * 
 * @author Oliver Nielsen
 */
public class ManageCoursePresenter {

	final String[] tableHead = { "Class No.", "Class Name", "Year" };

	private ManageCourseModel manageAssociatedClasses;
	private ManageCourseModel manageAvailableClasses;

	// The classes that is associated with the course
	private ResourceSet associatedClassesSet;
	// All the classes that can be associated with the course
	private ResourceSet availableClassesSet;
	// The available classes sorted for the users preferences
	private ResourceSet filteredClassesSet;

	private boolean isFiltered;

	private Class currentlySelectedClassFromAssociatedTable;
	private Class currentlySelectedClassFromAvailableTable;

	public ManageCoursePresenter() {
		this.manageAssociatedClasses = new ManageCourseModel(this.associatedClassesSet);
		this.manageAvailableClasses = new ManageCourseModel(this.availableClassesSet);

		refreshAvailableClasses();
	}

	private void refreshAvailableClasses() {
		Action toDo = new Action(ActionType.READ, "all");
		Class performOnCourse = new Class();

		Request getCourses = new Request(Session.getInstance(), toDo,
				performOnCourse);
		EasyTeachClient serverCom = new EasyTeachClient(getCourses);
		serverCom.run();
		serverCom.getResponse();

		if (serverCom.getResponse().getStatus() != ResponseStatus.FAILURE) {
			// Get all the classes as a ResourceSet
			this.availableClassesSet = (ResourceSet) serverCom.getResponse()
					.getResponse();

			// Testing if the filter is on

			// If the filter is on the filteredClassesSet will put in
			// association with the manageAvailableClasses table model.
			// Otherwise the available will be put in association with the table
			// model.
			if (this.isFiltered) {
				this.manageAvailableClasses.refreshData(this.filteredClassesSet);
			} else {
				this.manageAvailableClasses.refreshData(this.availableClassesSet);
			}

			// This method call changes what is in the table model according to
			// what happened in the if/else statement a few lines above
			this.manageAvailableClasses.fireTableDataChanged();
		}
	}

	private void refreshTableModels() {
		this.manageAssociatedClasses.refreshData(this.associatedClassesSet);
		this.manageAssociatedClasses.fireTableDataChanged();

		if (this.isFiltered) {
			this.manageAvailableClasses.refreshData(this.filteredClassesSet);
		} else {
			this.manageAvailableClasses.refreshData(this.availableClassesSet);
		}
		this.manageAvailableClasses.fireTableDataChanged();
	}

	public void saveCourse(String courseName) {
		// Create the course
		String courseNo = UUID.randomUUID().toString();
		Course newCourse = new Course(courseNo, courseName);

		// Sending the new course to the server
		Action toDo = new Action(ActionType.CREATE);

		Request toServer = new Request(Session.getInstance(), toDo, newCourse);

		// serverCom = server communication
		EasyTeachClient serverCom = new EasyTeachClient(toServer);
		serverCom.run();
		serverCom.getResponse();

		Response responseFromServer = serverCom.getResponse();
		System.out.println(responseFromServer.getStatus() + ": "
				+ responseFromServer.getResponseMessage());

		// Create the new relation(s)
		ResourceSet newClassCourseRelation = new ResourceSet();

		for (Resource resource : this.associatedClassesSet) {
			Class classEntity = (Class) resource;
			newClassCourseRelation.add(new ClassCourseRelation(classEntity
					.getClassNo(), courseNo));
		}

		// Sending the new relation(s) to the server. Reusing the Action,
		// Request, EasyTeachClient and Response objects from the creation of
		// the new course
		toDo = new Action(ActionType.UPDATE, "course");
		toServer = new Request(Session.getInstance(), toDo,
				newClassCourseRelation);
		serverCom = new EasyTeachClient(toServer);
		serverCom.run();
		responseFromServer = serverCom.getResponse();

		if (responseFromServer.getStatus() == ResponseStatus.SUCCESS) {
			JOptionPane.showMessageDialog(null, "Saved succesfully!");
			MainFrame.updateFrame(new AdminManagerUI().getAdminManagerUI(),
					"Admin Manager");
		}
	}

	public void filter(String comboBoxValue, String by) {
		if (comboBoxValue.equals("") && by.equals("")) {
			refreshAvailableClasses();
		} else {
			this.isFiltered = true;
			
			this.filteredClassesSet = new ResourceSet();

			for (Resource resource : this.availableClassesSet) {
				Class classEntity = (Class) resource;

				// Be sure that the comboBoxValues is actually equal to what is
				// written!
				if (comboBoxValue.equals("Class No.")) {
					if (classEntity.getClassNo().contains(by)) {
						this.filteredClassesSet.add(classEntity);
					}
				} else if (comboBoxValue.equals("Class name")) {
					if (classEntity.getClassName().contains(by)) {
						this.filteredClassesSet.add(classEntity);
					}
				} else if (comboBoxValue.equals("Year")) {
					String year = classEntity.getYear() + "";
					if (year.contains(by)) {
						this.filteredClassesSet.add(classEntity);
					}
				}
				refreshAvailableClasses();
			}
		}
	}

	public void setCurrentlySelectedClassFromAssociationTable(String classNo) {
		for (Resource resource : this.associatedClassesSet) {
			Class classEntity = (Class) resource;
			if (classEntity.getClassNo().equals(classNo)) {
				this.currentlySelectedClassFromAssociatedTable = classEntity;
			}
		}
	}

	public void setCurrectlySelectedClassFromAvailableTable(String classNo) {
		for (Resource resource : this.availableClassesSet) {
			Class classEntity = (Class) resource;
			if (classEntity.getClassNo().equals(classNo)) {
				this.currentlySelectedClassFromAvailableTable = classEntity;
			}
		}
	}

	/**
	 * Deletes everything in the ResourceSets
	 */
	public void discard() {
		this.associatedClassesSet = null;
		this.availableClassesSet = null;
		this.filteredClassesSet = null;
	}

	public void add() {
		// Adds the select row from the available HashSet to the associated
		// HashSet
		if (this.associatedClassesSet == null) {
			this.associatedClassesSet = new ResourceSet();
		}

		if (this.currentlySelectedClassFromAvailableTable != null) {
			this.associatedClassesSet.add(this.currentlySelectedClassFromAvailableTable);
			refreshTableModels();
			this.currentlySelectedClassFromAvailableTable = null;
		} else {
			JOptionPane.showMessageDialog(null,
					"You have to choose a class to add to the course!");
		}
	}

	public void remove() {
		// Removes the row from the associated HashSet

		if (this.currentlySelectedClassFromAssociatedTable != null) {
			this.associatedClassesSet
					.remove(this.currentlySelectedClassFromAssociatedTable);
			refreshTableModels();
			this.currentlySelectedClassFromAssociatedTable = null;
		} else {
			JOptionPane.showMessageDialog(null,
					"You have to choose a class to remove from the course!");
		}
	}

	public DisplayTableModel getDTMAssociatedClasses() {
		return this.manageAssociatedClasses;
	}

	public DisplayTableModel getDTMAvailableClasses() {
		return this.manageAvailableClasses;
	}

	private class ManageCourseModel extends DisplayTableModel {

		private static final long serialVersionUID = -5132427387930797460L;

		public ManageCourseModel(ResourceSet classesSet) {
			super(ManageCoursePresenter.this.tableHead, classesSet);
		}

		@Override
		public String getValueAt(int row, int column) {
			// tableData is the table that performs the method call getValueAt
			// in the UI class
			Class classEntity = (Class) this.tableData.get(row);

			switch (getColumnName(column)) {
			case "Class No.":
				return classEntity.getClassNo();
			case "Year":
				return Integer.toString(classEntity.getYear());
			case "Class Name":
				return classEntity.getClassName();
			default:
				return new String();
			}
		}
	}

}
