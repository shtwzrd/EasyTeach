package com.easyTeach.client.presenter;

import javax.swing.table.DefaultTableModel;

public class DisplayTableModel extends DefaultTableModel {

	private static final long serialVersionUID = -1818692271815576526L;

	@Override
    public boolean isCellEditable(int row, int column) {
       return false;
    }
    
}
