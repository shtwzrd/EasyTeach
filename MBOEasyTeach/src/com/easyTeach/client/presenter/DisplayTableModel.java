package com.easyTeach.client.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.easyTeach.common.entity.Resource;
import com.easyTeach.common.entity.ResourceSet;

/**
 * Extension of AbstractTableModel, with uneditable cells (Display-oriented)
 * and polymorphic handling of tableData as Resources.
 * 
 * <p>
 * Most of the necessary methods for AbstractTableModel are already satisfied
 * by this extension, so that Presenters implementing this Model for their
 * table models need only implement the getValueAt method for their appropriate
 * Resource.
 * </p>
 * 
 * @author Brandon Lucas
 * @version 1.1
 * @date 12 December, 2013 
 *
 */
public abstract class DisplayTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -1818692271815576526L;
	protected List<Resource> tableData;
	protected String[] columnHeaders;

	public DisplayTableModel(String[] columnHeaders, ResourceSet resources) {
		if (resources != null) {
			this.tableData = new ArrayList<>(resources);
		} else {
			this.tableData = new ArrayList<>();
		}
		this.columnHeaders = columnHeaders;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public int getRowCount() {
		return this.tableData.size();
	}

	public String getColumnName(int column) {
		return this.columnHeaders[column];
	}

	public int getColumnCount() {
		return this.columnHeaders.length;
	}
	
	public void refreshData(ResourceSet resources) {
		this.tableData = new ArrayList<>(resources);
	}
	
	public Resource getResourceAtRow(int row) {
		return this.tableData.get(row);
	}

	@Override
	public abstract Object getValueAt(int row, int column);

}
