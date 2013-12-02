package com.easyTeach.test;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.easyTeach.client.ui.MainFrame;

public class MainFrameTest {

    public static void main(String[] args) {
        new MainFrame();
        JPanel panel = new JPanel();
        panel.add(new JButton("ButMaster"));
        MainFrame.updateFrame(panel, "EasyTeach New Frame");

        JPanel panel2 = new JPanel();
        panel2.add(new JButton("Bututaui"));
        MainFrame.updateFrame(panel2, "EasyTeach New Frame 2");
    }
    
}
