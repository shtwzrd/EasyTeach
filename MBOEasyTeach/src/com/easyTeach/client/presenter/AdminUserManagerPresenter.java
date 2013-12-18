package com.easyTeach.client.presenter;

import com.easyTeach.client.network.EasyTeachClient;
import com.easyTeach.common.entity.Resource;
import com.easyTeach.common.entity.User;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.network.Action;
import com.easyTeach.common.network.Action.ActionType;
import com.easyTeach.common.network.Request;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.common.network.Session;
import com.easyTeach.server.domainLogic.RoleResource.Role;

/**
 * Handles the logic necessary for communicating with the EasyTeachClient/Server
 * on behalf of the AdminUserManagerUI.
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

public class AdminUserManagerPresenter {
	private UserTableModel userModel;
	protected ResourceSet userSet;
	protected ResourceSet filteredUserSet;
	private EasyTeachClient client;
	private User currentlySelectedUser;
	private boolean isFiltered;
	private Role role;

	String[] tableColumnHeaders = { "Email", "First name", "Last name",
			"Date added" };

	/**
	 * Initializes sets and table models
	 */
	public AdminUserManagerPresenter(Role role) {
		this.role = role;
		this.userModel = new UserTableModel(this.tableColumnHeaders,
				this.userSet);
		this.userSet = new ResourceSet();
		refreshUserTable();
	}

	public UserTableModel getUserModel() {
		return this.userModel;
	}

	public void setSelectedUser(int row) {
		this.currentlySelectedUser = (User) this.userModel
				.getResourceAtRow(row);
	}

	public User getSelectedUser() {
		return this.currentlySelectedUser;
	}

	/**
	 * Gets the currently selected user and removes it from the database, then
	 * fires an event to the table model to inform it of the change in data
	 */
	public void removeCurrrentlySelected() {
		if (this.currentlySelectedUser != null) {
			this.userSet.remove(this.currentlySelectedUser);
			Action rm = new Action(ActionType.DELETE);
			Request remove = new Request(Session.getInstance(), rm,
					this.currentlySelectedUser);
			this.client = new EasyTeachClient(remove);
			this.client.run();
		}
		if (this.isFiltered) {
			this.userModel.refreshData(this.filteredUserSet);
		} else {
			this.userModel.refreshData(this.userSet);
		}
		this.userModel.fireTableDataChanged();
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
			this.filteredUserSet = new ResourceSet();
			switch (column) {
			case "Email":
				for (Resource r : this.userSet) {
					User u = (User) r;
					if (u.getEmail().contains(by)) {
						this.filteredUserSet.add(u);
					}
				}
				break;
			case "First name":
				for (Resource r : this.userSet) {
					User u = (User) r;
					if (u.getFirstName().contains(by)) {
						this.filteredUserSet.add(u);
					}
				}
				break;
			case "Last name":
				for (Resource r : this.userSet) {
					User u = (User) r;
					if (u.getLastName().contains(by)) {
						this.filteredUserSet.add(u);
					}
				}
				break;
			}
		}
		this.refreshUserTable();
		this.userModel.fireTableDataChanged();
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
	private void refreshUserTable() {

		String attrib;
		switch (this.role) {
		case ADMIN:
			attrib = "admins";
			break;
		case TEACHER:
			attrib = "teachers";
			break;
		default:
			attrib = "students";
		}
		Action toDo = new Action(ActionType.READ, attrib);

		Request getUsers = new Request(Session.getInstance(), toDo, new User());
		this.client = new EasyTeachClient(getUsers);
		this.client.run();
		this.client.getResponse();
		if (this.client.getResponse().getStatus() != ResponseStatus.FAILURE) {
			this.userSet = (ResourceSet) this.client.getResponse()
					.getResponse();
		}

		if (this.isFiltered) {
			this.userModel.refreshData(this.filteredUserSet);
		} else {
			this.userModel.refreshData(this.userSet);
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
	private class UserTableModel extends DisplayTableModel {
		public UserTableModel(String[] columnHeaders, ResourceSet resources) {
			super(columnHeaders, resources);
		}

		private static final long serialVersionUID = -2996998684634631118L;

		@Override
		public String getValueAt(int row, int column) {
			User u = (User) this.tableData.get(row);
			switch (this.getColumnName(column)) {
			case "Email":
				return u.getEmail();
			case "First name":
				return u.getFirstName();
			case "Last name":
				return u.getLastName();
			case "Date added":
				return u.getDateAdded().toString();
			default:
				return new String();
			}
		}
	}
}