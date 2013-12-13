package com.easyTeach.client.presenter;

import java.util.UUID;

import com.easyTeach.client.network.EasyTeachClient;
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
 * TODO Link this presenter to the ManageCourseUI and incorporate the filter.
 * 
 * @author Oliver Nielsen
 */
public class ManageCoursePresenter {

	private final String[] tableHead = { "Class No.", "Class Name", "Year" };

	private DisplayTableModel dtmAssociatedClasses;
	private DisplayTableModel dtmAvailableClasses;

	private int countAssociatedClassesRow;
	private int countAvailableClassesRow;

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
		dtmAssociatedClasses = new DisplayTableModel();
		dtmAvailableClasses = new DisplayTableModel();

		dtmAssociatedClasses.setColumnIdentifiers(tableHead);
		dtmAvailableClasses.setColumnIdentifiers(tableHead);

		countAssociatedClassesRow = 0;
		countAvailableClassesRow = 0;

		refreshAvailableClasses();
		refreshAssociatedClasses();
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
			// Get all the classes as a resources
			availableClassesSet = (ResourceSet) serverCom.getResponse()
					.getResponse();

			// Testing if the filter is on
			filteredClassesSet = availableClassesSet;

			if (isFiltered) {
				// All the courses is in a ResourceSet (as a Resource) so they
				// have to be "converted" to courses by typecasting and put into
				// a DisplayTableModel.
				// The ResourceSet is filtered.
				for (Resource resource : filteredClassesSet) {
					Class classEntity = (Class) resource;

					dtmAvailableClasses
							.setColumnCount(countAvailableClassesRow);

					dtmAvailableClasses.setValueAt(classEntity.getClassNo(),
							countAvailableClassesRow, 0);
					dtmAvailableClasses.setValueAt(classEntity.getClassName(),
							countAvailableClassesRow, 1);
					dtmAvailableClasses.setValueAt(classEntity.getYear(),
							countAvailableClassesRow, 2);

					countAvailableClassesRow++;
				}
			} else {
				// All the courses is in a ResourceSet (as a Resource) so they
				// have to be "converted" to courses by typecasting and put into
				// a DisplayTableModel.
				// The ResourceSet is not filtered.
				for (Resource resource : availableClassesSet) {
					Class classEntity = (Class) resource;

					dtmAvailableClasses
							.setColumnCount(countAvailableClassesRow);

					dtmAvailableClasses.setValueAt(classEntity.getClassNo(),
							countAvailableClassesRow, 0);
					dtmAvailableClasses.setValueAt(classEntity.getClassName(),
							countAvailableClassesRow, 1);
					dtmAvailableClasses.setValueAt(classEntity.getYear(),
							countAvailableClassesRow, 2);

					countAvailableClassesRow++;
				}
			}

		}

	}

	private void refreshAssociatedClasses() {
		for (Resource resource : associatedClassesSet) {
			Class classEntity = (Class) resource;

			dtmAssociatedClasses.setColumnCount(countAssociatedClassesRow);

			dtmAssociatedClasses.setValueAt(classEntity.getClassNo(),
					countAssociatedClassesRow, 0);
			dtmAssociatedClasses.setValueAt(classEntity.getClassName(),
					countAssociatedClassesRow, 1);
			dtmAssociatedClasses.setValueAt(classEntity.getYear(),
					countAssociatedClassesRow, 2);

			countAssociatedClassesRow++;
		}
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

		for (Resource resource : associatedClassesSet) {
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
		System.out.println(responseFromServer.getStatus() + ": "
				+ responseFromServer.getResponseMessage());
	}

	public void filter(String comboBoxValue, String by) {
		if (comboBoxValue.equals("") && by.equals("")) {
			refreshAvailableClasses();
		} else {
			isFiltered = true;

			for (Resource resource : availableClassesSet) {
				Class classEntity = (Class) resource;

				// Be sure that the comboBoxValues is actually equal to what is
				// written!
				if (comboBoxValue.equals("Class No.")) {
					if (classEntity.getClassNo().contains(by)) {
						filteredClassesSet.add(classEntity);
					}
				} else if (comboBoxValue.equals("Class name")) {
					if (classEntity.getClassName().contains(by)) {
						filteredClassesSet.add(classEntity);
					}
				} else if (comboBoxValue.equals("Year")) {
					if (classEntity.getYear() == Integer.parseInt(by)) {
						filteredClassesSet.add(classEntity);
					}
				}
			}
		}
	}

	public void setCurrentlySelectedClassFromAssociationTable(String classNo) {
		for (Resource resource : associatedClassesSet) {
			Class classEntity = (Class) resource;
			if (classEntity.getClassNo().equals(classNo)) {
				currentlySelectedClassFromAssociatedTable = classEntity;
			}
		}
	}

	public void setCurrectlySelectedClassFromAvailableTable(String classNo) {
		for (Resource resource : availableClassesSet) {
			Class classEntity = (Class) resource;
			if (classEntity.getClassNo().equals(classNo)) {
				currentlySelectedClassFromAvailableTable = classEntity;
			}
		}
	}

	/**
	 * Deletes everything in the ResourceSets
	 */
	public void discard() {
		associatedClassesSet = null;
		availableClassesSet = null;
		filteredClassesSet = null;
	}

	public void add() {
		// Adds the select row from the available HashSet to the associated
		// HashSet
		associatedClassesSet.add(currentlySelectedClassFromAvailableTable);
		// Removes the select row from the available table
		availableClassesSet.remove(currentlySelectedClassFromAvailableTable);
		refreshAssociatedClasses();
		refreshAvailableClasses();
	}

	public void remove() {
		// Removes the row from the associated HashSet
		associatedClassesSet.remove(currentlySelectedClassFromAssociatedTable);
		// Adds the selected row from the associated HashSet to the available
		// HashSet
		availableClassesSet.add(currentlySelectedClassFromAssociatedTable);
		refreshAvailableClasses();
		refreshAssociatedClasses();
	}

	public DisplayTableModel getDTMAssociatedClasses() {
		return dtmAssociatedClasses;
	}

	public DisplayTableModel getDTMAvailableClasses() {
		return dtmAvailableClasses;
	}

}
