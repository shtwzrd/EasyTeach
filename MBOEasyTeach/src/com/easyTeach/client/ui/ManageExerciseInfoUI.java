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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.easyTeach.client.presenter.HelpPresenter;

/**
 * The ManageExerciseInfoUI class constructs a JPanel with all the different
 * JComponents needed to manage the information about an Exercise.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 8. December, 2013
 */

public class ManageExerciseInfoUI {

    private final String[] TRUE_FALSE = { "True", "False" };
    private JPanel manageExerciseInfoPanel;
    private JButton btnHelp;
    private JButton btnDiscard;
    private JPanel centerPanel;
    private JButton btnFilter;
    private JButton btnContinue;
    private JTextField txtExerciseName;
    private JTextField txtIsTest;
    private JTextField txtIsLocked;
    private JPasswordField txtPassword;
    private JTextField txtTimelimit;
    private JTextField txtAccessStart;
    private JTextField txtAccessEnd;
    private JComboBox isTextBox;
    private JComboBox isLockedBox;

    /**
     * Constructor for building the manageExerciseInfoPanel. The panel is built
     * by calling the buildPanel method. The constructor also calls a method
     * adding actionListeners to all buttons.
     */
    public ManageExerciseInfoUI() {
        buildPanel();
        addActionListeners();
    }

    /**
     * Returns the ManageExercisePanel with all of the JComponents within.
     * 
     * @return the JPanel manageExerciseInfoPanel
     */
    public JPanel getManageExerciseInfoUI() {
        return manageExerciseInfoPanel;
    }

    /**
     * Builds the ManageExercisePanel with all of the JComponenets within.
     */
    public void buildPanel() {
        manageExerciseInfoPanel = new JPanel(new BorderLayout());

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

        manageExerciseInfoPanel.add(northPanel, BorderLayout.NORTH);
    }

    /**
     * Builds the center panel with the components needed to manage all the
     * information about an exercise.
     */
    private void buildCenterPanel() {
        centerPanel = new JPanel(new GridLayout(8, 2, 1, 50));
        centerPanel.setBackground(UIColors.lightBlue);

        manageExerciseInfoPanel.add(centerPanel, BorderLayout.CENTER);

        centerPanel.setBackground(UIColors.lightBlue);
        centerPanel.setBorder(new TitledBorder(new EtchedBorder(
                EtchedBorder.LOWERED, new Color(0, 0, 0), null),
                "Exercise Information", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Tahoma", Font.PLAIN, 20)));

        JLabel lblExerciseName = new JLabel("Exercise Name:", JLabel.CENTER);
        lblExerciseName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        centerPanel.add(lblExerciseName);

        txtExerciseName = new JTextField();
        centerPanel.add(txtExerciseName);

        JLabel lblIsText = new JLabel("Is a Test:", JLabel.CENTER);
        lblIsText.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        centerPanel.add(lblIsText);

        isTextBox = new JComboBox();
        isTextBox.setModel(new DefaultComboBoxModel(TRUE_FALSE));
        centerPanel.add(isTextBox);

        JLabel lblOptionalFields = new JLabel("Optional Fields (Tests only)", JLabel.CENTER);
        lblOptionalFields.setFont(new Font("Lucida Grande", Font.ITALIC
                + Font.BOLD, 14));
        centerPanel.add(lblOptionalFields);
        centerPanel.add(new JLabel(
                "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -",
                        JLabel.CENTER));

        JLabel lblIsLocked = new JLabel("Is Locked:", JLabel.CENTER);
        lblIsLocked.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        centerPanel.add(lblIsLocked);

        isLockedBox = new JComboBox();
        isLockedBox.setModel(new DefaultComboBoxModel(TRUE_FALSE));
        centerPanel.add(isLockedBox);

        JLabel lblPassword = new JLabel("Password:", JLabel.CENTER);
        lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        centerPanel.add(lblPassword);

        txtPassword = new JPasswordField();
        centerPanel.add(txtPassword);

        JLabel lblTimelimit = new JLabel("Time Limit (min):", JLabel.CENTER);
        lblTimelimit.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        centerPanel.add(lblTimelimit);

        txtTimelimit = new JTextField();
        centerPanel.add(txtTimelimit);

        JLabel lblAccessStart = new JLabel("Access Start (YYYY/MM/DD hh:mm):", JLabel.CENTER);
        lblAccessStart.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        centerPanel.add(lblAccessStart);

        txtAccessStart = new JTextField();
        centerPanel.add(txtAccessStart);

        JLabel lblAccessEnd = new JLabel("Access End (YYYY/MM/DD hh:mm):", JLabel.CENTER);
        lblAccessEnd.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        centerPanel.add(lblAccessEnd);

        txtAccessEnd = new JTextField();
        centerPanel.add(txtAccessEnd);
    }

    /**
     * Builds the South Panel which has buttons for Help, Discard, Continue.
     * Continue moves the user to the ManageExerciseUI where questions for the
     * exercise are managed.
     */
    private void buildSouthPanel() {
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.setBackground(UIColors.darkBlue);

        btnHelp = new JButton("Help");
        southPanel.add(btnHelp);

        btnDiscard = new JButton("Discard");
        southPanel.add(btnDiscard);

        btnContinue = new JButton("Continue");
        southPanel.add(btnContinue);

        manageExerciseInfoPanel.add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Adds an ActionListener of type ManageClassListener to all the JButtons
     * from the manageExerciseInfoPanel.
     */
    private void addActionListeners() {
        ManageExerciseInfoListener listener = new ManageExerciseInfoListener();
        btnDiscard.addActionListener(listener);
        btnHelp.addActionListener(listener);
        btnContinue.addActionListener(listener);
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

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnDiscard) {
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

            else if (e.getSource() == btnContinue) {
                MainFrame.updateFrame(
                        new ManageExerciseUI().getManageExerciseUI(),
                        "Manage Exercise");
            }

            else if (e.getSource() == btnFilter) {

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
