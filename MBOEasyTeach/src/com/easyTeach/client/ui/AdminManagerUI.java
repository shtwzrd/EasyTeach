package com.easyTeach.client.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.easyTeach.client.presenter.AdminClassManagerPresenter;
import com.easyTeach.client.presenter.AdminCourseManagerPresenter;
import com.easyTeach.client.presenter.AdminUserManagerPresenter;
import com.easyTeach.client.presenter.HelpPresenter;
import com.easyTeach.server.domainLogic.RoleResource.Role;

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

	private final String[] FILTER_CLASS = { "Class Name", "Year" };
	private final String[] FILTER_USER = { "First name", "Last name", "Email",
			"Date Added" };
	private JPanel adminManagerPanel;
	JButton btnEdit;
	JButton btnHelp;
	JButton btnDelete;
	JButton btnAdd;
	JTabbedPane tabPanel;
	private JPanel classPanel;
	private JPanel coursePanel;
	private JTextField txtFilter;
	JTable classTable;
	JTable courseTable;
	JTable adminTable;
	JTable teacherTable;
	JTable studentTable;
	JTabbedPane userTabPanel;
	JButton courseFilter;
	JTextField courseTxt;
	JButton classFilter;
	JTextField classTxt;
	private JComboBox<String> classCombo;
	JButton studentFilter;
	JTextField studentTxt;
	private JComboBox<String> studentCombo;
	JButton teacherFilter;
	JTextField teacherTxt;
	JComboBox<String> teacherCombo;
	JButton adminFilter;
	JTextField adminTxt;
	private JComboBox<String> adminCombo;
	AdminCourseManagerPresenter coursePresenter;
	AdminClassManagerPresenter classPresenter;
	AdminUserManagerPresenter adminPresenter;
	AdminUserManagerPresenter teacherPresenter;
	AdminUserManagerPresenter studentPresenter;

	/**
	 * Constructor for building the adminManagerPanel. The panel is built by
	 * calling the buildPanel method. The constructor also calls a method adding
	 * actionListeners to all buttons.
	 */
	public AdminManagerUI() {
		this.coursePresenter = new AdminCourseManagerPresenter();
		this.classPresenter = new AdminClassManagerPresenter();
		this.adminPresenter = new AdminUserManagerPresenter(Role.ADMIN);
		this.teacherPresenter = new AdminUserManagerPresenter(Role.TEACHER);
		this.studentPresenter = new AdminUserManagerPresenter(Role.STUDENT);
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
		return this.adminManagerPanel;
	}

	/**
	 * The method responsible for building the adminManagerPanel. The panel
	 * consists of all the JComponenets needed to maintain users, classes and
	 * courses.
	 */
	public void buildPanel() {
		this.adminManagerPanel = new JPanel(new BorderLayout());
		this.adminManagerPanel.setBackground(UIColors.lightBlue);

		buildNorthPanel();
		buildCenterPanel();
		buildSouthPanel();
	}

	/**
	 * Builds the center panel with the tabbed panels for managing users,
	 * classes and courses.
	 */
	private void buildCenterPanel() {
		this.tabPanel = new JTabbedPane(SwingConstants.TOP);
		this.tabPanel.setBackground(UIColors.lightBlue);
		this.adminManagerPanel.add(this.tabPanel, BorderLayout.CENTER);

		buildUserPanel();
		buildClassPanel();
		buildCoursePanel();
	}

	/**
	 * Method for building the Course Panel tab. The Course Panel consists of a
	 * JTable with all Courses along with a filtering function for the table.
	 */
	private void buildCoursePanel() {
		this.coursePanel = new JPanel(new BorderLayout());
		this.coursePanel.setBackground(UIColors.lightBlue);

		this.courseTable = new JTable();
		this.courseTable.setModel(this.coursePresenter.getCoursesModel());

		JScrollPane courseScroll = new JScrollPane(this.courseTable);
		courseScroll.getViewport().setBackground(UIColors.white);
		this.coursePanel.add(courseScroll, BorderLayout.CENTER);

		this.coursePanel.setName("Courses");
		this.tabPanel.addTab("Courses", this.coursePanel);
	}

	/**
	 * Method for building the Class Panel tab. The Class Panel consists of a
	 * JTable with all classes along with a filtering function for the table.
	 */
	private void buildClassPanel() {
		this.classPanel = new JPanel(new BorderLayout());
		this.classPanel.setBackground(UIColors.lightBlue);

		this.classTable = new JTable();
		this.classTable.setModel(this.classPresenter.getClassesModel());

		JScrollPane classScroll = new JScrollPane(this.classTable);
		classScroll.getViewport().setBackground(UIColors.white);
		this.classPanel.add(classScroll, BorderLayout.CENTER);

		this.classFilter = new JButton("Filter");
		this.classTxt = new JTextField(14);
		this.classCombo = new JComboBox<>();
		this.classCombo.setModel(new DefaultComboBoxModel<>(this.FILTER_CLASS));

		this.classPanel
				.add(getFilterPanel(this.classFilter, this.classTxt,
						this.classCombo), BorderLayout.SOUTH);
		this.classPanel.setName("Classes");
		this.tabPanel.addTab("Classes", this.classPanel);
	}

	/**
	 * Method for building the User Panel tab. The User Panel consists of a
	 * JTable with all users (students, teachers and admins) along with a
	 * filtering function for the tables.
	 */
	private void buildUserPanel() {
		this.userTabPanel = new JTabbedPane();
		this.userTabPanel.setName("Users");
		this.userTabPanel.setBackground(UIColors.lightBlue);

		buildStudentPanel();
		buildTeacherPanel();
		buildAdminPanel();

		this.tabPanel.addTab("Users", this.userTabPanel);
	}

	/**
	 * Method for building the Admin Panel tab for the Users tab. The Admin
	 * Panel consists of a JTable with all Admins along with a filtering
	 * function for the table.
	 */
	private void buildAdminPanel() {
		JPanel adminPanel = new JPanel(new BorderLayout());
		adminPanel.setBackground(UIColors.lightBlue);

		this.adminTable = new JTable();
		this.adminTable.setModel(this.adminPresenter.getUserModel());

		JScrollPane adminScroll = new JScrollPane(this.adminTable);
		adminScroll.getViewport().setBackground(UIColors.white);
		adminPanel.add(adminScroll, BorderLayout.CENTER);

		this.adminFilter = new JButton("Filter");
		this.adminTxt = new JTextField(14);
		this.adminCombo = new JComboBox<>();
		this.adminCombo.setModel(new DefaultComboBoxModel<>(this.FILTER_USER));

		adminPanel
				.add(getFilterPanel(this.adminFilter, this.adminTxt,
						this.adminCombo), BorderLayout.SOUTH);
		adminPanel.setName("Admins");
		this.userTabPanel.addTab("Admins", adminPanel);
	}

	/**
	 * Method for building the Teacher Panel tab to the Users tab. The Teacher
	 * Panel consists of a JTable with all Teachers along with a filtering
	 * function for the table.
	 */
	private void buildTeacherPanel() {
		JPanel teacherPanel = new JPanel(new BorderLayout());
		teacherPanel.setBackground(UIColors.lightBlue);

		this.teacherTable = new JTable();
		this.teacherTable.setModel(this.teacherPresenter.getUserModel());

		JScrollPane teacherScroll = new JScrollPane(this.teacherTable);
		teacherScroll.getViewport().setBackground(UIColors.white);
		teacherPanel.add(teacherScroll, BorderLayout.CENTER);

		this.teacherFilter = new JButton("Filter");
		this.teacherTxt = new JTextField(14);
		this.teacherCombo = new JComboBox<>();
		this.teacherCombo
				.setModel(new DefaultComboBoxModel<>(this.FILTER_USER));

		teacherPanel.add(
				getFilterPanel(this.teacherFilter, this.teacherTxt,
						this.teacherCombo), BorderLayout.SOUTH);
		teacherPanel.setName("Teachers");
		this.userTabPanel.addTab("Teachers", teacherPanel);
	}

	/**
	 * Method for building the Student Panel tab for the Users tab. The Student
	 * Panel consists of a JTable with all Students along with a filtering
	 * function for the table.
	 */
	private void buildStudentPanel() {
		JPanel studentPanel = new JPanel(new BorderLayout());
		studentPanel.setBackground(UIColors.lightBlue);

		this.studentTable = new JTable();

		this.studentTable.setModel(this.studentPresenter.getUserModel());

		JScrollPane studentScroll = new JScrollPane(this.studentTable);
		studentScroll.getViewport().setBackground(UIColors.white);
		studentPanel.add(studentScroll, BorderLayout.CENTER);

		this.studentFilter = new JButton("Filter");
		this.studentTxt = new JTextField(14);
		this.studentCombo = new JComboBox<>();
		this.studentCombo
				.setModel(new DefaultComboBoxModel<>(this.FILTER_USER));

		studentPanel.add(
				getFilterPanel(this.studentFilter, this.studentTxt,
						this.studentCombo), BorderLayout.SOUTH);
		studentPanel.setName("Students");
		this.userTabPanel.addTab("Students", studentPanel);
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

		this.adminManagerPanel.add(northPanel, BorderLayout.NORTH);
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
	public JPanel getFilterPanel(JButton but, JTextField txt,
			JComboBox<String> combo) {
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

		this.btnHelp = new JButton("Help");
		southButtonPanel.add(this.btnHelp);

		this.btnEdit = new JButton("Edit");
		southButtonPanel.add(this.btnEdit);

		this.btnDelete = new JButton("Delete");
		southButtonPanel.add(this.btnDelete);

		this.btnAdd = new JButton("Add");
		southButtonPanel.add(this.btnAdd);

		this.adminManagerPanel.add(southButtonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Adds an ActionListener of type AdminManagerUIListener to all the JButtons
	 * from the adminManagerPanel.
	 */
	private void addActionListeners() {
		AdminManagerUIListener listener = new AdminManagerUIListener();
		this.btnAdd.addActionListener(listener);
		this.btnDelete.addActionListener(listener);
		this.btnEdit.addActionListener(listener);
		this.btnHelp.addActionListener(listener);
		this.studentFilter.addActionListener(listener);
		this.teacherFilter.addActionListener(listener);
		this.adminFilter.addActionListener(listener);
		this.classFilter.addActionListener(listener);
		this.adminTable.addMouseListener(listener);
		this.teacherTable.addMouseListener(listener);
		this.studentTable.addMouseListener(listener);
		this.courseTable.addMouseListener(listener);
		this.classTable.addMouseListener(listener);
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
	private class AdminManagerUIListener implements ActionListener,
			MouseListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String selectedTab = AdminManagerUI.this.tabPanel
					.getSelectedComponent().getName();
			String selectedUserTab = AdminManagerUI.this.userTabPanel
					.getSelectedComponent().getName();
			if (e.getSource() == AdminManagerUI.this.btnAdd) {
				if (selectedTab.equals("Users")) {
					MainFrame.updateFrame(new ManageUserUI().getManageUserUI(),
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
			else if (e.getSource() == AdminManagerUI.this.btnDelete) {
				if (selectedTab.equals("Classes")) {
					AdminManagerUI.this.classPresenter
							.removeCurrrentlySelected();
				} else if (selectedTab.equals("Courses")) {
					AdminManagerUI.this.coursePresenter
							.removeCurrrentlySelected();
				} else {
					if (selectedUserTab.equals("Students")) {
						AdminManagerUI.this.studentPresenter
								.removeCurrrentlySelected();
					} else if (selectedUserTab.equals("Teachers")) {
						AdminManagerUI.this.teacherPresenter
								.removeCurrrentlySelected();
					} else {
						AdminManagerUI.this.adminPresenter
								.removeCurrrentlySelected();
					}
				}

			}

			else if (e.getSource() == AdminManagerUI.this.btnEdit) {
				if (selectedTab.equals("Users")) {
					MainFrame.updateFrame(new ManageUserUI().getManageUserUI(),
							"EasyTeach - Add New User");
				} else if (selectedTab.equals("Classes")) {
					MainFrame.updateFrame(
							new ManageClassUI(
									AdminManagerUI.this.classPresenter
											.getSelectedClass())
									.getManageClassUI(),
							"EasyTeach - Edit Class");
				} else if (selectedTab.equals("Courses")) {
					MainFrame.updateFrame(
							new ManageCourseUI().getManageCourseUI(),
							"EasyTeach - Add New Course");
				}
			}

			else if (e.getSource() == AdminManagerUI.this.btnHelp) {
				JOptionPane.showMessageDialog(null,
						HelpPresenter.getAdminManagerHelp(),
						HelpPresenter.getAdminManagerTitle(),
						JOptionPane.PLAIN_MESSAGE, HelpPresenter.getHelpIcon());
			}

			else if (e.getSource() == AdminManagerUI.this.studentFilter) {
				System.out.println(AdminManagerUI.this.studentTxt.getText());
			}

			else if (e.getSource() == AdminManagerUI.this.teacherFilter) {
				System.out.println(AdminManagerUI.this.teacherTxt.getText());
				System.out.println(AdminManagerUI.this.teacherCombo
						.getSelectedItem());
			}

			else if (e.getSource() == AdminManagerUI.this.adminFilter) {
				System.out.println(AdminManagerUI.this.adminTxt.getText());
			}

			else if (e.getSource() == AdminManagerUI.this.classFilter) {
				System.out.println(AdminManagerUI.this.classTxt.getText());
			}

		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// Course Table
			if (arg0.getSource() == AdminManagerUI.this.courseTable) {
				AdminManagerUI.this.coursePresenter
						.setSelectedCourse(AdminManagerUI.this.courseTable
								.rowAtPoint(arg0.getPoint()));
				// Class Table
			} else if (arg0.getSource() == AdminManagerUI.this.classTable) {
				AdminManagerUI.this.classPresenter
						.setSelectedClass(AdminManagerUI.this.classTable
								.rowAtPoint(arg0.getPoint()));
				// User Tables...
				// Admin
			} else if (arg0.getSource() == AdminManagerUI.this.adminTable) {
				AdminManagerUI.this.adminPresenter
						.setSelectedUser(AdminManagerUI.this.adminTable
								.rowAtPoint(arg0.getPoint()));

				// Teacher
			} else if (arg0.getSource() == AdminManagerUI.this.teacherTable) {
				AdminManagerUI.this.teacherPresenter
						.setSelectedUser(AdminManagerUI.this.teacherTable
								.rowAtPoint(arg0.getPoint()));

				// Student
			} else if (arg0.getSource() == AdminManagerUI.this.studentTable) {
				AdminManagerUI.this.studentPresenter
						.setSelectedUser(AdminManagerUI.this.courseTable
								.rowAtPoint(arg0.getPoint()));
			}

		}

		@Override
		public void mouseEntered(MouseEvent arg0) { /* Do nothing */
		}

		@Override
		public void mouseExited(MouseEvent arg0) { /* Do nothing */
		}

		@Override
		public void mousePressed(MouseEvent arg0) { /* Do nothing */
		}

		@Override
		public void mouseReleased(MouseEvent arg0) { /* Do nothing */
		}
	}

}