package com.easyTeach.client.presenter;

import java.util.HashMap;

import com.easyTeach.client.network.EasyTeachClient;
import com.easyTeach.common.entity.Course;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.network.Action;
import com.easyTeach.common.network.Action.ActionType;
import com.easyTeach.common.network.Request;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.common.network.Session;

/**
 * Handles the logic necessary for communicating with the EasyTeachClient/Server
 * on behalf of the AdminCourseManagerUI.
 * 
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
public class AdminCourseManagerPresenter {

	private CourseTableModel coursesModel;
	protected ResourceSet coursesSet;
	protected ResourceSet filteredCoursesSet;
	private EasyTeachClient client;
	private Course currentlySelectedCourse;
	HashMap<String, Integer> classesOfCourse;

	String[] tableColumnHeaders = { "Course name" };

	/**
	 * Initializes sets and table models
	 */
	public AdminCourseManagerPresenter() {
		this.coursesModel = new CourseTableModel(this.tableColumnHeaders,
				this.coursesSet);
		this.classesOfCourse = new HashMap<>();
		refreshCourseTable();
	}

	public CourseTableModel getCoursesModel() {
		return this.coursesModel;
	}

	public void setSelectedCourse(int row) {
		this.currentlySelectedCourse = (Course) this.coursesModel
				.getResourceAtRow(row);
	}

	public Course getSelectedCourse() {
		return this.currentlySelectedCourse;
	}

	/**
	 * Gets the currently selected course and removes it from the database, then
	 * fires an event to the table model to inform it of the change in data
	 */
	public void removeCurrrentlySelected() {
		if (this.currentlySelectedCourse != null) {
			this.coursesSet.remove(this.currentlySelectedCourse);
			Action rm = new Action(ActionType.DELETE);
			Request remove = new Request(Session.getInstance(), rm,
					this.currentlySelectedCourse);
			this.client = new EasyTeachClient(remove);
			this.client.run();
			this.coursesModel.refreshData(this.coursesSet);
		}
		this.coursesModel.fireTableDataChanged();
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
	private void refreshCourseTable() {

		Action toDo = new Action(ActionType.READ, "all");

		Request getCourses = new Request(Session.getInstance(), toDo,
				new Course());
		this.client = new EasyTeachClient(getCourses);
		this.client.run();
		this.client.getResponse();
		if (this.client.getResponse().getStatus() != ResponseStatus.FAILURE) {
			this.coursesSet = (ResourceSet) this.client.getResponse()
					.getResponse();
			this.coursesModel.refreshData(this.coursesSet);
		}

	}

	/**
	 * 
	 * @author Brandon Lucas
	 * @version 1.0
	 * @date 12 December, 2013
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
	private class CourseTableModel extends DisplayTableModel {
		public CourseTableModel(String[] columnHeaders, ResourceSet resources) {
			super(columnHeaders, resources);
		}

		private static final long serialVersionUID = -2996998684634631118L;

		@Override
		public String getValueAt(int row, int column) {
			Course c = (Course) this.tableData.get(row);
			switch (this.getColumnName(column)) {
			case "Course name":
				return c.getCourseName();
			default:
				return new String();
			}
		}
	}
}
