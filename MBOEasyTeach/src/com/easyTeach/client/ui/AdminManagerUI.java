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

import com.easyTeach.client.presenter.DisplayTableModel;
import com.easyTeach.client.presenter.HelpPresenter;

/**
 * The AdminManagerUI class constructs a JPanel with all the different
 * JComponents needed to maintain users, classes and courses. This includes
 * JTables with classes and courses along with buttons for deleting, adding and
 * editing courses and classes.
 * 
 * @author Morten Faarkrog
 * @version 1.1
 * @date 7. December, 2013
 */

public class AdminManagerUI {

    private final String[] FILTER_CLASS = { "Class Name", "Year", "No. of Classs" };
    private final String[] FILTER_COURSE = { "Course Name", "No. of Classes" };
    private final String[] FILTER_USER = { "First name", "Last name", "Email",
            "Date Added" };
    private JPanel adminManagerPanel;
    private JButton btnEdit;
    private JButton btnHelp;
    private JButton btnDelete;
    private JButton btnAdd;
    private JTabbedPane tabPanel;
    private JPanel classPanel;
    private JPanel coursePanel;
    private JTextField txtFilter;
    private JTable classTable;
    private JTable courseTable;
    private JTable adminTable;
    private JTable teacherTable;
    private JTable studentTable;
    private JTabbedPane userTabPanel;
    private JButton courseFilter;
    private JTextField courseTxt;
    private JComboBox courseCombo;
    private JButton classFilter;
    private JTextField classTxt;
    private JComboBox classCombo;
    private JButton studentFilter;
    private JTextField studentTxt;
    private JComboBox studentCombo;
    private JButton teacherFilter;
    private JTextField teacherTxt;
    private JComboBox teacherCombo;
    private JButton adminFilter;
    private JTextField adminTxt;
    private JComboBox adminCombo;

    /**
     * Constructor for building the adminManagerPanel. The panel is built by
     * calling the buildPanel method. The constructor also calls a method adding
     * actionListeners to all buttons.
     */
    public AdminManagerUI() {
        buildPanel();
        addActionListeners();
    }

    /**
     * The getAdminManagerUI returns the JPanel containing all the components
     * needed to maintain users, classes and courses.
     * 
     * @return the JPanel, adminManagerPanel. Meant to be used in another frame.
     */
    public JPanel getAdminManagerUI() {
        return adminManagerPanel;
    }

    /**
     * The method responsible for building the adminManagerPanel. The panel
     * consists of all the JComponenets needed to maintain users, classes and
     * courses.
     */
    public void buildPanel() {
        adminManagerPanel = new JPanel(new BorderLayout());
        adminManagerPanel.setBackground(UIColors.lightBlue);

        buildNorthPanel();
        buildCenterPanel();
        buildSouthPanel();
    }

    /**
     * Builds the center panel with the tabbed panels for managing users,
     * classes and courses.
     */
    private void buildCenterPanel() {
        tabPanel = new JTabbedPane(JTabbedPane.TOP);
        tabPanel.setBackground(UIColors.lightBlue);
        adminManagerPanel.add(tabPanel, BorderLayout.CENTER);

        buildUserPanel();
        buildClassPanel();
        buildCoursePanel();
    }

    /**
     * Method for building the Course Panel tab. The Course Panel consists of a
     * JTable with all Courses along with a filtering function for the table.
     */
    private void buildCoursePanel() {
        coursePanel = new JPanel(new BorderLayout());
        coursePanel.setBackground(UIColors.lightBlue);

        courseTable = new JTable();
        DisplayTableModel courseModel = new DisplayTableModel();
        String[] courseHeads = { "Course name", "No. of Classes" };
        courseModel.setRowCount(1);
        courseModel.setColumnIdentifiers(courseHeads);
        courseModel.setValueAt("Software Design", 0, 0);
        courseModel.setValueAt("15", 0, 1);
        courseTable.setModel(courseModel);

        JScrollPane courseScroll = new JScrollPane(courseTable);
        courseScroll.getViewport().setBackground(UIColors.white);
        coursePanel.add(courseScroll, BorderLayout.CENTER);

        courseFilter = new JButton("Filter");
        courseTxt = new JTextField(14);
        courseCombo = new JComboBox();
        courseCombo.setModel(new DefaultComboBoxModel(FILTER_COURSE));

        coursePanel.add(getFilterPanel(courseFilter, courseTxt, courseCombo),
                BorderLayout.SOUTH);
        coursePanel.setName("Courses");
        tabPanel.addTab("Courses", coursePanel);
    }

    /**
     * Method for building the Class Panel tab. The Class Panel consists of a
     * JTable with all classes along with a filtering function for the table.
     */
    private void buildClassPanel() {
        classPanel = new JPanel(new BorderLayout());
        classPanel.setBackground(UIColors.lightBlue);

        classTable = new JTable();
        DisplayTableModel classModel = new DisplayTableModel();
        String[] classHeads = { "Class Name", "Year", "No. of Students" };
        classModel.setRowCount(1);
        classModel.setColumnIdentifiers(classHeads);
        classModel.setValueAt("DAT13W", 0, 0);
        classModel.setValueAt("2013", 0, 1);
        classModel.setValueAt("14", 0, 2);
        classTable.setModel(classModel);

        JScrollPane classScroll = new JScrollPane(classTable);
        classScroll.getViewport().setBackground(UIColors.white);
        classPanel.add(classScroll, BorderLayout.CENTER);

        classFilter = new JButton("Filter");
        classTxt = new JTextField(14);
        classCombo = new JComboBox();
        classCombo.setModel(new DefaultComboBoxModel(FILTER_CLASS));

        classPanel.add(getFilterPanel(classFilter, classTxt, classCombo),
                BorderLayout.SOUTH);
        classPanel.setName("Classes");
        tabPanel.addTab("Classes", classPanel);
    }

    /**
     * Method for building the User Panel tab. The User Panel consists of a
     * JTable with all users (students, teachers and admins) along with a
     * filtering function for the tables.
     */
    private void buildUserPanel() {
        userTabPanel = new JTabbedPane();
        userTabPanel.setName("Users");
        userTabPanel.setBackground(UIColors.lightBlue);

        buildStudentPanel();
        buildTeacherPanel();
        buildAdminPanel();

        tabPanel.addTab("Users", userTabPanel);
    }

    /**
     * Method for building the Admin Panel tab for the Users tab. The Admin
     * Panel consists of a JTable with all Admins along with a filtering
     * function for the table.
     */
    private void buildAdminPanel() {
        JPanel adminPanel = new JPanel(new BorderLayout());
        adminPanel.setBackground(UIColors.lightBlue);

        adminTable = new JTable();
        DisplayTableModel adminModel = new DisplayTableModel();
        String[] adminHeads = { "Last name", "First name", "Email",
                "Date added" };
        adminModel.setRowCount(1);
        adminModel.setColumnIdentifiers(adminHeads);
        adminModel.setValueAt("Doe", 0, 0);
        adminModel.setValueAt("Johanna", 0, 1);
        adminModel.setValueAt("joha1234@stud.kea.dk", 0, 2);
        adminModel.setValueAt("02-24-2000", 0, 3);
        adminTable.setModel(adminModel);

        JScrollPane adminScroll = new JScrollPane(adminTable);
        adminScroll.getViewport().setBackground(UIColors.white);
        adminPanel.add(adminScroll, BorderLayout.CENTER);

        adminFilter = new JButton("Filter");
        adminTxt = new JTextField(14);
        adminCombo = new JComboBox();
        adminCombo.setModel(new DefaultComboBoxModel(FILTER_USER));

        adminPanel.add(getFilterPanel(adminFilter, adminTxt, adminCombo),
                BorderLayout.SOUTH);
        adminPanel.setName("Admins");
        userTabPanel.addTab("Admins", adminPanel);
    }

    /**
     * Method for building the Teacher Panel tab to the Users tab. The Teacher
     * Panel consists of a JTable with all Teachers along with a filtering
     * function for the table.
     */
    private void buildTeacherPanel() {
        JPanel teacherPanel = new JPanel(new BorderLayout());
        teacherPanel.setBackground(UIColors.lightBlue);

        teacherTable = new JTable();
        DisplayTableModel teacherModel = new DisplayTableModel();
        String[] teacherHeads = { "Last name", "First name", "Email",
                "Date added" };
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

        teacherFilter = new JButton("Filter");
        teacherTxt = new JTextField(14);
        teacherCombo = new JComboBox();
        teacherCombo.setModel(new DefaultComboBoxModel(FILTER_USER));

        teacherPanel.add(
                getFilterPanel(teacherFilter, teacherTxt, teacherCombo),
                BorderLayout.SOUTH);
        teacherPanel.setName("Teachers");
        userTabPanel.addTab("Teachers", teacherPanel);
    }

    /**
     * Method for building the Student Panel tab for the Users tab. The Student
     * Panel consists of a JTable with all Students along with a filtering
     * function for the table.
     */
    private void buildStudentPanel() {
        JPanel studentPanel = new JPanel(new BorderLayout());
        studentPanel.setBackground(UIColors.lightBlue);

        studentTable = new JTable();
        DisplayTableModel studentModel = new DisplayTableModel();
        String[] studentHeads = { "Last name", "First name", "Email",
                "Date added" };
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

        studentFilter = new JButton("Filter");
        studentTxt = new JTextField(14);
        studentCombo = new JComboBox();
        studentCombo.setModel(new DefaultComboBoxModel(FILTER_USER));

        studentPanel.add(
                getFilterPanel(studentFilter, studentTxt, studentCombo),
                BorderLayout.SOUTH);
        studentPanel.setName("Students");
        userTabPanel.addTab("Students", studentPanel);
    }

    /**
     * Builds the north panel with the the headline of the UI.
     */
    private void buildNorthPanel() {
        JPanel northPanel = new JPanel();
        northPanel.setBackground(UIColors.darkBlue);

        JLabel lblManageUserTitle = new JLabel("User, Class & Course Manager");
        lblManageUserTitle.setForeground(UIColors.white);
        lblManageUserTitle.setFont(new Font("Lucida Grande", Font.BOLD, 24));
        northPanel.add(lblManageUserTitle);

        adminManagerPanel.add(northPanel, BorderLayout.NORTH);
    }

    /**
     * Returns the filtering panel for the user, course and class table. The
     * method takes
     * 
     * @param but
     *            is the JButton for the filterPanel
     * @param txt
     *            is the JTextField for the filterPanel
     * @param combo
     *            is the JComboBox for the filterPanel
     * @return the filterPanel with all its needed components.
     */
    public JPanel getFilterPanel(JButton but, JTextField txt, JComboBox combo) {
        JPanel filterPanel = new JPanel();

        filterPanel.setBorder(new TitledBorder(null, "Filter",
                TitledBorder.CENTER, TitledBorder.TOP));
        filterPanel.setBackground(UIColors.lightBlue);

        JLabel lblFilter = new JLabel("Filter");
        filterPanel.add(lblFilter);
        filterPanel.add(combo);
        JLabel lblBy = new JLabel("by");
        filterPanel.add(lblBy);
        filterPanel.add(txt);
        filterPanel.add(but);

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

        btnEdit = new JButton("Edit");
        southButtonPanel.add(btnEdit);

        btnDelete = new JButton("Delete");
        southButtonPanel.add(btnDelete);

        btnAdd = new JButton("Add");
        southButtonPanel.add(btnAdd);

        adminManagerPanel.add(southButtonPanel, BorderLayout.SOUTH);
    }

    /**
     * Adds an ActionListener of type AdminManagerUIListener to all the JButtons
     * from the adminManagerPanel.
     */
    private void addActionListeners() {
        AdminManagerUIListener listener = new AdminManagerUIListener();
        btnAdd.addActionListener(listener);
        btnDelete.addActionListener(listener);
        btnEdit.addActionListener(listener);
        btnHelp.addActionListener(listener);
        studentFilter.addActionListener(listener);
        teacherFilter.addActionListener(listener);
        adminFilter.addActionListener(listener);
        classFilter.addActionListener(listener);
        courseFilter.addActionListener(listener);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    /**
     * The AdminManagerUIListener class is the class in charge of listening for
     * events happening in the AdminManagerUI (e.g. an user clicking the help
     * button). When an event occurs the AdminManagerUIListener will send a
     * signal to the AdminManagerPresenter which will in return act upon the
     * event.
     * 
     * @author Morten Faarkrog
     * @version 0.1
     * @see ActionListener
     * @date 7. December, 2013
     */
    private class AdminManagerUIListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnAdd) {
                String selectedTab = tabPanel.getSelectedComponent().getName();
                if (selectedTab.equals("Users")) {
                    MainFrame.updateFrame(
                            new ManageUserUI().getManageUserUI(),
                            "EasyTeach - Add New User");
                } else if (selectedTab.equals("Classes")) {
                    MainFrame.updateFrame(
                            new ManageClassUI().getManageClassUI(),
                            "EasyTeach - Add New Class");
                } else if (selectedTab.equals("Courses")) {
                    MainFrame.updateFrame(
                            new ManageCourseUI().getManageCourseUI(),
                            "EasyTeach - Add New Course");
                }
            }

            // Finds the selected row and...
            else if (e.getSource() == btnDelete) {

            }

            else if (e.getSource() == btnEdit) {

            }

            else if (e.getSource() == btnHelp) {
                JOptionPane.showMessageDialog(null,
                        HelpPresenter.getAdminManagerHelp(),
                        HelpPresenter.getAdminManagerTitle(),
                        JOptionPane.PLAIN_MESSAGE, HelpPresenter.getHelpIcon());
            }

            else if (e.getSource() == studentFilter) {
                System.out.println(studentTxt.getText());
            }

            else if (e.getSource() == teacherFilter) {
                System.out.println(teacherTxt.getText());
                System.out.println(teacherCombo.getSelectedItem());
            }

            else if (e.getSource() == adminFilter) {
                System.out.println(adminTxt.getText());
            }

            else if (e.getSource() == classFilter) {
                System.out.println(classTxt.getText());
            }

            else if (e.getSource() == courseFilter) {
                System.out.println(courseTxt.getText());
            }
        }
        
    }

}