package com.easyTeach.client.ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import com.easyTeach.common.ui.UIColors;

/**
 * The TeacherManagerUI class constructs a JPanel with all the different
 * JComponents needed to maintain exercises, questions and review the progress
 * of classes and users. 
 * 
 * @author Morten Faarkrog
 * @version 1.1
 * @date 8. December, 2013
 */

public class TeacherManagerUI {

    private JPanel teacherManagerPanel;
    private JTabbedPane tabPanel;

    /**
     * Constructor for building the teacherManagerPanel. The panel is built by
     * calling the buildPanel method.
     */
    public TeacherManagerUI() {
        buildPanel();
    }

    /**
     * The getTeacherManagerUI returns the JPanel containing all the components
     * needed to maintain exercises, questions and review progress amongst
     * classes and users.
     * 
     * @return the JPanel, teacherManagerPanel. Meant to be used in another
     *         frame.
     */
    public JPanel getTeacherManagerUI() {
        return this.teacherManagerPanel;
    }

    /**
     * The method responsible for building the teacherManagerPanel. The panel
     * consists of all the JComponenets needed to maintain users, classes and
     * courses.
     */
    public void buildPanel() {
        this.teacherManagerPanel = new JPanel(new BorderLayout());
        this.teacherManagerPanel.setBackground(UIColors.lightBlue);

        buildNorthPanel();
        buildCenterPanel();
    }

    /**
     * Builds the center panel with the tabbed panels for managing exercises,
     * quizzes and reviewing classes' and users' progress.
     * 
     * @see ExerciseManagerUI
     * @see QuestionManagerUI
     */
    private void buildCenterPanel() {
        this.tabPanel = new JTabbedPane(SwingConstants.TOP);
        this.tabPanel.add("Exercises",
                new ExerciseManagerUI().getExerciseManagerUI());
        this.tabPanel.add("Questions",
                new QuestionManagerUI().getQuestionManagerUI());
        this.tabPanel.add("Progress", new JPanel());

        this.teacherManagerPanel.add(this.tabPanel, BorderLayout.CENTER);
        this.teacherManagerPanel.setBackground(UIColors.darkBlue);
    }

    /**
     * Builds the north panel with the the headline of the UI.
     */
    private void buildNorthPanel() {
        JPanel northPanel = new JPanel();
        northPanel.setBackground(UIColors.darkBlue);

        JLabel lblTeacherManagerTitle = new JLabel(
                "Exercise, Question & Progress Manager");
        lblTeacherManagerTitle.setForeground(UIColors.white);
        lblTeacherManagerTitle.setFont(new Font("Lucida Grande", Font.BOLD, 24));
        northPanel.add(lblTeacherManagerTitle);

        this.teacherManagerPanel.add(northPanel, BorderLayout.NORTH);
    }

}