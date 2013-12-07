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
 * The QuestionManagerUI class constructs a JPanel with all the different 
 * JComponents needed to maintain questions (Add, edit and delete). For every
 * 
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 5. December, 2013
 */

public class QuestionManagerUI  {

    private final String[] FILTER = {"Tag", "Points", "Question"};
    private JPanel questionManagerPanel;
    private JButton btnHelp;
    private JButton btnEditQuestion;
    private JButton btnDeleteQuestion;
    private JButton btnAddQuestion;
    private JPanel centerPanel;
    private JPanel lowerCenterPanel;
    private JTable answerTable;
    private JTable questionTable;
    private JPanel filterPanel;
    private JComboBox filterBox;
    private JButton btnFilter;
    private JPanel answerTablePanel;
    private JTextField txtFilter;
    
    /**
     * Constructor for building the questionManagerPanel. The panel is built 
     * by calling the buildPanel method. The constructor also calls a method
     * adding actionListeners to all buttons.
     */
    public QuestionManagerUI() {
        buildPanel();
        addActionListeners();
    }
    
    /**
     * The getQuestionManagerUI returns the JPanel containing all the 
     * components needed to maintain questions.
     * 
     * @return the JPanel, questionManagerPanel. Meant to be used in 
     * another frame.
     */
    public JPanel getQuestionManagerUI() {
        return questionManagerPanel;
    }
    
    /**
     * The method responsible for building the questionManagerPanel. The panel
     * consists of all the JComponenets needed to maintain questions.
     */
    public void buildPanel() {
        questionManagerPanel = new JPanel(new BorderLayout());
        questionManagerPanel.setBackground(UIColors.lightBlue);
        
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
        
        JLabel lblManageQuestionTitle = new JLabel("Question Manager");
        lblManageQuestionTitle.setForeground(UIColors.white);
        lblManageQuestionTitle.setFont(new Font("Lucida Grande", Font.BOLD, 24));
        northPanel.add(lblManageQuestionTitle);

        questionManagerPanel.add(northPanel, BorderLayout.NORTH);
    }
    
    /**
     * Method for creating the south panel with buttons for getting help and 
     * editing/deleting/adding questions.
     */
    private void buildSouthPanel() {
        JPanel southButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southButtonPanel.setBackground(UIColors.darkBlue);
        
        btnHelp = new JButton("Help");
        southButtonPanel.add(btnHelp);
        
        btnEditQuestion = new JButton("Edit Question");
        southButtonPanel.add(btnEditQuestion);
        
        btnDeleteQuestion = new JButton("Delete Question");
        southButtonPanel.add(btnDeleteQuestion);
        
        btnAddQuestion = new JButton("Add Question");
        southButtonPanel.add(btnAddQuestion);

        questionManagerPanel.add(southButtonPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Builds the center panel consisting of two inner panels.
     * One for an answer table showing which questions relate to answers
     * and another containing a panel to filter questions.
     */
    private void buildCenterPanel() {
        centerPanel = new JPanel(new GridLayout(2, 1));
        questionManagerPanel.add(centerPanel, BorderLayout.CENTER);
        
        buildUpperCenterPanel();
        
        lowerCenterPanel = new JPanel(new BorderLayout());
        buildFilterPanel();
        buildAnswerTablePanel();
        
        centerPanel.add(lowerCenterPanel);
    }
    
    /**
     * Builds a panel with a table for answers for a selected question.
     */
    private void buildAnswerTablePanel() {
        answerTablePanel = new JPanel(new BorderLayout());
        answerTable = new JTable();

        answerTablePanel.setBackground(UIColors.lightBrown);
        answerTablePanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
                new Color(0, 0, 0), null), "Question's Answers", TitledBorder.CENTER, 
                TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 20)));     
        lowerCenterPanel.add(answerTablePanel, BorderLayout.CENTER);
        
        DefaultTableModel1 answerModel = new DefaultTableModel1();
        String[] answerHeads = {"Correct", "Answer"};
        answerModel.setRowCount(1);
        answerModel.setColumnIdentifiers(answerHeads);
        answerModel.setValueAt("X", 0, 0);
        answerModel.setValueAt("42", 0, 1);
        
        answerTable.setModel(answerModel);
        
        JScrollPane answerScroll = new JScrollPane(answerTable);
        answerScroll.getViewport().setBackground(UIColors.white);

        answerTablePanel.add(answerScroll);
    }
    
    /**
     * Builds the filter panel for filtering questions.
     */
    private void buildFilterPanel() {
        filterPanel = new JPanel();
        filterPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
                new Color(0, 0, 0), null), "Filter Questions", TitledBorder.CENTER, 
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
     * Builds the upper center panel with a JTable for all questions.
     */
    private void buildUpperCenterPanel() {
        JPanel upperCenterPanel = new JPanel(new BorderLayout());
        upperCenterPanel.setBackground(UIColors.lightBlue);
        upperCenterPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
                new Color(0, 0, 0), null), "Questions", TitledBorder.CENTER, 
                TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 20)));
        
        questionTable = new JTable();
        DefaultTableModel1 questionModel = new DefaultTableModel1();
        String[] questionHeads = {"Question No", "Question Type", "Question", "Points", "Tags"};
        questionModel.setRowCount(1);
        questionModel.setColumnIdentifiers(questionHeads);
        questionModel.setValueAt("134", 0, 0);
        questionModel.setValueAt("Multiple Choice", 0, 1);
        questionModel.setValueAt("What is the meaning of life?", 0, 2);
        questionModel.setValueAt("1337", 0, 3);
        questionModel.setValueAt("meaning, life", 0, 4);
        questionTable.setModel(questionModel);
        
        JScrollPane questionScroll = new JScrollPane(questionTable);
        questionScroll.getViewport().setBackground(UIColors.white);
        upperCenterPanel.add(questionScroll, BorderLayout.CENTER);

        centerPanel.add(upperCenterPanel);
    }
    
    /**
     * Adds an ActionListener of type QuestionManagerUIListener to all the 
     * JButtons from the exerciseManagerPanel.
     */
    private void addActionListeners() {
        QuestionManagerUIListener listener = new QuestionManagerUIListener();
        btnAddQuestion.addActionListener(listener);
        btnDeleteQuestion.addActionListener(listener);
        btnEditQuestion.addActionListener(listener);
        btnHelp.addActionListener(listener);
        btnFilter.addActionListener(listener);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    
    /**
     * The QuestionManagerUIListener class is the class in charge of listening
     * for events happening in the QuestionManagerUI (e.g. an user clicking the 
     * help button). When an event occurs the QuestionManagerUIListener will 
     * send a signal to the QuestionManagerPresenter which will in return act 
     * upon the event.
     * 
     * @author Morten Faarkrog
     * @version 0.1
     * @see ActionListener
     * @see QuestionManagerPresenter
     * @date 5. December, 2013
     */
    private class QuestionManagerUIListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnAddQuestion) {
                
            }
            
            else if (e.getSource() == btnDeleteQuestion) {
                
            }
            
            else if (e.getSource() == btnEditQuestion) {
                
            }
            
            else if (e.getSource() == btnHelp) {
                JOptionPane.showMessageDialog(null, HelpPresenter.getQuestionManagerHelp(), 
                        HelpPresenter.getQuestionManagerTitle(), JOptionPane.PLAIN_MESSAGE, 
                        HelpPresenter.getHelpIcon());
            }
            
            else if (e.getSource() == btnFilter) {
                
            }
        }
        
    }
    
}