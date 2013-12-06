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

import com.easyTeach.client.presenter.HelpPresenter;

/**
 * The ExerciseManagerUI class constructs a JPanel with all the different 
 * JComponents needed to maintain exercises (Add, edit and delete).
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 5. December, 2013
 */

public class ExerciseManagerUI  {

    private final String[] FILTER = {"Author", "Exercise Name", "Question Tag", 
            "Is Test", "Course"};
    private JPanel exerciseManagerPanel;
    private JButton btnHelp;
    private JButton btnEditExercise;
    private JButton btnDeleteExercise;
    private JButton btnAddExercise;
    private JPanel centerPanel;
    private JPanel lowerCenterPanel;
    private JTable questionTable;
    private JTable exerciseTable;
    private JPanel filterPanel;
    private JComboBox filterBox;
    private JButton btnFilter;
    private JPanel questionTablePanel;
    private JTextField txtFilter;
    
    /**
     * Constructor for building the exerciseManagerPanel. The panel is built 
     * by calling the buildPanel method. The constructor also calls a method
     * adding actionListeners to all buttons.
     */
    public ExerciseManagerUI() {
        buildPanel();
        addActionListeners();
    }
    
    /**
     * The getExerciseManagerUI returns the JPanel containing all the 
     * components needed to maintain exercises.
     * 
     * @return the JPanel, exerciseManagerPanel. Meant to be used in 
     * another frame.
     */
    public JPanel getExerciseManagerUI() {
        return exerciseManagerPanel;
    }
    
    /**
     * The method responsible for building the exerciseManagerPanel. The panel
     * consists of all the JComponenets needed to maintain exercises.
     */
    public void buildPanel() {
        exerciseManagerPanel = new JPanel(new BorderLayout());
        exerciseManagerPanel.setBackground(UIColors.lightBlue);
        
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
        
        JLabel lblManageExerciseTitle = new JLabel("Exercise Manager");
        lblManageExerciseTitle.setForeground(UIColors.white);
        lblManageExerciseTitle.setFont(new Font("Lucida Grande", Font.BOLD, 24));
        northPanel.add(lblManageExerciseTitle);

        exerciseManagerPanel.add(northPanel, BorderLayout.NORTH);
    }
    
    /**
     * Method for creating the south panel with buttons for getting help and 
     * editing/deleting/adding exercises.
     */
    private void buildSouthPanel() {
        JPanel southButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southButtonPanel.setBackground(UIColors.darkBlue);
        
        btnHelp = new JButton("Help");
        southButtonPanel.add(btnHelp);
        
        btnEditExercise = new JButton("Edit Exercise");
        southButtonPanel.add(btnEditExercise);
        
        btnDeleteExercise = new JButton("Delete Exercise");
        southButtonPanel.add(btnDeleteExercise);
        
        btnAddExercise = new JButton("Add Exercise");
        southButtonPanel.add(btnAddExercise);

        exerciseManagerPanel.add(southButtonPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Builds the center panel consisting of two inner panels.
     * One for an question table showing which exercises relate to questions
     * and another containing a panel to filter exercises.
     */
    private void buildCenterPanel() {
        centerPanel = new JPanel(new GridLayout(2, 1));
        exerciseManagerPanel.add(centerPanel, BorderLayout.CENTER);
        
        buildUpperCenterPanel();
        
        lowerCenterPanel = new JPanel(new BorderLayout());
        buildFilterPanel();
        buildQuestionTablePanel();
        
        centerPanel.add(lowerCenterPanel);
    }
    
    /**
     * Builds a panel with a table for questions for a selected exercise.
     */
    private void buildQuestionTablePanel() {
        questionTablePanel = new JPanel(new BorderLayout());
        questionTable = new JTable();

        questionTablePanel.setBackground(UIColors.lightBrown);
        questionTablePanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
                new Color(0, 0, 0), null), "Exercise's Questions", TitledBorder.CENTER, 
                TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 20)));     
        lowerCenterPanel.add(questionTablePanel, BorderLayout.CENTER);
        
        DefaultTableModel1 questionModel = new DefaultTableModel1();
        String[] questionHeads = {"Question Type", "Question", "Tags"};
        questionModel.setRowCount(1);
        questionModel.setColumnIdentifiers(questionHeads);
        questionModel.setValueAt("Multiple Choice", 0, 0);
        questionModel.setValueAt("What is the square root of -1?", 0, 1);
        questionModel.setValueAt("square-root, math", 0, 2);
        
        questionTable.setModel(questionModel);
        
        JScrollPane questionScroll = new JScrollPane(questionTable);
        questionScroll.getViewport().setBackground(UIColors.white);

        questionTablePanel.add(questionScroll);
    }
    
    /**
     * Builds the filter panel for filtering exercises.
     */
    private void buildFilterPanel() {
        filterPanel = new JPanel();
        filterPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
                new Color(0, 0, 0), null), "Filter Exercises", TitledBorder.CENTER, 
                TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 20)));
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
        lowerCenterPanel.add(filterPanel, BorderLayout.NORTH);
    }
    
    /**
     * Builds the upper center panel with a JTable for all exercises.
     */
    private void buildUpperCenterPanel() {
        JPanel upperCenterPanel = new JPanel(new BorderLayout());
        upperCenterPanel.setBackground(UIColors.lightBlue);
        upperCenterPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
                new Color(0, 0, 0), null), "Exercises", TitledBorder.CENTER, 
                TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 20)));
        
        exerciseTable = new JTable();
        DefaultTableModel1 exerciseModel = new DefaultTableModel1();
        String[] exerciseHeads = {"Course", "Author", "Exercise Name", "Is Test"};
        exerciseModel.setRowCount(1);
        exerciseModel.setColumnIdentifiers(exerciseHeads);
        exerciseModel.setValueAt("Software Construction", 0, 0);
        exerciseModel.setValueAt("John Smith", 0, 1);
        exerciseModel.setValueAt("Discrete Mathematics in Computer Science", 0, 2);
        exerciseModel.setValueAt("X", 0,  3);
        exerciseTable.setModel(exerciseModel);
        
        JScrollPane exerciseScroll = new JScrollPane(exerciseTable);
        exerciseScroll.getViewport().setBackground(UIColors.white);
        upperCenterPanel.add(exerciseScroll, BorderLayout.CENTER);

        centerPanel.add(upperCenterPanel);
    }
    
    /**
     * Adds an ActionListener of type ExerciseManagerUIListener to all the 
     * JButtons from the exerciseManagerPanel.
     */
    private void addActionListeners() {
        ExerciseManagerUIListener listener = new ExerciseManagerUIListener();
        btnAddExercise.addActionListener(listener);
        btnDeleteExercise.addActionListener(listener);
        btnEditExercise.addActionListener(listener);
        btnHelp.addActionListener(listener);
        btnFilter.addActionListener(listener);
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

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnAddExercise) {
                
            }
            
            else if (e.getSource() == btnDeleteExercise) {
                
            }
            
            else if (e.getSource() == btnEditExercise) {
                
            }
            
            else if (e.getSource() == btnHelp) {
                JOptionPane.showMessageDialog(null, HelpPresenter.getExerciseManagerHelp(), 
                        HelpPresenter.getExerciseManagerTitle(), JOptionPane.PLAIN_MESSAGE, 
                        HelpPresenter.getHelpIcon());
            }
            
            else if (e.getSource() == btnFilter) {
                
            }
        }
        
    }
    
}