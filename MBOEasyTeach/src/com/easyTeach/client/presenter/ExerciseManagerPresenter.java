package com.easyTeach.client.presenter;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import com.easyTeach.client.network.EasyTeachClient;
import com.easyTeach.common.entity.Exercise;
import com.easyTeach.common.entity.Resource;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.network.Action;
import com.easyTeach.common.network.Action.ActionType;
import com.easyTeach.common.network.Request;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.common.network.Session;

/**
 * The ExerciseManagerPresenter communicates with the domain logic on behalf of
 * the ExerciseManagerUI, providing all of its logic.
 * <p>
 * The Listeners in the UI class call the relevant methods in the Presenter in
 * order to update and retrieve information from the domain logic. This is an
 * implementation of the Model View Presenter pattern.
 * </p>
 * 
 * @author Brandon Lucas
 * @version 1.0
 * @date 14. December, 2013
 */
public class ExerciseManagerPresenter {

	private ExerciseTableModel exerciseTable;
	protected ResourceSet filteredSelectionSet;
	protected ResourceSet exerciseSet;
	private EasyTeachClient client;
	private Exercise currentlySelectedExercise;
	private boolean isFiltered;

	private String[] exerciseColumnHeaders = { "Exercise Name", "Date added" };

	/**
	 * Initializes sets and table models
	 */
	public ExerciseManagerPresenter() {
		this.exerciseSet = new ResourceSet();

		this.exerciseTable = new ExerciseTableModel(this.exerciseColumnHeaders,
				this.exerciseSet);

		refreshTable();
	}

	public ExerciseTableModel getExerciseTableModel() {
		return this.exerciseTable;
	}

	public void setSelectedExercise(int row) {
		this.currentlySelectedExercise = (Exercise) this.exerciseTable
				.getResourceAtRow(row);
	}

	/**
	 * Sends a {@link Request} to the Server, updating the Presenter's set of
	 * Exercises.
	 * 
	 * <p>
	 * It checks if the data in the table should be filtered, and updates the
	 * Table Model accordingly, before firing the dataChanged event and forcing
	 * the table in the UI to update.
	 * </p>
	 */
	private void refreshTable() {

		Action toDo = new Action(ActionType.READ, "all");

		Request getExercises = new Request(Session.getInstance(), toDo,
				new Exercise());
		this.client = new EasyTeachClient(getExercises);
		this.client.run();
		this.client.getResponse();
		if (this.client.getResponse().getStatus() != ResponseStatus.FAILURE) {
			this.exerciseSet = (ResourceSet) this.client.getResponse()
					.getResponse();
		}
		if (this.isFiltered) {
			this.exerciseTable.refreshData(this.filteredSelectionSet);
		} else {
			this.exerciseTable.refreshData(this.exerciseSet);
		}

		this.exerciseTable.fireTableDataChanged();
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
			case "Author":
				for (Resource r : this.exerciseSet) {
					Exercise e = (Exercise) r;
					if (e.getAuthor().contains(by)) {
						this.filteredSelectionSet.add(e);
					}
				}
				break;
			case "Exercise Name":
				for (Resource r : this.exerciseSet) {
					Exercise e = (Exercise) r;
					if (e.getExerciseName().contains(by)) {
						this.filteredSelectionSet.add(e);
					}
				}
				break;
			}
		}
		this.refreshTable();
		this.exerciseTable.refreshData(this.filteredSelectionSet);
		this.exerciseTable.fireTableDataChanged();
	}

	public void duplicate() {
		if (this.currentlySelectedExercise != null) {
			String newCourseId = UUID.randomUUID().toString();
			Exercise newExercise = this.currentlySelectedExercise;
			newExercise.setExerciseNo(newCourseId);
			GregorianCalendar cal = new GregorianCalendar();
			newExercise.setDateAdded(new Timestamp(cal.getTimeInMillis()));

			Action createExercise = new Action(ActionType.CREATE);
			Request dupeExercise = new Request(Session.getInstance(),
					createExercise, newExercise);

			this.client = new EasyTeachClient(dupeExercise);
			this.client.run();

			this.refreshTable();
			this.exerciseTable.refreshData(this.exerciseSet);
			this.exerciseTable.fireTableDataChanged();
		}
	}

	/**
	 * Removes a User from the EnrolledTable.
	 */
	public void delete() {
		if (this.currentlySelectedExercise != null) {
			Action rm = new Action(ActionType.DELETE);
			Request rmExercise = new Request(Session.getInstance(),
					rm, this.currentlySelectedExercise);

			this.client = new EasyTeachClient(rmExercise);
			this.client.run();
			this.refreshTable();
			this.exerciseTable.refreshData(this.exerciseSet);
			this.exerciseTable.fireTableDataChanged();
		}
	}

	/**
	 * 
	 * @author Brandon Lucas
	 * @version 1.0
	 * @date 14 December, 2013
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
	private class ExerciseTableModel extends DisplayTableModel {
		public ExerciseTableModel(String[] columnHeaders, ResourceSet resources) {
			super(columnHeaders, resources);
		}

		private static final long serialVersionUID = -2996998684634631118L;

		@Override
		public String getValueAt(int row, int column) {
			Exercise e = (Exercise) this.tableData.get(row);
			switch (this.getColumnName(column)) {
			case "Exercise Name":
				return e.getExerciseName();
			case "Date added":
				return e.getDateAdded().toString();
			default:
				return new String();
			}
		}
	}
}
