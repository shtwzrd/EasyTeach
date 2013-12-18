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
 * Class for the presenter layer in the ManageCourseUI. Every event is handled
 * by this class and keeps track of the classes associations.
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

	private Course editCourse;

	/**
	 * Constructor for ManageCoursePresenter. Used to adding new courses.
	 */
	public ManageCoursePresenter() {
		this.manageAssociatedClasses = new ManageCourseModel(this.associatedClassesSet);
		this.manageAvailableClasses = new ManageCourseModel(this.availableClassesSet);

		refreshAvailableClasses();
	}

	/**
	 * Constructor for ManageCoursePresenter. Used to editing courses.
	 * 
	 * @param editCourse
	 *            The course that should be edited.
	 */
	public ManageCoursePresenter(Course editCourse) {
		this.editCourse = editCourse;

		this.manageAssociatedClasses = new ManageCourseModel(this.associatedClassesSet);
		this.manageAvailableClasses = new ManageCourseModel(this.availableClassesSet);

		refreshAvailableClasses();
	}

	/**
	 * Method for fetching courses data. Both when adding and editing courses.
	 */
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

			if (this.editCourse != null) {
				Action readRelations = new Action(ActionType.READ, "classes");
				Request getRelations = new Request(Session.getInstance(),
						readRelations, this.editCourse);

				serverCom = new EasyTeachClient(getRelations);
				serverCom.run();
				serverCom.getResponse();

				if (serverCom.getResponse().getStatus() != ResponseStatus.FAILURE) {
					this.associatedClassesSet = (ResourceSet) serverCom
							.getResponse().getResponse();
				}
				this.manageAssociatedClasses.refreshData(this.associatedClassesSet);
				this.manageAssociatedClasses.fireTableDataChanged();
			}

			// This method call changes what is in the table model according to
			// what happened in the if/else statement a few lines above
			this.manageAvailableClasses.fireTableDataChanged();
		}
	}

	/**
	 * Method for changes in the table. When called it update the two table with
	 * the new information.
	 */
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

	/**
	 * Method for saving the added or edited course.
	 * 
	 * @param courseName
	 *            The name of the course.
	 */
	public void saveCourse(String courseName) {
		if (!courseName.equals("")) {
			String courseNo = UUID.randomUUID().toString();
			Course newCourse = new Course(courseNo, courseName);

			// Sending the new course to the server
			Action toDo;

			if (this.editCourse != null) {
				toDo = new Action(ActionType.UPDATE);
			} else {
				toDo = new Action(ActionType.CREATE);
			}

			Request toServer = new Request(Session.getInstance(), toDo,
					newCourse);

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
			// Request, EasyTeachClient and Response objects from the creation
			// of
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
			} else {
				JOptionPane.showMessageDialog(null,
						"Something went wrong. Try again!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Invalid course name!");
		}
	}

	/**
	 * Method for filtering the availableClassesSet, which is all the unselected
	 * classes for a course.
	 * 
	 * @param comboBoxValue
	 *            The value in the combo box.
	 * @param by
	 *            The value that is filtered by.
	 */
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

	/**
	 * Method for setting the currently selected class from the association
	 * table, which is triggered as an MouseEvent in ManageCourseUI.
	 * 
	 * @param classNo
	 *            The class number of the select class.
	 */
	public void setCurrentlySelectedClassFromAssociationTable(String classNo) {
		for (Resource resource : this.associatedClassesSet) {
			Class classEntity = (Class) resource;
			if (classEntity.getClassNo().equals(classNo)) {
				this.currentlySelectedClassFromAssociatedTable = classEntity;
			}
		}
	}

	/**
	 * Method for setting the currently selected class from the available table,
	 * which is triggered as an MouseEvent in ManageCourseUI.
	 * 
	 * @param classNo
	 *            The class number of the select class.
	 */
	public void setCurrectlySelectedClassFromAvailableTable(String classNo) {
		for (Resource resource : this.availableClassesSet) {
			Class classEntity = (Class) resource;
			if (classEntity.getClassNo().equals(classNo)) {
				this.currentlySelectedClassFromAvailableTable = classEntity;
			}
		}
	}

	/**
	 * Deletes everything in the ResourceSets.
	 */
	public void discard() {
		this.associatedClassesSet = null;
		this.availableClassesSet = null;
		this.filteredClassesSet = null;
	}

	/**
	 * Method for adding selected classes into the associated table.
	 */
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

	/**
	 * Method for removing selected classes from the associated table.
	 */
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

	/**
	 * @return the associated table model.
	 */
	public DisplayTableModel getDTMAssociatedClasses() {
		return this.manageAssociatedClasses;
	}

	/**
	 * @return the available table model.
	 */
	public DisplayTableModel getDTMAvailableClasses() {
		return this.manageAvailableClasses;
	}

	/**
	 * Method for getting the edited classes name.
	 * 
	 * @return the edited course name.
	 */
	public String getEditCourseName() {
		if (this.editCourse != null) {
			return this.editCourse.getCourseName();
		}
		return "";
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
