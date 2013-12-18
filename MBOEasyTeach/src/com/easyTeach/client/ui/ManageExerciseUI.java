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
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.easyTeach.client.presenter.DisplayTableModel;
import com.easyTeach.client.presenter.HelpPresenter;
import com.easyTeach.common.ui.UIColors;

/**
 * The ManageExerciseUI class constructs a JPanel with all the different
 * JComponents needed to constructor new Exercises and edit old ones.
 * 
 * @author Morten Faarkrog
 * @version 1.1
 * @date 5. December, 2013
 */

public class ManageExerciseUI {

    private JPanel manageExercisePanel;
    private JPanel centerPanel;
    private JPanel infoPanel;
    private JTable table;
    private JLabel lblExerciseTitle;
    private final String[] QUESTION_TYPES = { "Multiple Choice", "True/False",
            "Missing Word" };
    private JTextArea questionArea;
    JComboBox<String> questionTypeBox;
    JComboBox<String> comboBox;
    JTextField txtNewTag;
    JTextField txtExistingTag;
    JButton btnSave;
    JButton btnHelp;
    JButton btnDiscard;
    JButton btnResetQuestion;
    JButton btnAddExistingQuestion;
    JButton btnAddQuestion;
    JButton btnToggleCorrect;
    JButton btnRemoveAnswer;
    JButton btnAddAnswer;
    JButton btnRemoveTag;
    JButton btnAddExistingTag;
    JButton btnPrevQuestion;
    JButton btnNextQuestion;
    JButton btnNewTag;
    JButton btnFindExistingTag;
    JButton btnRemoveQuestion;
    JSlider slider;

    /**
     * Constructor for building the manageExercisePanel. The panel is built by
     * calling the buildPanel method. The constructor also calls a method adding
     * actionListeners to all buttons.
     */
    public ManageExerciseUI() {
        buildPanel();
        addActionListeners();
    }

    /**
     * The getManageExerciseUI returns the JPanel containing all the components
     * needed to maintain exercises.
     * 
     * @return the JPanel, manageExercisePanel. Meant to be used in another
     *         frame.
     */
    public JPanel getManageExerciseUI() {
        return this.manageExercisePanel;
    }

    /**
     * The method responsible for building the manageExercisePanel.
     */
    private void buildPanel() {
        this.manageExercisePanel = new JPanel();
        this.manageExercisePanel.setLayout(new BorderLayout());

        buildNorthPanel();
        buildCenterPanel();
        buildSouthPanel();
    }

    /**
     * Method for creating the Navigation panel for going to the next or the
     * previous question. This will make it easier to change previously defined
     * questions if one wants to do this.
     */
    private void buildQuestionNavPanel() {
        JPanel questionNavPanel = new JPanel();
        questionNavPanel.setBackground(UIColors.lightBlue);
        questionNavPanel.setBorder(new TitledBorder(new EtchedBorder(
                EtchedBorder.LOWERED, new Color(0, 0, 0), null), "Navigation",
                TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma",
                        Font.PLAIN, 20)));
        this.centerPanel.add(questionNavPanel, BorderLayout.NORTH);
        questionNavPanel.setLayout(new GridLayout(1, 2));

        this.btnPrevQuestion = new JButton("<< Previous Question <<");
        this.btnPrevQuestion.setFont(new Font("Arial", Font.BOLD, 16));
        questionNavPanel.add(this.btnPrevQuestion);

        this.btnNextQuestion = new JButton(">> Next Question >>");
        this.btnNextQuestion.setFont(new Font("Arial", Font.BOLD, 16));
        questionNavPanel.add(this.btnNextQuestion);
    }

    /**
     * Method for building the infoPanel containing calls to create the panels
     * where the question's type and tags are defined.
     */
    private void buildInfoPanel() {
        this.infoPanel = new JPanel();
        this.centerPanel.add(this.infoPanel, BorderLayout.WEST);
        this.infoPanel.setLayout(new BorderLayout());

        buildQuestionTypePanel();
        buildTagPanel();
    }

    /**
     * Method for defining the questiobTypePanel where one selects the question
     * type
     */
    private void buildQuestionTypePanel() {
        JPanel questionTypePanel = new JPanel();
        questionTypePanel.setBackground(UIColors.lightBlue);
        questionTypePanel.setBorder(new TitledBorder(new EtchedBorder(
                EtchedBorder.LOWERED, new Color(0, 0, 0), null),
                "Question Type", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Tahoma", Font.PLAIN, 20)));
        this.infoPanel.add(questionTypePanel, BorderLayout.NORTH);

        // JComboBox with the various question types one can pick
        this.questionTypeBox = new JComboBox();
        this.questionTypeBox.setModel(new DefaultComboBoxModel(this.QUESTION_TYPES));
        questionTypePanel.add(this.questionTypeBox);

        this.infoPanel.add(questionTypePanel, BorderLayout.NORTH);
    }

    /**
     * Method for building the panel for maintaining the tags for a question.
     * New tags can be created, existing tags can be browsed, and tags for a
     * question can be removed.
     */
    private void buildTagPanel() {
        JPanel tagPanel = new JPanel();
        tagPanel.setBackground(UIColors.lightBlue);
        tagPanel.setBorder(new TitledBorder(new EtchedBorder(
                EtchedBorder.LOWERED, new Color(0, 0, 0), null), "Tags",
                TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma",
                        Font.PLAIN, 20)));
        this.infoPanel.add(tagPanel, BorderLayout.CENTER);
        tagPanel.setLayout(new GridLayout(3, 1));

        // Panel for browsing the tags for the question one is working on
        JPanel questionTagPanel = new JPanel();
        questionTagPanel.setBackground(UIColors.lightBlue);
        questionTagPanel.setBorder(new TitledBorder(null, "Question Tags",
                TitledBorder.CENTER, TitledBorder.TOP));
        tagPanel.add(questionTagPanel);
        questionTagPanel.setLayout(new GridLayout(5, 1));

        // JComboBox with all the current tags for a question
        this.comboBox = new JComboBox();
        questionTagPanel.add(this.comboBox);

        this.btnRemoveTag = new JButton("Remove Tag");
        questionTagPanel.add(this.btnRemoveTag);

        // Panel for selecting tags previously used in other questions
        JPanel existingTagPanel = new JPanel();
        existingTagPanel.setBackground(UIColors.lightBlue);
        existingTagPanel.setBorder(new TitledBorder(null, "Add Existing Tag",
                TitledBorder.CENTER, TitledBorder.TOP));
        tagPanel.add(existingTagPanel);
        existingTagPanel.setLayout(new GridLayout(5, 1));

        this.btnFindExistingTag = new JButton("Find Existing Tag");
        existingTagPanel.add(this.btnFindExistingTag);

        this.txtExistingTag = new JTextField();
        this.txtExistingTag.setEditable(false);
        existingTagPanel.add(this.txtExistingTag);
        this.txtExistingTag.setColumns(10);

        this.btnAddExistingTag = new JButton("Add Existing Tag");
        existingTagPanel.add(this.btnAddExistingTag);

        // Panel for creating new tags to the question
        JPanel newTagPanel = new JPanel();
        newTagPanel.setBorder(new TitledBorder(null, "Add New Tag",
                TitledBorder.CENTER, TitledBorder.TOP));
        newTagPanel.setBackground(UIColors.lightBlue);
        tagPanel.add(newTagPanel);
        newTagPanel.setLayout(new GridLayout(5, 1));

        this.txtNewTag = new JTextField();
        newTagPanel.add(this.txtNewTag);
        this.txtNewTag.setColumns(10);

        this.btnNewTag = new JButton("Add New Tag");
        newTagPanel.add(this.btnNewTag);
    }

    /**
     * Method for creating the north panel containing the exercise's name and
     * what question one is currently at.
     */
    private void buildNorthPanel() {
        JPanel northPanel = new JPanel();
        northPanel.setBackground(UIColors.darkBlue);
        northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        this.manageExercisePanel.add(northPanel, BorderLayout.NORTH);

        this.lblExerciseTitle = new JLabel("Exercise - Name Goes Here - Question #1");
        this.lblExerciseTitle.setForeground(UIColors.white);
        this.lblExerciseTitle.setFont(new Font("Lucida Grande", Font.BOLD, 24));
        northPanel.add(this.lblExerciseTitle);
    }

    /**
     * Method for creating the south panel with buttons for saving and
     * discarding an exercise along with a help button.
     */
    private void buildSouthPanel() {
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.setBackground(UIColors.darkBlue);
        southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        this.manageExercisePanel.add(southPanel, BorderLayout.SOUTH);

        this.btnSave = new JButton("Save");

        this.btnHelp = new JButton("Help");
        southPanel.add(this.btnHelp);

        this.btnDiscard = new JButton("Discard");
        southPanel.add(this.btnDiscard);
        southPanel.add(this.btnSave);
    }

    /**
     * Method for creating the center panel. The center panel contains other
     * panels for Navigating the next/prev question, selecting Question Type,
     * creating Tags and creating the actual Question. These panels will be
     * created calling other methods.
     */
    private void buildCenterPanel() {
        // Center panel with panels for Question Type, Tags and Question
        this.centerPanel = new JPanel();
        this.manageExercisePanel.add(this.centerPanel, BorderLayout.CENTER);
        this.centerPanel.setLayout(new BorderLayout());

        buildQuestionNavPanel();
        buildInfoPanel();
        buildQuestionPanel();
    }

    /**
     * Method for creating the questionPanel where a user can define a question
     * and its matching answers. Moreover, the user will also have the option of
     * saving the question or resetting it.
     */
    private void buildQuestionPanel() {
        JPanel questionPanel = new JPanel();
        questionPanel.setBackground(UIColors.lightBrown);
        questionPanel.setBorder(new TitledBorder(new EtchedBorder(
                EtchedBorder.LOWERED, new Color(0, 0, 0), null), "Question",
                TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma",
                        Font.PLAIN, 20)));
        this.centerPanel.add(questionPanel, BorderLayout.CENTER);
        questionPanel.setLayout(new BorderLayout());

        // The questionTextPanel contains the JTextArea for the actual question
        JPanel questionTextPanel = new JPanel(new BorderLayout());
        questionTextPanel.setBackground(UIColors.lightBrown);
        questionTextPanel.setBorder(new TitledBorder(null, "Question Text",
                TitledBorder.CENTER, TitledBorder.TOP));
        questionPanel.add(questionTextPanel, BorderLayout.NORTH);
        questionTextPanel.setLayout(new BorderLayout());

        this.questionArea = new JTextArea();
        questionTextPanel.add(new JScrollPane(this.questionArea));
        this.questionArea.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        this.questionArea.setWrapStyleWord(true);
        this.questionArea.setLineWrap(true);
        this.questionArea.setRows(5);

        JPanel pointsPanel = new JPanel();
        this.slider = new JSlider();
        this.slider.setPaintLabels(true);
        this.slider.setMajorTickSpacing(1);
        this.slider.setValue(5);
        this.slider.setMinimum(1);
        this.slider.setMaximum(10);
        pointsPanel.add(this.slider);
        pointsPanel.setBackground(UIColors.lightBrown);
        pointsPanel.setBorder(new TitledBorder(null, "Points",
                TitledBorder.CENTER, TitledBorder.TOP));
        questionTextPanel.add(pointsPanel, BorderLayout.SOUTH);

        /*
         * The answerPanel with a JTable for answers and buttons manipulating
         * the answers.
         */
        JPanel answerPanel = new JPanel();
        answerPanel.setBackground(UIColors.lightBrown);
        answerPanel.setBorder(new TitledBorder(null, "Answers",
                TitledBorder.CENTER, TitledBorder.TOP));
        questionPanel.add(answerPanel, BorderLayout.CENTER);
        answerPanel.setLayout(new BorderLayout());

        // JTable with all the answers
        // Random data. Will work differently at some point.. * * * * * * * * *
        // * * * *
        this.table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        String[] heads = { "Correct", "Choice" };
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

        this.table.setModel(model);
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = this.table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(500);
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        answerPanel.add(new JScrollPane(this.table));

        // Panel with buttons to manipulate answers.
        JPanel answerButtonPanel = new JPanel();
        answerButtonPanel.setBackground(UIColors.lightBrown);
        answerButtonPanel.setLayout(new GridLayout(1, 3));

        this.btnToggleCorrect = new JButton("Toggle Correct");
        answerButtonPanel.add(this.btnToggleCorrect);
        this.btnRemoveAnswer = new JButton("Remove Answer");
        answerButtonPanel.add(this.btnRemoveAnswer);
        this.btnAddAnswer = new JButton("Add Answer");
        answerButtonPanel.add(this.btnAddAnswer);

        answerPanel.add(answerButtonPanel, BorderLayout.SOUTH);

        /*
         * Panel with buttons to reset and add a question. The panel also has a
         * button to select previous questions used in other exercises.
         */
        JPanel questionButtonPanel = new JPanel();
        questionButtonPanel.setBorder(new TitledBorder(null, "Add Question",
                TitledBorder.CENTER, TitledBorder.TOP));
        questionButtonPanel.setBackground(UIColors.lightBrown);
        questionPanel.add(questionButtonPanel, BorderLayout.SOUTH);
        questionButtonPanel.setLayout(new GridLayout(1, 3));

        this.btnResetQuestion = new JButton("Reset Question");
        questionButtonPanel.add(this.btnResetQuestion);

        this.btnRemoveQuestion = new JButton("Remove Question");
        questionButtonPanel.add(this.btnRemoveQuestion);

        this.btnAddExistingQuestion = new JButton("Add Existing Question");
        questionButtonPanel.add(this.btnAddExistingQuestion);

        this.btnAddQuestion = new JButton("Add Question");
        questionButtonPanel.add(this.btnAddQuestion);
    }

    /**
     * Adds an ActionListener of type ManageExerciseUIListener to all the
     * JButtons from the manageExercisePanel.
     */
    private void addActionListeners() {
        ManageExerciseUIListener listener = new ManageExerciseUIListener();
        this.btnSave.addActionListener(listener);
        this.btnHelp.addActionListener(listener);
        this.btnDiscard.addActionListener(listener);
        this.btnResetQuestion.addActionListener(listener);
        this.btnAddExistingQuestion.addActionListener(listener);
        this.btnAddQuestion.addActionListener(listener);
        this.btnToggleCorrect.addActionListener(listener);
        this.btnRemoveAnswer.addActionListener(listener);
        this.btnAddAnswer.addActionListener(listener);
        this.btnRemoveTag.addActionListener(listener);
        this.btnAddExistingTag.addActionListener(listener);
        this.btnPrevQuestion.addActionListener(listener);
        this.btnNextQuestion.addActionListener(listener);
        this.btnNewTag.addActionListener(listener);
        this.btnFindExistingTag.addActionListener(listener);
        this.btnRemoveQuestion.addActionListener(listener);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    /**
     * The ManageExerciseUIListener class is the class in charge of listening
     * for events happening in the ManageExerciseUI (e.g. an user clicking the
     * help button). When an event occurs the ManageExerciseUIListener will send
     * a signal to the ManageExercisePresenter which will in return act upon the
     * event.
     * 
     * @author Morten Faarkrog
     * @version 0.1
     * @see ActionListener
     * @date 4. December, 2013
     */
    private class ManageExerciseUIListener implements ActionListener {

        public ManageExerciseUIListener() {
			// Empty Constructor 
		}

		@Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ManageExerciseUI.this.btnSave) {

            }

            else if (e.getSource() == ManageExerciseUI.this.btnDiscard) {

            }

            else if (e.getSource() == ManageExerciseUI.this.btnResetQuestion) {

            }

            else if (e.getSource() == ManageExerciseUI.this.btnAddExistingQuestion) {

            }

            else if (e.getSource() == ManageExerciseUI.this.btnRemoveQuestion) {

            }

            else if (e.getSource() == ManageExerciseUI.this.btnAddQuestion) {

            }

            else if (e.getSource() == ManageExerciseUI.this.btnToggleCorrect) {

            }

            else if (e.getSource() == ManageExerciseUI.this.btnRemoveAnswer) {

            }

            else if (e.getSource() == ManageExerciseUI.this.btnAddAnswer) {

            }

            else if (e.getSource() == ManageExerciseUI.this.btnNewTag) {

            }

            else if (e.getSource() == ManageExerciseUI.this.btnFindExistingTag) {

            }

            else if (e.getSource() == ManageExerciseUI.this.btnRemoveTag) {

            }

            else if (e.getSource() == ManageExerciseUI.this.btnAddExistingTag) {

            }

            else if (e.getSource() == ManageExerciseUI.this.btnPrevQuestion) {

            }

            else if (e.getSource() == ManageExerciseUI.this.btnNextQuestion) {

            }

            else if (e.getSource() == ManageExerciseUI.this.btnHelp) {
                JOptionPane.showMessageDialog(null,
                        HelpPresenter.getManageExerciseHelp(),
                        HelpPresenter.getManageExerciseTitle(),
                        JOptionPane.PLAIN_MESSAGE, HelpPresenter.getHelpIcon());
            }
        }

    }

}
