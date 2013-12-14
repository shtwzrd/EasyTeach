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
import com.easyTeach.client.presenter.HelpPresenter;
import com.easyTeach.client.presenter.QuestionManagerPresenter;
import com.easyTeach.common.ui.UIColors;

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
    JButton btnHelp;
    JButton btnEditQuestion;
    JButton btnDeleteQuestion;
    JButton btnAddQuestion;
    private JPanel centerPanel;
    private JPanel lowerCenterPanel;
    JTable answerTable;
    JTable questionTable;
    private JPanel filterPanel;
    JComboBox<String> filterBox;
    JButton btnFilter;
    private JPanel answerTablePanel;
    JTextField txtFilter;
    
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
        return this.questionManagerPanel;
    }
    
    /**
     * The method responsible for building the questionManagerPanel. The panel
     * consists of all the JComponenets needed to maintain questions.
     */
    public void buildPanel() {
        this.questionManagerPanel = new JPanel(new BorderLayout());
        this.questionManagerPanel.setBackground(UIColors.lightBlue);
        
        buildCenterPanel();
        buildSouthPanel();               
    }
    
    /**
     * Method for creating the south panel with buttons for getting help and 
     * editing/deleting/adding questions.
     */
    private void buildSouthPanel() {
        JPanel southButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southButtonPanel.setBackground(UIColors.darkBlue);
        
        this.btnHelp = new JButton("Help");
        southButtonPanel.add(this.btnHelp);
        
        this.btnEditQuestion = new JButton("Edit Question");
        southButtonPanel.add(this.btnEditQuestion);
        
        this.btnDeleteQuestion = new JButton("Delete Question");
        southButtonPanel.add(this.btnDeleteQuestion);
        
        this.btnAddQuestion = new JButton("Add Question");
        southButtonPanel.add(this.btnAddQuestion);

        this.questionManagerPanel.add(southButtonPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Builds the center panel consisting of two inner panels.
     * One for an answer table showing which questions relate to answers
     * and another containing a panel to filter questions.
     */
    private void buildCenterPanel() {
        this.centerPanel = new JPanel(new GridLayout(2, 1));
        this.questionManagerPanel.add(this.centerPanel, BorderLayout.CENTER);
        
        buildUpperCenterPanel();
        
        this.lowerCenterPanel = new JPanel(new BorderLayout());
        buildFilterPanel();
        buildAnswerTablePanel();
        
        this.centerPanel.add(this.lowerCenterPanel);
    }
    
    /**
     * Builds a panel with a table for answers for a selected question.
     */
    private void buildAnswerTablePanel() {
        this.answerTablePanel = new JPanel(new BorderLayout());
        this.answerTable = new JTable();

        this.answerTablePanel.setBackground(UIColors.lightBrown);
        this.answerTablePanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
                new Color(0, 0, 0), null), "Question's Answers", TitledBorder.CENTER, 
                TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 20)));     
        this.lowerCenterPanel.add(this.answerTablePanel, BorderLayout.CENTER);
        
        DefaultTableModel answerModel = new DefaultTableModel();
        String[] answerHeads = {"Correct", "Answer"};
        answerModel.setRowCount(1);
        answerModel.setColumnIdentifiers(answerHeads);
        answerModel.setValueAt("X", 0, 0);
        answerModel.setValueAt("42", 0, 1);
        
        this.answerTable.setModel(answerModel);
        
        JScrollPane answerScroll = new JScrollPane(this.answerTable);
        answerScroll.getViewport().setBackground(UIColors.white);

        this.answerTablePanel.add(answerScroll);
    }
    
    /**
     * Builds the filter panel for filtering questions.
     */
    private void buildFilterPanel() {
        this.filterPanel = new JPanel();
        this.filterPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
                new Color(0, 0, 0), null), "Filter Questions", TitledBorder.CENTER, 
                TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 20)));
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
     * Builds the upper center panel with a JTable for all questions.
     */
    private void buildUpperCenterPanel() {
        JPanel upperCenterPanel = new JPanel(new BorderLayout());
        upperCenterPanel.setBackground(UIColors.lightBlue);
        upperCenterPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
                new Color(0, 0, 0), null), "Questions", TitledBorder.CENTER, 
                TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 20)));
        
        this.questionTable = new JTable();
        DefaultTableModel questionModel = new DefaultTableModel();
        String[] questionHeads = {"Question No", "Question Type", "Question", "Points", "Tags"};
        questionModel.setRowCount(1);
        questionModel.setColumnIdentifiers(questionHeads);
        questionModel.setValueAt("134", 0, 0);
        questionModel.setValueAt("Multiple Choice", 0, 1);
        questionModel.setValueAt("What is the meaning of life?", 0, 2);
        questionModel.setValueAt("1337", 0, 3);
        questionModel.setValueAt("meaning, life", 0, 4);
        this.questionTable.setModel(questionModel);
        
        JScrollPane questionScroll = new JScrollPane(this.questionTable);
        questionScroll.getViewport().setBackground(UIColors.white);
        upperCenterPanel.add(questionScroll, BorderLayout.CENTER);

        this.centerPanel.add(upperCenterPanel);
    }
    
    /**
     * Adds an ActionListener of type QuestionManagerUIListener to all the 
     * JButtons from the exerciseManagerPanel.
     */
    private void addActionListeners() {
        QuestionManagerUIListener listener = new QuestionManagerUIListener();
        this.btnAddQuestion.addActionListener(listener);
        this.btnDeleteQuestion.addActionListener(listener);
        this.btnEditQuestion.addActionListener(listener);
        this.btnHelp.addActionListener(listener);
        this.btnFilter.addActionListener(listener);
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

        public QuestionManagerUIListener() {
			// Empty Constructor 
		}

		@Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == QuestionManagerUI.this.btnAddQuestion) {
                
            }
            
            else if (e.getSource() == QuestionManagerUI.this.btnDeleteQuestion) {
                
            }
            
            else if (e.getSource() == QuestionManagerUI.this.btnEditQuestion) {
                
            }
            
            else if (e.getSource() == QuestionManagerUI.this.btnHelp) {
                JOptionPane.showMessageDialog(null, HelpPresenter.getQuestionManagerHelp(), 
                        HelpPresenter.getQuestionManagerTitle(), JOptionPane.PLAIN_MESSAGE, 
                        HelpPresenter.getHelpIcon());
            }
            
            else if (e.getSource() == QuestionManagerUI.this.btnFilter) {
                
            }
        }
        
    }
    
}