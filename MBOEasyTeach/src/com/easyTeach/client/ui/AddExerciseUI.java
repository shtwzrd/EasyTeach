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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.easyTeach.client.presenter.HelpPresenter;

/**
 * The AddExerciseUI class constructs a JPanel with all the different 
 * JComponents needed to constructor a new Exercise with a set of questions.
 * Moreover, for all Questions Tags can be created.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 4. December, 2013
 */

public class AddExerciseUI {

    private JPanel addExercisePanel;
    private JPanel centerPanel;
    private JPanel infoPanel;
    private JTable table;
    private JLabel lblExerciseTitle;
    private final String[] QUESTION_TYPES = {"Multiple Choice", "True/False", "Missing Word"};
    private JTextArea questionArea;
    private JComboBox questionTypeBox;
    private JComboBox comboBox;
    private JTextField txtNewTag;
    private JTextField txtExistingTag;
    private JButton btnSubmit;
    private JButton btnHelp;
    private JButton btnDiscard;
    private JButton btnResetQuestion;
    private JButton btnAddExistingQuestion;
    private JButton btnAddQuestion;
    private JButton btnToggleCorrect;
    private JButton btnRemoveAnswer;
    private JButton btnAddAnswer;
    private JButton btnRemoveTag;
    private JButton btnAddExistingTag;
    private JButton btnPrevQuestion;
    private JButton btnNextQuestion;
    private JButton btnNewTag;
    private JButton btnFindExistingTag;

    /**
     * Constructor for building the addExercisePanel. The panel is built by 
     * calling the buildPanel method. The constructor also calls a method
     * adding actionListeners to all buttons.
     */
    public AddExerciseUI() {
        buildPanel();
        addActionListeners();
    }
    
    /**
     * The getAddExerciseUI returns the JPanel containing all the components
     * needed to constructor a new Exercise with Questions and Tags.
     * 
     * @return the JPanel, addExercisePanel. Meant to be used in another frame.
     */
    public JPanel getAddExerciseUI() {
        return addExercisePanel;
    }
    
    /**
     * The method responsible for building the addExercisePanel. The panel
     * consists of all the JComponenets needed to constructor new Exercises.
     */
    private void buildPanel() {
        addExercisePanel = new JPanel();
        addExercisePanel.setLayout(new BorderLayout());
        
        buildNorthPanel();
        buildCenterPanel();
        buildSouthPanel();   
    }
    
    /**
     * Method for creating the Navigation panel for going to the next or 
     * the previous question. This will make it easier to change previously
     * defined questions if one wants to do this. 
     */
    private void buildQuestionNavPanel() {
        JPanel questionNavPanel = new JPanel();
        questionNavPanel.setBackground(UIColors.lightBlue);
        questionNavPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
                new Color(0, 0, 0), null), "Navigation", TitledBorder.CENTER, TitledBorder.TOP, 
                new Font("Tahoma", Font.PLAIN, 20)));
        centerPanel.add(questionNavPanel, BorderLayout.NORTH);
        questionNavPanel.setLayout(new GridLayout(1, 2));
        
        btnPrevQuestion = new JButton("<< Previous Question <<");
        btnPrevQuestion.setFont(new Font("Arial", Font.BOLD, 16));
        questionNavPanel.add(btnPrevQuestion);
        
        btnNextQuestion = new JButton(">> Next Question >>");
        btnNextQuestion.setFont(new Font("Arial", Font.BOLD, 16));
        questionNavPanel.add(btnNextQuestion);
    }
    
    /**
     * Method for building the infoPanel containing calls to create the panels 
     * where the question's type and tags are created.
     */
    private void buildInfoPanel() {
        infoPanel = new JPanel();
        centerPanel.add(infoPanel, BorderLayout.WEST);
        infoPanel.setLayout(new BorderLayout());
        
        buildQuestionTypePanel();
        buildTagPanel();
    }

    /**
     * Method for creating the questiobTypePanel where one selects the 
     * question type
     */
    private void buildQuestionTypePanel() {
        JPanel questionTypePanel = new JPanel();
        questionTypePanel.setBackground(UIColors.lightBlue);
        questionTypePanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
                new Color(0, 0, 0), null), "Question Type", TitledBorder.CENTER, 
                TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 20)));
        infoPanel.add(questionTypePanel, BorderLayout.NORTH);
        
        // JComboBox with the various question types one can pick
        questionTypeBox = new JComboBox();
        questionTypeBox.setModel(new DefaultComboBoxModel(QUESTION_TYPES));
        questionTypePanel.add(questionTypeBox);
        
        infoPanel.add(questionTypePanel, BorderLayout.NORTH);
    }

    /**
     * Method for building the panel for maintaining the tags for a question.
     * New tags can be created, existing tags can be browsed, and tags for a 
     * question can be removed.
     */
    private void buildTagPanel() {
        JPanel tagPanel = new JPanel();
        tagPanel.setBackground(UIColors.lightBlue);
        tagPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
                new Color(0, 0, 0), null), "Tags", TitledBorder.CENTER, 
                TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 20)));
        infoPanel.add(tagPanel, BorderLayout.CENTER);
        tagPanel.setLayout(new GridLayout(3, 1));
        
        // Panel for browsing the tags for the question one is working on
        JPanel questionTagPanel = new JPanel();
        questionTagPanel.setBackground(UIColors.lightBlue);
        questionTagPanel.setBorder(new TitledBorder(null, "Question Tags", 
                TitledBorder.CENTER, TitledBorder.TOP));
        tagPanel.add(questionTagPanel);
        questionTagPanel.setLayout(new GridLayout(5, 1));
        
        // JComboBox with all the current tags for a question
        comboBox = new JComboBox();
        questionTagPanel.add(comboBox);
        
        btnRemoveTag = new JButton("Remove Tag");
        questionTagPanel.add(btnRemoveTag);
        
        // Panel for selecting tags previously used in other questions
        JPanel existingTagPanel = new JPanel();
        existingTagPanel.setBackground(UIColors.lightBlue);
        existingTagPanel.setBorder(new TitledBorder(null, "Add Existing Tag", 
                TitledBorder.CENTER, TitledBorder.TOP));
        tagPanel.add(existingTagPanel);
        existingTagPanel.setLayout(new GridLayout(5, 1));
        
        btnFindExistingTag = new JButton("Find Existing Tag");
        existingTagPanel.add(btnFindExistingTag);
        
        txtExistingTag = new JTextField();
        txtExistingTag.setEditable(false);
        existingTagPanel.add(txtExistingTag);
        txtExistingTag.setColumns(10);
        
        btnAddExistingTag = new JButton("Add Existing Tag");
        existingTagPanel.add(btnAddExistingTag);
        
        // Panel for creating new tags to the question
        JPanel newTagPanel = new JPanel();
        newTagPanel.setBorder(new TitledBorder(null, "Add New Tag", 
                TitledBorder.CENTER, TitledBorder.TOP));
        newTagPanel.setBackground(UIColors.lightBlue);
        tagPanel.add(newTagPanel);
        newTagPanel.setLayout(new GridLayout(5, 1));
        
        txtNewTag = new JTextField();
        newTagPanel.add(txtNewTag);
        txtNewTag.setColumns(10);
        
        btnNewTag = new JButton("Add New Tag");
        newTagPanel.add(btnNewTag);
    }

    /**
     * Method for creating the north panel containing the exercise's name and
     * what question one is currently at.
     */
    private void buildNorthPanel() {
        JPanel northPanel = new JPanel();
        northPanel.setBackground(UIColors.darkBlue);
        northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        addExercisePanel.add(northPanel, BorderLayout.NORTH);
        
        lblExerciseTitle = new JLabel("Exercise - Name Goes Here - Question #1");
        lblExerciseTitle.setForeground(UIColors.white);
        lblExerciseTitle.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        northPanel.add(lblExerciseTitle);
    }
    
    /**
     * Method for creating the south panel with buttons for saving and 
     * discarding an exercise along with a help button.
     */
    private void buildSouthPanel() {
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.setBackground(UIColors.darkBlue);
        southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        addExercisePanel.add(southPanel, BorderLayout.SOUTH);
        
        btnSubmit = new JButton("Submit");
        
        btnHelp = new JButton("Help");
        southPanel.add(btnHelp);
        
        btnDiscard = new JButton("Discard");
        southPanel.add(btnDiscard);
        southPanel.add(btnSubmit);
    }

    /**
     * Method for creating the center panel. The center panel contains 
     * other panels for Navigating the next/prev question, selecting Question Type, 
     * creating Tags and creating the actual Question. These panels will be created 
     * calling other methods.
     */
    private void buildCenterPanel() {
        // Center panel with panels for Question Type, Tags and Question
        centerPanel = new JPanel();
        addExercisePanel.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new BorderLayout());
        
        buildQuestionNavPanel();
        buildInfoPanel();
        buildQuestionPanel();
    }

    /**
     * Method for creating the questionPanel where a user can define a 
     * question and its matching answers. Moreover, the user will also
     * have the option of saving the question or resetting it. 
     */
    private void buildQuestionPanel() {
        JPanel questionPanel = new JPanel();
        questionPanel.setBackground(UIColors.lightBrown);
        questionPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
                new Color(0, 0, 0), null), "Question", TitledBorder.CENTER, 
                TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 20)));
        centerPanel.add(questionPanel, BorderLayout.CENTER);
        questionPanel.setLayout(new BorderLayout());
        
        // The questionTextPanel contains the JTextArea for the actual question
        JPanel questionTextPanel = new JPanel();
        questionTextPanel.setBackground(UIColors.lightBrown);
        questionTextPanel.setBorder(new TitledBorder(null, "Question Text", 
                TitledBorder.CENTER, TitledBorder.TOP));
        questionPanel.add(questionTextPanel, BorderLayout.NORTH);
        questionTextPanel.setLayout(new BorderLayout());
        
        questionArea = new JTextArea();
        questionTextPanel.add(new JScrollPane(questionArea));
        questionArea.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        questionArea.setWrapStyleWord(true);
        questionArea.setLineWrap(true);
        questionArea.setRows(5);
        
        /* The answerPanel with a JTable for answers and buttons manipulating
         * the answers. */
        JPanel answerPanel = new JPanel();
        answerPanel.setBackground(UIColors.lightBrown);
        answerPanel.setBorder(new TitledBorder(null, "Answers", TitledBorder.CENTER, TitledBorder.TOP));
        questionPanel.add(answerPanel, BorderLayout.CENTER);
        answerPanel.setLayout(new BorderLayout());
        
        // JTable with all the answers
        // Random data. Will work differently at some point.. * * * * * * * * * * * * *
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        String[] heads = {"Correct", "Choice"};
        model.setRowCount(4);
        model.setColumnIdentifiers(heads);
        model.setValueAt("X", 0, 0);
        model.setValueAt("Unified Process", 0, 1);
        model.setValueAt("", 1, 0);
        model.setValueAt("Universal Process", 1, 1);
        model.setValueAt("", 2, 0);
        model.setValueAt("Unified Problems", 2, 1);
        model.setValueAt("", 3, 0);
        model.setValueAt("Unsatisfactory Process", 3, 1);
        
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(500);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        answerPanel.add(new JScrollPane(table));
        
        // Panel with buttons to manipulate answers.
        JPanel answerButtonPanel = new JPanel();
        answerButtonPanel.setBackground(UIColors.lightBrown);
        answerButtonPanel.setLayout(new GridLayout(1, 3));
        
        btnToggleCorrect = new JButton("Toggle Correct");
        answerButtonPanel.add(btnToggleCorrect);
        btnRemoveAnswer = new JButton("Remove Answer");
        answerButtonPanel.add(btnRemoveAnswer);        
        btnAddAnswer = new JButton("Add Answer");
        answerButtonPanel.add(btnAddAnswer);
        
        answerPanel.add(answerButtonPanel, BorderLayout.SOUTH);
        
        /* Panel with buttons to reset and add a question. The panel also has
         * a button to select previous questions used in other exercises. */
        JPanel questionButtonPanel = new JPanel();
        questionButtonPanel.setBorder(new TitledBorder(null, "Add Question", 
                TitledBorder.CENTER, TitledBorder.TOP));
        questionButtonPanel.setBackground(UIColors.lightBrown);
        questionPanel.add(questionButtonPanel, BorderLayout.SOUTH);
        questionButtonPanel.setLayout(new GridLayout(1, 3));
        
        btnResetQuestion = new JButton("Reset Question");
        questionButtonPanel.add(btnResetQuestion);
        
        btnAddExistingQuestion = new JButton("Add Existing Question");
        questionButtonPanel.add(btnAddExistingQuestion);
        
        btnAddQuestion = new JButton("Add Question");
        questionButtonPanel.add(btnAddQuestion);
    }

    /**
     * Adds an ActionListener of type AddExerciseUIListener to all the JButtons 
     * from the addExercisePanel.
     */
    private void addActionListeners() {
        AddExerciseUIListener listener = new AddExerciseUIListener();
        btnSubmit.addActionListener(listener);
        btnHelp.addActionListener(listener);
        btnDiscard.addActionListener(listener);
        btnResetQuestion.addActionListener(listener);
        btnAddExistingQuestion.addActionListener(listener);
        btnAddQuestion.addActionListener(listener);
        btnToggleCorrect.addActionListener(listener);
        btnRemoveAnswer.addActionListener(listener);
        btnAddAnswer.addActionListener(listener);
        btnRemoveTag.addActionListener(listener);
        btnAddExistingTag.addActionListener(listener);
        btnPrevQuestion.addActionListener(listener);
        btnNextQuestion.addActionListener(listener);
        btnNewTag.addActionListener(listener);
        btnFindExistingTag.addActionListener(listener);
    }
    
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    
    /**
     * The AddExercuseUIListener class is the class in charge of listening
     * for events happening in the AddExerciseUI (e.g. an user clicking the 
     * help button). When an event occurs the AddExercuseUIListener will 
     * send a signal to the AddExercisePresenter which will in return act 
     * upon the event.
     * 
     * @author Morten Faarkrog
     * @version 0.1
     * @see ActionListener
     * @date 4. December, 2013
     */
    private class AddExerciseUIListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnSubmit) {
                
            }
            
            else if (e.getSource() == btnDiscard) {
                
            }

            else if (e.getSource() == btnResetQuestion) {
                
            }

            else if (e.getSource() == btnAddExistingQuestion) {
                
            }

            else if (e.getSource() == btnAddQuestion) {
                
            }

            else if (e.getSource() == btnToggleCorrect) {
                
            }

            else if (e.getSource() == btnRemoveAnswer) {
                
            }

            else if (e.getSource() == btnAddAnswer) {
                
            }

            else if (e.getSource() == btnNewTag) {
                
            }
            
            else if (e.getSource() == btnFindExistingTag) {
                
            }
            
            else if (e.getSource() == btnRemoveTag) {
                
            }

            else if (e.getSource() == btnAddExistingTag) {
                
            }

            else if (e.getSource() == btnPrevQuestion) {
                
            }

            else if (e.getSource() == btnNextQuestion) {
                
            }
            
            else if (e.getSource() == btnHelp) {
                JOptionPane.showMessageDialog(null, HelpPresenter.getAddExerciseHelp(), 
                        HelpPresenter.getAddExerciseTitle(), JOptionPane.PLAIN_MESSAGE, 
                        HelpPresenter.getHelpIcon());
            }
        }
        
    }

}
