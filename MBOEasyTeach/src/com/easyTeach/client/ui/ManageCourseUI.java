package com.easyTeach.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.easyTeach.client.presenter.HelpPresenter;
import com.easyTeach.client.presenter.ManageCoursePresenter;
import com.easyTeach.common.entity.Course;
import com.easyTeach.common.ui.UIColors;

/**
 * The ManageCourseUI class constructs a JPanel with all the different
 * JComponents needed to add new courses and manage old ones.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 7. December, 2013
 */

public class ManageCourseUI {

	private final String[] FILTER = { "Class No.", "Class name", "Year" };
	private JPanel manageCoursePanel;
	JButton btnHelp;
	JButton btnDiscard;
	JButton btnSaveCourse;
	JTable associatedClassesTable;
	JTable allClassesTable;
	private JPanel centerPanel;
	JButton btnRemoveCourse;
	JButton btnAddCourse;
	JTextField txtCourseName;
	JComboBox<String> filterBox;
	JTextField txtFilter;
	JButton btnFilter;

	ManageCoursePresenter presenter;

	/**
	 * Constructor for building the manageCoursePanel. The panel is built by
	 * calling the buildPanel method. The constructor also calls a method adding
	 * actionListeners to all buttons.
	 */
	public ManageCourseUI() {
		this.presenter = new ManageCoursePresenter();

		buildPanel();
		addActionListeners();
	}

	public ManageCourseUI(Course selectedCourse) {
		presenter = new ManageCoursePresenter(selectedCourse);
		
		buildPanel();
		addActionListeners();
		
		syncTables();
	}

	/**
	 * Returns the ManageCoursePanel with all of the JComponents within.
	 * 
	 * @return the JPanel manageCoursePanel
	 */
	public JPanel getManageCourseUI() {
		return this.manageCoursePanel;
	}

	/**
	 * Builds the ManageCoursePanel with all of the JComponenets within.
	 */
	public void buildPanel() {
		this.manageCoursePanel = new JPanel(new BorderLayout());

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

		JLabel lblManageCourseTitle = new JLabel("Manage Course");
		lblManageCourseTitle.setForeground(UIColors.white);
		lblManageCourseTitle.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		northPanel.add(lblManageCourseTitle);

		this.manageCoursePanel.add(northPanel, BorderLayout.NORTH);
	}

	/**
	 * Builds the center panel with the needed information to create/edit a
	 * course (courseName and a JTable with classes associated with the course).
	 */
	private void buildCenterPanel() {
		this.centerPanel = new JPanel(new BorderLayout());
		this.centerPanel.setBackground(UIColors.lightBlue);

		buildInfoPanel();
		buildClassPanel();

		this.manageCoursePanel.add(this.centerPanel, BorderLayout.CENTER);
	}

	/**
	 * Builds the Course panel where a Class can be associated to the course
	 * being created/edited or be removed from the class. The panel consists of
	 * two tables showing what classes exist in the system and what classes are
	 * part of the course under discussion.
	 */
	private void buildClassPanel() {
		JPanel classPanel = new JPanel(new GridLayout(2, 1));
		classPanel.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, new Color(0, 0, 0), null),
				"Classes in Course", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Tahoma", Font.PLAIN, 20)));
		classPanel.setBackground(UIColors.lightBlue);
		this.centerPanel.add(classPanel, BorderLayout.CENTER);

		// The panel showing the classes currently in the course
		JPanel associatedClassesPanel = new JPanel(new BorderLayout());
		associatedClassesPanel.setBorder(new TitledBorder(null,
				"Associated Classes", TitledBorder.CENTER, TitledBorder.TOP));
		associatedClassesPanel.setBackground(UIColors.lightBlue);

		// Table showing all associated classes
		this.associatedClassesTable = new JTable();
		this.associatedClassesTable.setModel(this.presenter.getDTMAssociatedClasses());

		JScrollPane associatedClassesScroll = new JScrollPane(
				this.associatedClassesTable);
		associatedClassesScroll.getViewport().setBackground(UIColors.white);
		associatedClassesPanel
				.add(associatedClassesScroll, BorderLayout.CENTER);

		classPanel.add(associatedClassesPanel);

		// The panel showing all classes in the system
		JPanel allClassesPanel = new JPanel(new BorderLayout());

		allClassesPanel.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, new Color(0, 0, 0), null),
				"Available Classes", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Tahoma", Font.PLAIN, 20)));
		allClassesPanel.setBackground(UIColors.lightBlue);
		classPanel.add(allClassesPanel);

		JPanel addRemovePanel = new JPanel();
		addRemovePanel.setBackground(UIColors.lightBlue);
		addRemovePanel.setLayout(new GridLayout(1, 2));

		this.btnRemoveCourse = new JButton("Remove Class From Course");
		addRemovePanel.add(this.btnRemoveCourse);

		this.btnAddCourse = new JButton("Add Class To Course");
		addRemovePanel.add(this.btnAddCourse);

		associatedClassesPanel.add(addRemovePanel, BorderLayout.SOUTH);

		// Table showing all classes
		this.allClassesTable = new JTable();
		this.allClassesTable.setModel(this.presenter.getDTMAvailableClasses());

		JScrollPane allClassesScroll = new JScrollPane(this.allClassesTable);
		allClassesScroll.getViewport().setBackground(UIColors.white);
		allClassesPanel.add(allClassesScroll, BorderLayout.CENTER);

		JPanel filterPanel = new JPanel();
		filterPanel.setBorder(new TitledBorder(null,
				"Filter Available Classes", TitledBorder.CENTER,
				TitledBorder.TOP));
		filterPanel.setBackground(UIColors.lightBlue);

		JLabel lblFilter = new JLabel("Filter");
		filterPanel.add(lblFilter);

		this.filterBox = new JComboBox<>();
		this.filterBox.setModel(new DefaultComboBoxModel<>(this.FILTER));
		filterPanel.add(this.filterBox);

		JLabel lblBy = new JLabel("by");
		filterPanel.add(lblBy);

		this.txtFilter = new JTextField();
		filterPanel.add(this.txtFilter);
		this.txtFilter.setColumns(14);

		this.btnFilter = new JButton("Filter");
		filterPanel.add(this.btnFilter);
		allClassesPanel.add(filterPanel, BorderLayout.SOUTH);
	}

	/**
	 * Builds the Info Panel which is part of the centerPanel. The Info Panel
	 * contains JComponents for inserting the course's name.
	 * */
	private void buildInfoPanel() {
		JPanel infoPanel = new JPanel(new GridLayout(1, 2));
		infoPanel.setBackground(UIColors.lightBlue);
		infoPanel.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, new Color(0, 0, 0), null),
				"Course Information", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Tahoma", Font.PLAIN, 20)));

		JLabel lblCourseName = new JLabel("Course Name:");
		lblCourseName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblCourseName.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(lblCourseName);

		this.txtCourseName = new JTextField();
		infoPanel.add(this.txtCourseName);

		this.centerPanel.add(infoPanel, BorderLayout.NORTH);
	}

	/**
	 * Builds the South Panel which has buttons for Help, Discard and Save
	 * Course.
	 */
	private void buildSouthPanel() {
		JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		southPanel.setBackground(UIColors.darkBlue);

		this.btnHelp = new JButton("Help");
		southPanel.add(this.btnHelp);

		this.btnDiscard = new JButton("Discard");
		southPanel.add(this.btnDiscard);

		this.btnSaveCourse = new JButton("Save Course");
		southPanel.add(this.btnSaveCourse);

		this.manageCoursePanel.add(southPanel, BorderLayout.SOUTH);
	}

	synchronized void syncTables() {
		if (txtCourseName.getText().equals("")) {
			txtCourseName.setText(presenter.getEditCourseName());
		}
		
		this.allClassesTable.setModel(this.presenter.getDTMAvailableClasses());
		this.associatedClassesTable.setModel(this.presenter.getDTMAssociatedClasses());
	}

	/**
	 * Adds an ActionListener of type ManageCourseListener to all the JButtons
	 * from the manageCoursePanel.
	 */
	private void addActionListeners() {
		ManageCourseListener listener = new ManageCourseListener();
		this.btnDiscard.addActionListener(listener);
		this.btnHelp.addActionListener(listener);
		this.btnSaveCourse.addActionListener(listener);
		this.btnRemoveCourse.addActionListener(listener);
		this.btnAddCourse.addActionListener(listener);
		this.btnFilter.addActionListener(listener);

		this.allClassesTable.addMouseListener(listener);
		this.associatedClassesTable.addMouseListener(listener);
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

	/**
	 * The ManageCourseListener class is the class in charge of listening for
	 * events happening in the ManageCourseUI (e.g. a user clicking the help
	 * button). When an event occurs the ManageCourseListener will send a signal
	 * to the ManageCoursePresenter which will in return act upon the event.
	 * 
	 * @author Morten Faarkrog - btnDiscard and btnHelp
	 * @author Oliver Nielsen - Everything else
	 * @version 0.1
	 * @see ActionListener
	 * @date 7. December, 2013
	 */
	private class ManageCourseListener implements ActionListener, MouseListener {

		public ManageCourseListener() {
			// Empty Constructor 
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == ManageCourseUI.this.btnDiscard) {
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

			else if (e.getSource() == ManageCourseUI.this.btnSaveCourse) {
				if (ManageCourseUI.this.txtCourseName.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"You have to write a course name!");
				} else {
					ManageCourseUI.this.presenter.saveCourse(ManageCourseUI.this.txtCourseName.getText());
				}
			}

			else if (e.getSource() == ManageCourseUI.this.btnAddCourse) {
				ManageCourseUI.this.presenter.add();
				syncTables();
			}

			else if (e.getSource() == ManageCourseUI.this.btnRemoveCourse) {
				ManageCourseUI.this.presenter.remove();
				syncTables();
			}

			else if (e.getSource() == ManageCourseUI.this.btnFilter) {
				String comboBoxValue = (String) ManageCourseUI.this.filterBox.getSelectedItem();
				String by = ManageCourseUI.this.txtFilter.getText();

				ManageCourseUI.this.presenter.filter(comboBoxValue, by);
				syncTables();
			}

			else if (e.getSource() == ManageCourseUI.this.btnHelp) {
				JOptionPane.showMessageDialog(null,
						HelpPresenter.getManageCourseHelp(),
						HelpPresenter.getManageCourseTitle(),
						JOptionPane.PLAIN_MESSAGE, HelpPresenter.getHelpIcon());
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == ManageCourseUI.this.associatedClassesTable) {
				// Gets the primary key (classNo) of the row selected in the
				// associatedClassesTable. The zero is because of the classNo is
				// the first value in the column of associatedClassesTable and
				// it is zero equivalent.
				String classNo = (String) ManageCourseUI.this.associatedClassesTable.getValueAt(
						ManageCourseUI.this.associatedClassesTable.rowAtPoint(e.getPoint()), 0);
				ManageCourseUI.this.presenter
						.setCurrentlySelectedClassFromAssociationTable(classNo);
			} else if (e.getSource() == ManageCourseUI.this.allClassesTable) {
				// Does the same as the if before this but with another table.
				String classNo = (String) ManageCourseUI.this.allClassesTable.getValueAt(
						ManageCourseUI.this.allClassesTable.rowAtPoint(e.getPoint()), 0);

				ManageCourseUI.this.presenter.setCurrectlySelectedClassFromAvailableTable(classNo);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// Empty overridden method
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// Empty overridden method
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// Empty overridden method
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// Empty overridden method
		}
	}

}
