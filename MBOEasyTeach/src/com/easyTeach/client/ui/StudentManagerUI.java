package com.easyTeach.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.easyTeach.client.presenter.HelpPresenter;
import com.easyTeach.common.ui.UIColors;

/**
 * The StudentManagerUI class constructs a JPanel with all the different
 * JComponents needed for students to browse exercises and review their
 * progress.
 * 
 * @author Morten Faarkrog
 * @version 1.1
 * @date 8. December, 2013
 */

public class StudentManagerUI {

    private static final Object[] FILTER_QUIZZES = { "Exercise Name", "Tags",
            "Completed (t/f)", "Date Added" };
    private static final Object[] FILTER_COURSES = { "All", "Course 1",
            "Course 2" };
    private JPanel studentManagerPanel;
    private JTabbedPane tabPanel;
    private JTabbedPane exerciseTabPanel;
    private JTable availableTestsTable;
    private JButton quizzesFilter;
    private JTextField quizzesTxt;
    private JComboBox<String> quizzesCombo;
    private JComboBox<String> courseQuizzesCombo;
    private JButton btnHelp;
    private JButton btnTakeExercise;
    private JTable lockedTestsTable;

    /**
     * Constructor for building the StudentManagerUI. The panel is built by
     * calling the buildPanel method.
     */
    public StudentManagerUI() {
        buildPanel();
        addActionListeners();
    }

    /**
     * The getStudentManagerUI returns the JPanel containing all the components
     * needed for students to browse exercises and review their progress.
     * 
     * @return the JPanel, StudentManagerUI. Meant to be used in another frame.
     */
    public JPanel getStudentManagerUI() {
        return this.studentManagerPanel;
    }

    /**
     * The method responsible for building the teacherManagerPanel. The panel
     * consists of all the JComponenets needed for students to browse exercises
     * and review their progress.
     */
    public void buildPanel() {
        this.studentManagerPanel = new JPanel(new BorderLayout());
        this.studentManagerPanel.setBackground(UIColors.lightBlue);

        buildNorthPanel();
        buildCenterPanel();
    }

    /**
     * Builds the center panel with the tabbed panels for managing exercises,
     * quizzes and reviewing classes' and users' progress.
     */
    private void buildCenterPanel() {
        this.tabPanel = new JTabbedPane(JTabbedPane.TOP);

        JPanel exercisePanel = new JPanel(new BorderLayout());
        exercisePanel.setBackground(UIColors.lightBlue);
        this.exerciseTabPanel = new JTabbedPane(JTabbedPane.TOP);

        // Adding tabs to the exerciseTabPanel
        buildQuizzesPanel();
        buildAvailableTestsPanel();
        buildLockedTestsPanel();

        exercisePanel.add(this.exerciseTabPanel, BorderLayout.CENTER);

        JPanel southButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southButtonPanel.setBackground(UIColors.darkBlue);

        this.btnHelp = new JButton("Help");
        southButtonPanel.add(this.btnHelp);

        this.btnTakeExercise = new JButton("Take Exercise");
        southButtonPanel.add(this.btnTakeExercise);

        exercisePanel.add(southButtonPanel, BorderLayout.SOUTH);

        this.tabPanel.add("Exercises", exercisePanel);
        this.tabPanel.add("Progress", new JPanel());

        this.studentManagerPanel.add(this.tabPanel, BorderLayout.CENTER);
        this.studentManagerPanel.setBackground(UIColors.darkBlue);
    }

    /**
     * Builds the north panel with the the headline of the UI.
     */
    private void buildNorthPanel() {
        JPanel northPanel = new JPanel();
        northPanel.setBackground(UIColors.darkBlue);

        JLabel lblStudentManagerTitle = new JLabel("Student Manager");
        lblStudentManagerTitle.setForeground(UIColors.white);
        lblStudentManagerTitle
                .setFont(new Font("Lucida Grande", Font.BOLD, 24));
        northPanel.add(lblStudentManagerTitle);

        this.studentManagerPanel.add(northPanel, BorderLayout.NORTH);
    }

    /**
     * Method for building the Quizzes Panel tab for the Exercises tab. The
     * Quizzes Panel consists of a JTable with all Quizzes along with a
     * filtering function for the table.
     */
    private void buildQuizzesPanel() {
        JPanel quizzesPanel = new JPanel(new BorderLayout());
        quizzesPanel.setBackground(UIColors.lightBlue);

        this.availableTestsTable = new JTable();
        DefaultTableModel quizzesModel = new DefaultTableModel();
        String[] quizzesHeads = { "Exercise Name", "Tags", "Completed",
                "Date Added" };
        quizzesModel.setRowCount(1);
        quizzesModel.setColumnIdentifiers(quizzesHeads);
        quizzesModel.setValueAt("Polymorphism 101", 0, 0);
        quizzesModel.setValueAt(
                "superclass, subclass, polymorphism, inheritance", 0, 1);
        quizzesModel.setValueAt("X", 0, 2);
        quizzesModel.setValueAt("01-20-2013", 0, 3);
        this.availableTestsTable.setModel(quizzesModel);

        JScrollPane quizzesScroll = new JScrollPane(this.availableTestsTable);
        quizzesScroll.getViewport().setBackground(UIColors.white);
        quizzesPanel.add(quizzesScroll, BorderLayout.CENTER);

        JPanel filterPanel = new JPanel(new BorderLayout());

        // Panel for course filter
        JPanel courseFilterPanel = new JPanel();
        courseFilterPanel.setBackground(UIColors.lightBlue);

        courseFilterPanel.setBorder(new TitledBorder(new EtchedBorder(
                EtchedBorder.LOWERED, new Color(0, 0, 0), null), "Courses",
                TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma",
                        Font.PLAIN, 20)));
        this.courseQuizzesCombo = new JComboBox<>();
        this.courseQuizzesCombo.setModel(new DefaultComboBoxModel(FILTER_COURSES));
        courseFilterPanel.add(this.courseQuizzesCombo);
        filterPanel.add(courseFilterPanel, BorderLayout.WEST);

        // Table filter (minus course filter)
        JPanel tableFilterPanel = new JPanel();
        tableFilterPanel.setBackground(UIColors.lightBlue);
        this.quizzesFilter = new JButton("Filter");
        this.quizzesTxt = new JTextField(14);
        this.quizzesCombo = new JComboBox<>();
        this.quizzesCombo.setModel(new DefaultComboBoxModel(FILTER_QUIZZES));

        tableFilterPanel.add(new JLabel("Filer"));
        tableFilterPanel.add(this.quizzesCombo);
        tableFilterPanel.add(new JLabel("by"));
        tableFilterPanel.add(this.quizzesTxt);
        tableFilterPanel.add(this.quizzesFilter);

        tableFilterPanel.setBorder(new TitledBorder(new EtchedBorder(
                EtchedBorder.LOWERED, new Color(0, 0, 0), null), "Filter",
                TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma",
                        Font.PLAIN, 20)));
        filterPanel.add(tableFilterPanel, BorderLayout.CENTER);

        quizzesPanel.add(filterPanel, BorderLayout.SOUTH);

        this.exerciseTabPanel.add("Quizzes", quizzesPanel);
    }

    /**
     * Method for building the AvailableTestsPanel where students can browse
     * through the tests that are currently available to them.
     */
    public void buildAvailableTestsPanel() {
        JPanel availableTestsPanel = new JPanel(new BorderLayout());
        availableTestsPanel.setBackground(UIColors.lightBlue);

        this.availableTestsTable = new JTable();
        DefaultTableModel availableTestsModel = new DefaultTableModel();
        String[] availableTestsHeads = { "Exercise Name", "Tags", "Completed",
                "Date Added" };
        availableTestsModel.setRowCount(1);
        availableTestsModel.setColumnIdentifiers(availableTestsHeads);
        availableTestsModel.setValueAt("Polymorphism 101", 0, 0);
        availableTestsModel.setValueAt(
                "superclass, subclass, polymorphism, inheritance", 0, 1);
        availableTestsModel.setValueAt("X", 0, 2);
        availableTestsModel.setValueAt("01-20-2013", 0, 3);
        this.availableTestsTable.setModel(availableTestsModel);

        JScrollPane availableTestsScroll = new JScrollPane(this.availableTestsTable);
        availableTestsScroll.getViewport().setBackground(UIColors.white);
        availableTestsPanel.add(availableTestsScroll, BorderLayout.CENTER);

        this.exerciseTabPanel.add("Available Tests", availableTestsPanel);
    }

    /**
     * Method for building the LockedTestsPanel where students can browse
     * through the tests that are not currently available to them.
     */
    public void buildLockedTestsPanel() {
        JPanel lockedTestsPanel = new JPanel(new BorderLayout());
        lockedTestsPanel.setBackground(UIColors.lightBlue);

        this.lockedTestsTable = new JTable();
        DefaultTableModel lockedTestsModel = new DefaultTableModel();
        String[] lockedTestsHeads = { "Exercise Name", "Tags", "Completed",
                "Date Added" };
        lockedTestsModel.setRowCount(1);
        lockedTestsModel.setColumnIdentifiers(lockedTestsHeads);
        lockedTestsModel.setValueAt("Polymorphism 101", 0, 0);
        lockedTestsModel.setValueAt(
                "superclass, subclass, polymorphism, inheritance", 0, 1);
        lockedTestsModel.setValueAt("X", 0, 2);
        lockedTestsModel.setValueAt("01-20-2013", 0, 3);
        this.lockedTestsTable.setModel(lockedTestsModel);

        JScrollPane lockedTestsScroll = new JScrollPane(this.lockedTestsTable);
        lockedTestsScroll.getViewport().setBackground(UIColors.white);
        lockedTestsPanel.add(lockedTestsScroll, BorderLayout.CENTER);

        this.exerciseTabPanel.add("Locked Tests", lockedTestsPanel);
    }

    /**
     * Adds an ActionListener of type AdminManagerUIListener to all the JButtons
     * from the adminManagerPanel.
     */
    private void addActionListeners() {
        StudentManagerUIListener listener = new StudentManagerUIListener();
        this.quizzesFilter.addActionListener(listener);
        this.btnTakeExercise.addActionListener(listener);
        this.btnHelp.addActionListener(listener);
        this.courseQuizzesCombo.addActionListener(listener);;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    /**
     * The StudentManagerUIListener class is the class in charge of listening
     * for events happening in the StudentManagerUI (e.g. an user clicking the
     * help button). When an event occurs the StudentManagerUIListener will send
     * a signal to the StudentManagerPresenter which will in return act upon the
     * event.
     * 
     * @author Morten Faarkrog
     * @version 0.1
     * @see ActionListener
     * @date 8. December, 2013
     */
    private class StudentManagerUIListener implements ActionListener {

        public StudentManagerUIListener() {
			// Empty Constructor 
		}

		@Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == StudentManagerUI.this.btnTakeExercise) {

            }

            else if (e.getSource() == StudentManagerUI.this.courseQuizzesCombo) {

            }

            else if (e.getSource() == StudentManagerUI.this.btnHelp) {
                JOptionPane.showMessageDialog(null,
                        HelpPresenter.getStudentManagerHelp(),
                        HelpPresenter.getStudentManagerTitle(),
                        JOptionPane.PLAIN_MESSAGE, HelpPresenter.getHelpIcon());
            }

            else if (e.getSource() == StudentManagerUI.this.quizzesFilter) {
            }
        }

    }

}