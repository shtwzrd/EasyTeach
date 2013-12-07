package com.easyTeach.client.ui;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.easyTeach.client.presenter.HelpPresenter;

/**
 * Starts the client's application and sets the LookAndFeel for the system.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 5. December, 2013
 */

public class Run {

    public static void main(String[] args) 
            throws ClassNotFoundException, InstantiationException, 
            IllegalAccessException, UnsupportedLookAndFeelException {
        /* Decides what LookAndFeel is used for the application (used to test how 
         * it looks on both Windows and Mac OSX) */
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        
        // Start application
        new UIColors();
        new HelpPresenter();
        new LoginUI();
        new MainFrame();
//        MainFrame.updateFrame(new ManageExerciseUI().getManageExerciseUI(), "Title");
//        MainFrame.updateFrame(new ManageUserUI().getManageUserUI(), "Title");
//        MainFrame.updateFrame(new UserManagerUI().getUserManagerUI(), "Title");
//        MainFrame.updateFrame(new ExerciseManagerUI().getExerciseManagerUI(), "Title");
//        MainFrame.updateFrame(new QuestionManagerUI().getQuestionManagerUI(), "Title");
//        MainFrame.updateFrame(new ManageClassUI().getManageClassUI(), "Title");
        MainFrame.updateFrame(new AdminManagerUI().getAdminManagerUI(), "Admin Manager");
//        MainFrame.updateFrame(new ManageCourseUI().getManageCourseUI(), "Title");
    }

}
