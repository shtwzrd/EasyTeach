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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.easyTeach.client.presenter.DisplayTableModel;
import com.easyTeach.client.presenter.ExerciseManagerPresenter;
import com.easyTeach.client.presenter.HelpPresenter;
import com.easyTeach.common.ui.UIColors;

/**
 * The ExerciseManagerUI class constructs a JPanel with all the different
 * JComponents needed to maintain exercises (Add, edit and delete).
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 5. December, 2013
 */

public class ExerciseManagerUI {

    private final String[] FILTER = { "Author", "Exercise Name",
            "Question Tag", "Is Test", "Course" };
    private JPanel exerciseManagerPanel;
    JButton btnHelp;
    JButton btnEditExercise;
    JButton btnDeleteExercise;
    JButton btnAddExercise;
    JPanel centerPanel;
    JPanel lowerCenterPanel;
    JTable questionTable;
    JTable exerciseTable;
    JPanel filterPanel;
    JComboBox<String> filterBox;
    JButton btnFilter;
    JPanel questionTablePanel;
    JTextField txtFilter;
    JButton btnDuplicateExercise;

    /**
     * Constructor for building the exerciseManagerPanel. The panel is built by
     * calling the buildPanel method. The constructor also calls a method adding
     * actionListeners to all buttons.
     */
    public ExerciseManagerUI() {
        buildPanel();
        addActionListeners();
    }

    /**
     * The getExerciseManagerUI returns the JPanel containing all the components
     * needed to maintain exercises.
     * 
     * @return the JPanel, exerciseManagerPanel. Meant to be used in another
     *         frame.
     */
    public JPanel getExerciseManagerUI() {
        return this.exerciseManagerPanel;
    }

    /**
     * The method responsible for building the exerciseManagerPanel. The panel
     * consists of all the JComponenets needed to maintain exercises.
     */
    public void buildPanel() {
        this.exerciseManagerPanel = new JPanel(new BorderLayout());
        this.exerciseManagerPanel.setBackground(UIColors.lightBlue);

        buildCenterPanel();
        buildSouthPanel();
    }

    /**
     * Method for creating the south panel with buttons for getting help and
     * editing/deleting/adding exercises.
     */
    private void buildSouthPanel() {
        JPanel southButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southButtonPanel.setBackground(UIColors.darkBlue);

        this.btnHelp = new JButton("Help");
        southButtonPanel.add(this.btnHelp);

        this.btnEditExercise = new JButton("Edit Exercise");
        southButtonPanel.add(this.btnEditExercise);

        this.btnDuplicateExercise = new JButton("Duplicate Exercise");
        southButtonPanel.add(this.btnDuplicateExercise);
        
        this.btnDeleteExercise = new JButton("Delete Exercise");
        southButtonPanel.add(this.btnDeleteExercise);

        this.btnAddExercise = new JButton("Add Exercise");
        southButtonPanel.add(this.btnAddExercise);

        this.exerciseManagerPanel.add(southButtonPanel, BorderLayout.SOUTH);
    }

    /**
     * Builds the center panel consisting of two inner panels. One for an
     * question table showing which exercises relate to questions and another
     * containing a panel to filter exercises.
     */
    private void buildCenterPanel() {
        this.centerPanel = new JPanel(new GridLayout(2, 1));
        this.exerciseManagerPanel.add(this.centerPanel, BorderLayout.CENTER);

        buildUpperCenterPanel();

        this.lowerCenterPanel = new JPanel(new BorderLayout());
        buildFilterPanel();
        buildQuestionTablePanel();

        this.centerPanel.add(this.lowerCenterPanel);
    }

    /**
     * Builds a panel with a table for questions for a selected exercise.
     */
    private void buildQuestionTablePanel() {
        this.questionTablePanel = new JPanel(new BorderLayout());
        this.questionTable = new JTable();

        this.questionTablePanel.setBackground(UIColors.lightBrown);
        this.questionTablePanel.setBorder(new TitledBorder(new EtchedBorder(
                EtchedBorder.LOWERED, new Color(0, 0, 0), null),
                "Exercise's Questions", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Tahoma", Font.PLAIN, 20)));
        this.lowerCenterPanel.add(this.questionTablePanel, BorderLayout.CENTER);

        DefaultTableModel questionModel = new DefaultTableModel();
        String[] questionHeads = { "Question Type", "Question", "Tags" };
        questionModel.setRowCount(1);
        questionModel.setColumnIdentifiers(questionHeads);
        questionModel.setValueAt("Multiple Choice", 0, 0);
        questionModel.setValueAt("What is the square root of -1?", 0, 1);
        questionModel.setValueAt("square-root, math", 0, 2);

        this.questionTable.setModel(questionModel);

        JScrollPane questionScroll = new JScrollPane(this.questionTable);
        questionScroll.getViewport().setBackground(UIColors.white);

        this.questionTablePanel.add(questionScroll);
    }

    /**
     * Builds the filter panel for filtering exercises.
     */
    private void buildFilterPanel() {
        this.filterPanel = new JPanel();
        this.filterPanel.setBorder(new TitledBorder(new EtchedBorder(
                EtchedBorder.LOWERED, new Color(0, 0, 0), null),
                "Filter Exercises", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Tahoma", Font.PLAIN, 20)));
        this.filterPanel.setBackground(UIColors.lightBlue);

        JLabel lblFilter = new JLabel("Filter");
        this.filterPanel.add(lblFilter);

        this.filterBox = new JComboBox<>();
        this.filterBox.setModel(new DefaultComboBoxModel<>(this.FILTER));
        this.filterPanel.add(this.filterBox);

        JLabel lblBy = new JLabel("by");
        this.filterPanel.add(lblBy);

        this.txtFilter = new JTextField();
        this.filterPanel.add(this.txtFilter);
        this.txtFilter.setColumns(14);

        this.btnFilter = new JButton("Filter");
        this.filterPanel.add(this.btnFilter);
        this.lowerCenterPanel.add(this.filterPanel, BorderLayout.NORTH);
    }

    /**
     * Builds the upper center panel with a JTable for all exercises.
     */
    private void buildUpperCenterPanel() {
        JPanel upperCenterPanel = new JPanel(new BorderLayout());
        upperCenterPanel.setBackground(UIColors.lightBlue);
        upperCenterPanel.setBorder(new TitledBorder(new EtchedBorder(
                EtchedBorder.LOWERED, new Color(0, 0, 0), null), "Exercises",
                TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma",
                        Font.PLAIN, 20)));

        this.exerciseTable = new JTable();
        DefaultTableModel exerciseModel = new DefaultTableModel();
        String[] exerciseHeads = { "Course", "Author", "Exercise Name",
                "Is Test" };
        exerciseModel.setRowCount(1);
        exerciseModel.setColumnIdentifiers(exerciseHeads);
        exerciseModel.setValueAt("Software Construction", 0, 0);
        exerciseModel.setValueAt("John Smith", 0, 1);
        exerciseModel.setValueAt("Discrete Mathematics in Computer Science", 0,
                2);
        exerciseModel.setValueAt("X", 0, 3);
        this.exerciseTable.setModel(exerciseModel);

        JScrollPane exerciseScroll = new JScrollPane(this.exerciseTable);
        exerciseScroll.getViewport().setBackground(UIColors.white);
        upperCenterPanel.add(exerciseScroll, BorderLayout.CENTER);

        this.centerPanel.add(upperCenterPanel);
    }

    /**
     * Adds an ActionListener of type ExerciseManagerUIListener to all the
     * JButtons from the exerciseManagerPanel.
     */
    private void addActionListeners() {
        ExerciseManagerUIListener listener = new ExerciseManagerUIListener();
        this.btnAddExercise.addActionListener(listener);
        this.btnDeleteExercise.addActionListener(listener);
        this.btnEditExercise.addActionListener(listener);
        this.btnHelp.addActionListener(listener);
        this.btnFilter.addActionListener(listener);
        this.btnDuplicateExercise.addActionListener(listener);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    /**
     * The ExerciseManagerUIListener class is the class in charge of listening
     * for events happening in the ExerciseManagerUI (e.g. an user clicking the
     * help button). When an event occurs the ExerciseManagerUIListener will
     * send a signal to the ExerciseManagerPresenter which will in return act
     * upon the event.
     * 
     * @author Morten Faarkrog
     * @version 0.1
     * @see ActionListener
     * @see ExerciseManagerPresenter
     * @date 5. December, 2013
     */
    private class ExerciseManagerUIListener implements ActionListener {

        public ExerciseManagerUIListener() {
			// Empty Constructor 
		}

		@Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ExerciseManagerUI.this.btnAddExercise) {
                MainFrame.updateFrame(
                        new ManageExerciseInfoUI().getManageExerciseInfoUI(),
                        "Manage Exercise Info");
            }

            else if (e.getSource() == ExerciseManagerUI.this.btnDeleteExercise) {

            }

            else if (e.getSource() == ExerciseManagerUI.this.btnEditExercise) {

            }

            else if (e.getSource() == ExerciseManagerUI.this.btnHelp) {
                JOptionPane.showMessageDialog(null,
                        HelpPresenter.getExerciseManagerHelp(),
                        HelpPresenter.getExerciseManagerTitle(),
                        JOptionPane.PLAIN_MESSAGE, HelpPresenter.getHelpIcon());
            }

            else if (e.getSource() == ExerciseManagerUI.this.btnFilter) {

            }
            
            else if (e.getSource() == ExerciseManagerUI.this.btnDuplicateExercise) {
                
            }
        }

    }

}