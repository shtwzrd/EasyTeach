package com.easyTeach.client.presenter;

import com.easyTeach.client.network.EasyTeachClient;
import com.easyTeach.common.entity.Class;
import com.easyTeach.common.entity.Resource;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.network.Action;
import com.easyTeach.common.network.Action.ActionType;
import com.easyTeach.common.network.Request;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.common.network.Session;

/**
 * Presenter handling the logic and interaction between the AdminClassManagerUI
 * and the EasyTeachClient/Server.
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

public class AdminClassManagerPresenter {
	private ClassTableModel classesModel;
	protected ResourceSet classesSet;
	protected ResourceSet filteredClassesSet;
	private EasyTeachClient client;
	private Class currentlySelectedClass;
	private boolean isFiltered;

	String[] tableColumnHeaders = { "Class name", "Year" };

	/**
	 * Initializes sets and table models
	 */
	public AdminClassManagerPresenter() {
		this.classesModel = new ClassTableModel(this.tableColumnHeaders,
				this.classesSet);
		refreshClassTable();
	}

	public ClassTableModel getClassesModel() {
		return this.classesModel;
	}

	public void setSelectedClass(int row) {
		this.currentlySelectedClass = (Class) this.classesModel
				.getResourceAtRow(row);
	}

	public Class getSelectedClass() {
		return this.currentlySelectedClass;
	}

	/**
	 * Gets the currently selected class, removes it from the database,
	 * and alerts the table model of the change.
	 */
	public void removeCurrrentlySelected() {
		if (this.currentlySelectedClass != null) {
			this.classesSet.remove(this.currentlySelectedClass);
			Action rm = new Action(ActionType.DELETE);
			Request remove = new Request(Session.getInstance(), rm,
					this.currentlySelectedClass);
			this.client = new EasyTeachClient(remove);
			this.client.run();
		}
		if (this.isFiltered) {
			this.classesModel.refreshData(this.filteredClassesSet);
		} else {
			this.classesModel.refreshData(this.classesSet);
		}
		this.classesModel.fireTableDataChanged();
	}

	/**
	 * Sends a {@link Request} to the Server, updating the Presenter's set of
	 * available Classs.
	 * 
	 * <p>
	 * It checks if the data in the table should be filtered, and updates the
	 * Table Model accordingly, before firing the dataChanged event and forcing
	 * the table in the UI to update.
	 * </p>
	 */
	private void refreshClassTable() {

		Action toDo = new Action(ActionType.READ, "all");

		Request getClasses = new Request(Session.getInstance(), toDo,
				new Class());
		this.client = new EasyTeachClient(getClasses);
		this.client.run();
		this.client.getResponse();
		if (this.client.getResponse().getStatus() != ResponseStatus.FAILURE) {
			this.classesSet = (ResourceSet) this.client.getResponse()
					.getResponse();
		}

		if (this.isFiltered) {
			this.classesModel.refreshData(this.filteredClassesSet);
		} else {
			this.classesModel.refreshData(this.classesSet);
		}

	}

	/**
	 * Logic for filtering the table containing the selection of lasses by a
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
			this.filteredClassesSet = new ResourceSet();
			switch (column) {
			case "Class Name":
				for (Resource r : this.classesSet) {
					Class c = (Class) r;
					if (c.getClassName().contains(by)) {
						this.filteredClassesSet.add(c);
					}
				}
				break;
			case "Year":
				for (Resource r : this.classesSet) {
					Class c = (Class) r;
					if (Integer.toString(c.getYear()).equals(by)) {
						this.filteredClassesSet.add(c);
					}
				}
				break;
			}
		}
		this.refreshClassTable();
		this.classesModel.fireTableDataChanged();
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
	private class ClassTableModel extends DisplayTableModel {
		public ClassTableModel(String[] columnHeaders, ResourceSet resources) {
			super(columnHeaders, resources);
		}

		private static final long serialVersionUID = -2996998684634631118L;

		@Override
		public String getValueAt(int row, int column) {
			Class c = (Class) this.tableData.get(row);
			switch (this.getColumnName(column)) {
			case "Class name":
				return c.getClassName();
			case "Year":
				return Integer.toString(c.getYear());
			default:
				return new String();
			}
		}
	}
}
