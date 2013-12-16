package com.easyTeach.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.easyTeach.client.presenter.HelpPresenter;
import com.easyTeach.client.presenter.ManageExerciseInfoPresenter;
import com.easyTeach.common.ui.UIColors;

/**
 * The ManageExerciseInfoUI class constructs a JPanel with all the different
 * JComponents needed to manage the information about an Exercise.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 8. December, 2013
 */

public class ManageExerciseInfoUI {

    private JPanel manageExerciseInfoPanel;
    JButton btnHelp;
    JButton btnDiscard;
    private JPanel centerPanel;
    JButton btnFilter;
    JButton btnContinue;
    JTextField txtExerciseName;
    JCheckBox txtIsTest;
    JTextField txtIsLocked;
    JPasswordField txtPassword;
    JTextField txtTimelimit;
    JTextField txtAccessStart;
    JTextField txtAccessEnd;
    JCheckBox isTextBox;
    JCheckBox isLockedBox;
    JComboBox<String> courseBox;
    ComboBoxModel<String> courseBoxModel;
    ManageExerciseInfoPresenter presenter;

    /**
     * Constructor for building the manageExerciseInfoPanel. The panel is built
     * by calling the buildPanel method. The constructor also calls a method
     * adding actionListeners to all buttons.
     */
    public ManageExerciseInfoUI() {
    	this.presenter = new ManageExerciseInfoPresenter();
        buildPanel();
        addActionListeners();
        loadFromPresenter();

    }

    /**
     * Returns the ManageExercisePanel with all of the JComponents within.
     * 
     * @return the JPanel manageExerciseInfoPanel
     */
    public JPanel getManageExerciseInfoUI() {
        return this.manageExerciseInfoPanel;
    }

    /**
     * Builds the ManageExercisePanel with all of the JComponenets within.
     */
    public void buildPanel() {
        this.manageExerciseInfoPanel = new JPanel(new BorderLayout());

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

        JLabel lblMangeExerciseInfo = new JLabel("Manage Exercise Info");
        lblMangeExerciseInfo.setForeground(UIColors.white);
        lblMangeExerciseInfo.setFont(new Font("Lucida Grande", Font.BOLD, 24));
        northPanel.add(lblMangeExerciseInfo);

        this.manageExerciseInfoPanel.add(northPanel, BorderLayout.NORTH);
    }

    /**
     * Builds the center panel with the components needed to manage all the
     * information about an exercise.
     */
    private void buildCenterPanel() {
        this.centerPanel = new JPanel(new GridLayout(9, 2, 1, 40));
        this.centerPanel.setBackground(UIColors.lightBlue);

        this.manageExerciseInfoPanel.add(this.centerPanel, BorderLayout.CENTER);

        this.centerPanel.setBackground(UIColors.lightBlue);
        this.centerPanel.setBorder(new TitledBorder(new EtchedBorder(
                EtchedBorder.LOWERED, new Color(0, 0, 0), null),
                "Exercise Information", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Tahoma", Font.PLAIN, 20)));

        JLabel lblExerciseName = new JLabel("Exercise Name:", SwingConstants.CENTER);
        lblExerciseName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        this.centerPanel.add(lblExerciseName);

        this.txtExerciseName = new JTextField();
        this.centerPanel.add(this.txtExerciseName);
        
        JLabel lblCourse = new JLabel("Course:", SwingConstants.CENTER);
        lblCourse.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        this.centerPanel.add(lblCourse);

        this.courseBox = new JComboBox<>();
        ArrayList<String> courseSelections = this.presenter.getAvailableCourses();
        this.courseBoxModel = new DefaultComboBoxModel<>();
        for(String s: courseSelections) {
        }
        this.courseBox.setModel((ComboBoxModel<String>) this.courseBoxModel);
        this.centerPanel.add(this.courseBox);

        JLabel lblIsText = new JLabel("Is a Test:", SwingConstants.CENTER);
        lblIsText.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        this.centerPanel.add(lblIsText);

        this.isTextBox = new JCheckBox();
        this.centerPanel.add(this.isTextBox);

        JLabel lblOptionalFields = new JLabel("Optional Fields (Tests only)", SwingConstants.CENTER);
        lblOptionalFields.setFont(new Font("Lucida Grande", Font.ITALIC
                + Font.BOLD, 14));
        this.centerPanel.add(lblOptionalFields);
        this.centerPanel.add(new JLabel(
                "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -",
                        SwingConstants.CENTER));

        JLabel lblIsLocked = new JLabel("Is Locked:", SwingConstants.CENTER);
        lblIsLocked.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        this.centerPanel.add(lblIsLocked);

        this.isLockedBox = new JCheckBox();
        this.centerPanel.add(this.isLockedBox);

        JLabel lblPassword = new JLabel("Password:", SwingConstants.CENTER);
        lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        this.centerPanel.add(lblPassword);

        this.txtPassword = new JPasswordField();
        this.centerPanel.add(this.txtPassword);

        JLabel lblTimelimit = new JLabel("Time Limit (min):", SwingConstants.CENTER);
        lblTimelimit.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        this.centerPanel.add(lblTimelimit);

        this.txtTimelimit = new JTextField();
        this.centerPanel.add(this.txtTimelimit);

        JLabel lblAccessStart = new JLabel("Access Start (YYYY/MM/DD hh:mm):", SwingConstants.CENTER);
        lblAccessStart.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        this.centerPanel.add(lblAccessStart);

        this.txtAccessStart = new JTextField();
        this.centerPanel.add(this.txtAccessStart);

        JLabel lblAccessEnd = new JLabel("Access End (YYYY/MM/DD hh:mm):", SwingConstants.CENTER);
        lblAccessEnd.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        this.centerPanel.add(lblAccessEnd);

        this.txtAccessEnd = new JTextField();
        this.centerPanel.add(this.txtAccessEnd);
    }

    /**
     * Builds the South Panel which has buttons for Help, Discard, Continue.
     * Continue moves the user to the ManageExerciseUI where questions for the
     * exercise are managed.
     */
    private void buildSouthPanel() {
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.setBackground(UIColors.darkBlue);

        this.btnHelp = new JButton("Help");
        southPanel.add(this.btnHelp);

        this.btnDiscard = new JButton("Discard");
        southPanel.add(this.btnDiscard);

        this.btnContinue = new JButton("Continue");
        southPanel.add(this.btnContinue);

        this.manageExerciseInfoPanel.add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Adds an ActionListener of type ManageClassListener to all the JButtons
     * from the manageExerciseInfoPanel.
     */
    private void addActionListeners() {
        ManageExerciseInfoListener listener = new ManageExerciseInfoListener();
        this.btnDiscard.addActionListener(listener);
        this.btnHelp.addActionListener(listener);
        this.btnContinue.addActionListener(listener);
    }
    
    private void loadFromPresenter() {
    	this.txtExerciseName.setText(this.presenter.getExerciseName());
    	this.isTextBox.setSelected(this.presenter.getIsTest());
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    /**
     * The ManageExerciseInfoListener class is the class in charge of listening
     * for events happening in the ManageExerciseInfoUI (e.g. a user clicking
     * the help button). When an event occurs the ManageExerciseInfoListener
     * will send a signal to the ManageExerciseInfoPresenter which will in
     * return act upon the event.
     * 
     * @author Morten Faarkrog
     * @version 0.1
     * @see ActionListener
     * @date 6. December, 2013
     */
    private class ManageExerciseInfoListener implements ActionListener {

        public ManageExerciseInfoListener() {
        	//Empty Constructor
		}

		@Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ManageExerciseInfoUI.this.btnDiscard) {
                int reply = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want discard what you have made?\n"
                                + "Warning: It will not be saved.",
                        "Discard Message", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    MainFrame.updateFrame(
                            new TeacherManagerUI().getTeacherManagerUI(),
                            "Teacher Manager");
                }
            }

            else if (e.getSource() == ManageExerciseInfoUI.this.btnContinue) {
                MainFrame.updateFrame(
                        new ManageExerciseUI().getManageExerciseUI(),
                        "Manage Exercise");
            }

            else if (e.getSource() == ManageExerciseInfoUI.this.btnHelp) {
                JOptionPane.showMessageDialog(null,
                        HelpPresenter.getManageClassHelp(),
                        HelpPresenter.getManageClassTitle(),
                        JOptionPane.PLAIN_MESSAGE, HelpPresenter.getHelpIcon());
            }
        }
    }

}
