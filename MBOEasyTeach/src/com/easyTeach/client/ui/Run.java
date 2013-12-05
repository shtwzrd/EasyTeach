package com.easyTeach.client.ui;

import com.easyTeach.client.presenter.HelpPresenter;

/**
 * The start of the client's application.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 4. December, 2013
 */

public class Run {

    public static void main(String[] args) {
        new UIColors();
        new HelpPresenter();
        new LoginUI();
        new MainFrame();
//        MainFrame.updateFrame(new AddExerciseUI().getAddExerciseUI(), "Title");
        MainFrame.updateFrame(new ManageUserUI().getManagerUserUI(), "Title");
    }

}
