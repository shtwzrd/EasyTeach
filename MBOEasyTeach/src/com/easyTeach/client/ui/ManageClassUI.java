package com.easyTeach.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.easyTeach.client.presenter.DisplayTableModel;
import com.easyTeach.client.presenter.HelpPresenter;
import com.easyTeach.client.presenter.ManageClassPresenter;

/**
 * The ManageClassUI class constructs a JPanel with all the different
 * JComponents needed to add new classes and manage old ones.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 6. December, 2013
 */

public class ManageClassUI {

	private final String[] FILTER = { "Email", "First name", "Last name",
			"Date added" };
	private JPanel manageClassPanel;
	private JButton btnHelp;
	private JButton btnDiscard;
	private JButton btnSaveClass;
	private JTable enrolledClassesTable;
	private JTable allStudentsTable;
	private JPanel centerPanel;
	private JButton btnRemoveStudent;
	private JButton btnAddStudent;
	protected JTextField txtYear;
	protected JTextField txtClassName;
	private JComboBox filterBox;
	private JTextField txtFilter;
	private JButton btnFilter;
	protected ManageClassPresenter presenter;
	private boolean isSyncing;

	/**
	 * Constructor for building the manageClassPanel. The panel is built by
	 * calling the buildPanel method. The constructor also calls a method adding
	 * actionListeners to all buttons.
	 */
	public ManageClassUI() {
		presenter = new ManageClassPresenter();
		buildPanel();
		addActionListeners();
	}

	/**
	 * Returns the ManageClassPanel with all of the JComponents within.
	 * 
	 * @return the JPanel manageClassPanel
	 */
	public JPanel getManageClassUI() {
		return manageClassPanel;
	}

	/**
	 * Builds the ManageClassPanel with all of the JComponenets within.
	 */
	public void buildPanel() {
		manageClassPanel = new JPanel(new BorderLayout());

		buildNorthPanel();
		buildCenterPanel();
		buildSouthPanel();
	}

	/**
	 * Builds the north panel with the the headline of the UI.
	 */
	private void buildNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(UIColors.darkBlue);

		JLabel lblManageClassTitle = new JLabel("Manage Class");
		lblManageClassTitle.setForeground(UIColors.white);
		lblManageClassTitle.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		northPanel.add(lblManageClassTitle);

		manageClassPanel.add(northPanel, BorderLayout.NORTH);
	}

	/**
	 * Builds the center panel with the needed information to create/edit a new
	 * class (className, year, and enrolled students in the class).
	 */
	private void buildCenterPanel() {
		centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(UIColors.lightBlue);

		buildInfoPanel();
		buildStudentPanel();

		manageClassPanel.add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 * Builds the Class panel where a Student can be enrolled to the class being
	 * created/edited or be removed from the class. The panel consists of two
	 * tables showing what students exist in the system and what students are
	 * part of the class under discussion.
	 */
	private void buildStudentPanel() {
		JPanel studentPanel = new JPanel(new GridLayout(2, 1));
		studentPanel.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, new Color(0, 0, 0), null),
				"Students in class", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Tahoma", Font.PLAIN, 20)));
		studentPanel.setBackground(UIColors.lightBlue);
		centerPanel.add(studentPanel, BorderLayout.CENTER);

		// The panel showing the students currently in the class
		JPanel enrolledStudentsPanel = new JPanel(new BorderLayout());
		enrolledStudentsPanel.setBorder(new TitledBorder(null,
				"Enrolled Students", TitledBorder.CENTER, TitledBorder.TOP));
		enrolledStudentsPanel.setBackground(UIColors.lightBlue);

		// Table showing all enrolled students
		enrolledClassesTable = new JTable();
		enrolledClassesTable.setModel(presenter.getEnrolledStudentsModel());

		JScrollPane enrolledClassesScroll = new JScrollPane(
				enrolledClassesTable);
		enrolledClassesScroll.getViewport().setBackground(UIColors.white);
		enrolledStudentsPanel.add(enrolledClassesScroll, BorderLayout.CENTER);

		studentPanel.add(enrolledStudentsPanel);

		// The panel showing all students in the system
		JPanel allClassesPanel = new JPanel(new BorderLayout());

		allClassesPanel.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, new Color(0, 0, 0), null),
				"Available Students", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Tahoma", Font.PLAIN, 20)));
		allClassesPanel.setBackground(UIColors.lightBlue);
		studentPanel.add(allClassesPanel);

		JPanel addRemovePanel = new JPanel();
		addRemovePanel.setBackground(UIColors.lightBlue);
		addRemovePanel.setLayout(new GridLayout(1, 2));

		btnRemoveStudent = new JButton("Remove Student From Class");
		btnRemoveStudent.setEnabled(presenter.canRemove());
		addRemovePanel.add(btnRemoveStudent);

		btnAddStudent = new JButton("Add Student To Class");
		btnAddStudent.setEnabled(presenter.canAdd());
		addRemovePanel.add(btnAddStudent);

		enrolledStudentsPanel.add(addRemovePanel, BorderLayout.SOUTH);

		// Table showing all classes
		allStudentsTable = new JTable();
		allStudentsTable.setModel(presenter.getAllStudentsModel());

		JScrollPane allStudentsScroll = new JScrollPane(allStudentsTable);
		allStudentsScroll.getViewport().setBackground(UIColors.white);
		allClassesPanel.add(allStudentsScroll, BorderLayout.CENTER);

		JPanel filterPanel = new JPanel();
		filterPanel.setBorder(new TitledBorder(null,
				"Filter Available Students", TitledBorder.CENTER,
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
		allClassesPanel.add(filterPanel, BorderLayout.SOUTH);
	}

	/**
	 * Builds the Info Panel which is part of the centerPanel. The Info Panel
	 * contains JComponents for inserting the class name and year.
	 * */
	private void buildInfoPanel() {
		JPanel infoPanel = new JPanel(new GridLayout(2, 2));
		infoPanel.setBackground(UIColors.lightBlue);
		infoPanel.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, new Color(0, 0, 0), null),
				"Class Information", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Tahoma", Font.PLAIN, 20)));

		JLabel lblClassName = new JLabel("Class Name:");
		lblClassName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblClassName.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(lblClassName);

		txtClassName = new JTextField();
		infoPanel.add(txtClassName);

		JLabel lblClassYear = new JLabel("Year:");
		lblClassYear.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblClassYear.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(lblClassYear);

		txtYear = new JTextField();
		infoPanel.add(txtYear);

		centerPanel.add(infoPanel, BorderLayout.NORTH);
	}

	/**
	 * Builds the South Panel which has buttons for Help, Discard, and Save
	 * Class.
	 */
	private void buildSouthPanel() {
		JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		southPanel.setBackground(UIColors.darkBlue);

		btnHelp = new JButton("Help");
		southPanel.add(btnHelp);

		btnDiscard = new JButton("Discard");
		southPanel.add(btnDiscard);

		btnSaveClass = new JButton("Save Class");
		southPanel.add(btnSaveClass);

		manageClassPanel.add(southPanel, BorderLayout.SOUTH);
	}

	/**
	 * Adds an ActionListener of type ManageClassListener to all the JButtons
	 * from the manageClassPanel.
	 */

	private void addActionListeners() {
		ManageClassListener listener = new ManageClassListener();
		btnDiscard.addActionListener(listener);
		btnHelp.addActionListener(listener);
		btnSaveClass.addActionListener(listener);
	}

	protected void syncWithPresenter() {
		if (!isSyncing) {
			isSyncing = true;
			btnAddStudent.setEnabled(presenter.canAdd());
			btnRemoveStudent.setEnabled(presenter.canRemove());

			enrolledClassesTable.setModel(presenter.getEnrolledStudentsModel());
			allStudentsTable.setModel(presenter.getAllStudentsModel());
			isSyncing = false;
		}
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

	/**
	 * The ManageClassListener class is the class in charge of listening for
	 * events happening in the ManageClassUI (e.g. a user clicking the help
	 * button). When an event occurs the ManageClassListener will send a signal
	 * to the ManageClassPresenter which will in return act upon the event.
	 * 
	 * @author Morten Faarkrog
	 * @version 0.1
	 * @see ActionListener
	 * @date 6. December, 2013
	 */
	private class ManageClassListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnDiscard) {
				int reply = JOptionPane.showConfirmDialog(null,
						"Are you sure you want discard what you have made?\n"
								+ "Warning: It will not be saved.",
						"Discard Message", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					MainFrame.updateFrame(
							new AdminManagerUI().getAdminManagerUI(),
							"Admin Manager");
				}
			}

			else if (e.getSource() == btnSaveClass) {
				presenter.save(txtClassName.getText(),
						Integer.parseInt(txtYear.getText()));
				syncWithPresenter();
			}

			else if (e.getSource() == btnFilter) {
				presenter.filter();
				syncWithPresenter();
			}

			else if (e.getSource() == btnAddStudent) {
				presenter.add();
				syncWithPresenter();
			}

			else if (e.getSource() == btnRemoveStudent) {
				presenter.remove();
				syncWithPresenter();
			}

			else if (e.getSource() == btnHelp) {
				JOptionPane.showMessageDialog(null,
						HelpPresenter.getManageClassHelp(),
						HelpPresenter.getManageClassTitle(),
						JOptionPane.PLAIN_MESSAGE, HelpPresenter.getHelpIcon());
			}
		}
	}

}
