package com.easyTeach.client.ui;

import javax.swing.table.DefaultTableModel;

public class DefaultTableModel1 extends DefaultTableModel {

    @Override
    public boolean isCellEditable(int row, int column) {
       return false;
    }
    
}
