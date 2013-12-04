package com.easyTeach.client.ui;

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
        new LoginUI();
        new MainFrame();
        MainFrame.updateFrame(new AddExerciseUI().getAddExerciseUI(), "Title");
    }

}
