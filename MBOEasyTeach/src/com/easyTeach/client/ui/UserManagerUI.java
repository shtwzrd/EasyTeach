package com.easyTeach.client.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.easyTeach.client.presenter.HelpPresenter;

/**
 * The UserManagerUI class constructs a JPanel with all the different 
 * JComponents needed to maintain users. This includes JTables with
 * students, teachers and admins along with buttons for deleting, adding
 * and editing new users.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 5. December, 2013
 */

public class UserManagerUI {

    private final String[] FILTER = {"First name", "Last name", "Email", "Date Added"};
    private JPanel userManagerPanel;
    private JButton btnEditUser;
    private JButton btnHelp;
    private JButton btnDeleteUser;
    private JButton btnAddUser;
    private JTabbedPane tabPanel;
    private JPanel studentPanel;
    private JPanel teacherPanel;
    private JPanel adminPanel;
    private JButton btnFilter;
    private JComboBox filterBox;
    private JTextField txtFilter;
    private JTable studentTable;
    private JTable teacherTable;
    private JTable adminTable;

    /**
     * Constructor for building the userManagerPanel. The panel is built by 
     * calling the buildPanel method. The constructor also calls a method
     * adding actionListeners to all buttons.
     */
    public UserManagerUI() {
        buildPanel();
        addActionListeners();
    }
    
    /**
     * The getUserManagerUI returns the JPanel containing all the components
     * needed to maintain users.
     * 
     * @return the JPanel, userManagerPanel. Meant to be used in another frame.
     */
    public JPanel getUserManagerUI() {
        return userManagerPanel;
    }
    
    /**
     * The method responsible for building the userManagerPanel. The panel
     * consists of all the JComponenets needed to maintain users.
     */
    public void buildPanel() {
        userManagerPanel = new JPanel(new BorderLayout());
        userManagerPanel.setBackground(UIColors.lightBlue);
        
        buildNorthPanel();
        buildCenterPanel();
        buildSouthPanel();               
    }

    /**
     * Builds the center panel with the tabbed panels for managing
     * students, teachers and admins. Every tab will come with a JTable
     * for browsing the users along with options for filtering the table.
     */
    private void buildCenterPanel() {
        tabPanel = new JTabbedPane(JTabbedPane.TOP);
        userManagerPanel.add(tabPanel, BorderLayout.CENTER);
        
        buildStudentPanel();
        buildTeacherPanel();
        buildAdminPanel();
    }

    /**
     * Method for building the Admin Panel tab. The Admin Panel consists of
     * a JTable with all Admins along with a filtering function for the table.
     */
    private void buildAdminPanel() {
        adminPanel = new JPanel(new BorderLayout());
        adminPanel.setBackground(UIColors.lightBlue);
        
        adminTable = new JTable();
        DefaultTableModel1 adminModel = new DefaultTableModel1();
        String[] adminHeads = {"Last name", "First name", "Email", "Date added"};
        adminModel.setRowCount(1);
        adminModel.setColumnIdentifiers(adminHeads);
        adminModel.setValueAt("Doe", 0, 0);
        adminModel.setValueAt("Johanna", 0, 1);
        adminModel.setValueAt("joha1234@stud.kea.dk", 0, 2);
        adminModel.setValueAt("02-24-2000", 0, 3);
        adminPanel.setLayout(new BorderLayout());
        adminTable.setModel(adminModel);
        
        JScrollPane adminScroll = new JScrollPane(adminTable);
        adminScroll.getViewport().setBackground(UIColors.white);
        adminPanel.add(adminScroll, BorderLayout.CENTER);
        
        adminPanel.add(getFilterPanel(), BorderLayout.SOUTH);
        tabPanel.addTab("Admin", adminPanel);
    }

    /**
     * Method for building the Teacher Panel tab. The Teacher Panel consists of
     * a JTable with all Teachers along with a filtering function for the table.
     */
    private void buildTeacherPanel() {
        teacherPanel = new JPanel(new BorderLayout());
        teacherPanel.setBackground(UIColors.lightBlue);

        teacherTable = new JTable();
        DefaultTableModel1 teacherModel = new DefaultTableModel1();
        String[] teacherHeads = {"Last name", "First name", "Email", "Date added"};
        teacherModel.setRowCount(1);
        teacherModel.setColumnIdentifiers(teacherHeads);
        teacherModel.setValueAt("Doe", 0, 0);
        teacherModel.setValueAt("John", 0, 1);
        teacherModel.setValueAt("john9999@stud.kea.dk", 0, 2);
        teacherModel.setValueAt("05-23-2011", 0, 3);
        teacherPanel.setLayout(new BorderLayout());
        teacherTable.setModel(teacherModel);

        JScrollPane teacherScroll = new JScrollPane(teacherTable);
        teacherScroll.getViewport().setBackground(UIColors.white);
        teacherPanel.add(teacherScroll, BorderLayout.CENTER);
        
        teacherPanel.add(getFilterPanel(), BorderLayout.SOUTH);
        tabPanel.addTab("Teacher", teacherPanel);
    }

    /**
     * Method for building the Student Panel tab. The Student Panel consists of
     * a JTable with all Students along with a filtering function for the table.
     */
    private void buildStudentPanel() {
        studentPanel = new JPanel(new BorderLayout());
        studentPanel.setBackground(UIColors.lightBlue);
        
        studentTable = new JTable();
        DefaultTableModel1 studentModel = new DefaultTableModel1();
        String[] studentHeads = {"Last name", "First name", "Email", "Date added"};
        studentModel.setRowCount(1);
        studentModel.setColumnIdentifiers(studentHeads);
        studentModel.setValueAt("Smith", 0, 0);
        studentModel.setValueAt("John", 0, 1);
        studentModel.setValueAt("john1234@stud.kea.dk", 0, 2);
        studentModel.setValueAt("01-20-2013", 0, 3);
        studentTable.setModel(studentModel);
        
        JScrollPane studentScroll = new JScrollPane(studentTable);
        studentScroll.getViewport().setBackground(UIColors.white);
        studentPanel.add(studentScroll, BorderLayout.CENTER);
        
        studentPanel.add(getFilterPanel(), BorderLayout.SOUTH);
        tabPanel.setBackground(UIColors.lightBlue);
        tabPanel.addTab("Student", studentPanel);
    }

    /**
     * Builds the north panel with the the headline of the UI.
     */
    private void buildNorthPanel() {
        JPanel northPanel = new JPanel();
        northPanel.setBackground(UIColors.darkBlue);
        
        JLabel lblManageUserTitle = new JLabel("User Manager");
        lblManageUserTitle.setForeground(UIColors.white);
        lblManageUserTitle.setFont(new Font("Lucida Grande", Font.BOLD, 24));
        northPanel.add(lblManageUserTitle);

        userManagerPanel.add(northPanel, BorderLayout.NORTH);
    }
    
    /**
     * Returns the filtering panel for the student, teacher and admin tables.
     * 
     * @return A filtering JPanel to be used elsewhere.
     */
    public JPanel getFilterPanel() {
        JPanel filterPanel = new JPanel();
        
        filterPanel.setBorder(new TitledBorder(null, "Filter", TitledBorder.CENTER, 
                TitledBorder.TOP));
        filterPanel.setBackground(UIColors.lightBlue);
        
        JLabel lblFilter = new JLabel("Filter");
        filterPanel.add(lblFilter);
        
        filterBox = new JComboBox();
        filterBox.setModel(new DefaultComboBoxModel(FILTER));
        filterPanel.add(filterBox);
        
        JLabel lblBy = new JLabel("by");
        filterPanel.add(lblBy);
        
        txtFilter = new JTextField();
        filterPanel.add(txtFilter);
        txtFilter.setColumns(14);
        
        btnFilter = new JButton("Filter");
        filterPanel.add(btnFilter);
        
        return filterPanel;
    }
    
    /**
     * Method for creating the south panel with buttons for getting help and 
     * editing/deleting/adding users.
     */
    private void buildSouthPanel() {
        JPanel southButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southButtonPanel.setBackground(UIColors.darkBlue);
        
        btnHelp = new JButton("Help");
        southButtonPanel.add(btnHelp);
        
        btnEditUser = new JButton("Edit");
        southButtonPanel.add(btnEditUser);
        
        btnDeleteUser = new JButton("Delete");
        southButtonPanel.add(btnDeleteUser);
        
        btnAddUser = new JButton("Add");
        southButtonPanel.add(btnAddUser);

        userManagerPanel.add(southButtonPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Adds an ActionListener of type UserManagerUIListener to all the JButtons 
     * from the userManagerPanel.
     */
    private void addActionListeners() {
        UserManagerUIListener listener = new UserManagerUIListener();
        btnAddUser.addActionListener(listener);
        btnDeleteUser.addActionListener(listener);
        btnEditUser.addActionListener(listener);
        btnHelp.addActionListener(listener);
        btnFilter.addActionListener(listener);
    }
    
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    
    /**
     * The UserManagerUIListener class is the class in charge of listening
     * for events happening in the UserManagerUI (e.g. an user clicking the 
     * help button). When an event occurs the UserManagerUIListener will 
     * send a signal to the UserManagerPresenter which will in return act 
     * upon the event.
     * 
     * @author Morten Faarkrog
     * @version 0.1
     * @see ActionListener
     * @date 5. December, 2013
     */
    private class UserManagerUIListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnAddUser) {
                
            }
            
            else if (e.getSource() == btnDeleteUser) {
                
            }
            
            else if (e.getSource() == btnEditUser) {
                
            }
            
            else if (e.getSource() == btnHelp) {
                JOptionPane.showMessageDialog(null, HelpPresenter.getUserManagerHelp(), 
                        HelpPresenter.getUserManagerTitle(), JOptionPane.PLAIN_MESSAGE, 
                        HelpPresenter.getHelpIcon());
            }
            
            else if (e.getSource() == btnFilter) {
                
            }
        }
        
    }
    
}