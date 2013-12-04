package com.easyTeach.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * <p>
 * The MainFrame class is the main frame for the MBO EasyTeach application.
 * Instead of opening a different frame every time a user moves to a new
 * view, the contentPanel within the MainFrame is updated. This way frames
 * are not overlapping each other, and the user can only be operating within
 * one view at a time.
 * </p>
 * 
 * <p>
 * The MainFrame class has an updateContentPanel method which updates the 
 * user's view.
 * </p>
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 30. November, 2013
 */

public class MainFrame {

    private static JFrame frame;
    private static JPanel contentPanel;
    
    /**
     * Constructor for creating a new instance of the MainFrame.
     * The constructor calls the buildFrame method which creates the
     * frame, the contentPanel within and a menu bar.
     */
    public MainFrame() {
        buildFrame();
    }
    
    /**
     * The buildFrame method constructs the JFrame and the contentPanel within
     * for the main frame of the MBO EasyTeach application. The frame also has
     * a menu bar that is common among all UIs.
     */
    public void buildFrame() {
        frame = new JFrame("MBO EasyTeach");
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 800);
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        
        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(UIColors.darkBlue);
        frame.setJMenuBar(menuBar);
         
        JMenu mnFile = new JMenu("File");
        mnFile.setBackground(UIColors.darkBlue);
        menuBar.add(mnFile);
        
        JMenuItem mntmLogOut = new JMenuItem("Log out");
        mnFile.add(mntmLogOut);
        
        JMenuItem mntmQuit = new JMenuItem("Quit");
        mnFile.add(mntmQuit);
        
        JMenu mnNavigation = new JMenu("Navigation");
        mnNavigation.setBackground(UIColors.darkBlue);
        menuBar.add(mnNavigation);
        
        JMenuItem mntmHome = new JMenuItem("Home");
        mnNavigation.add(mntmHome);
        
        frame.add(contentPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }   
    
    /**
     * updateContentPanel is the method in charge of updating the contentPanel.
     * It is a static method so that it can be called from other classes 
     * so that a new instance of MainFrame does not have to be created.
     * 
     * @param panel is the view to be inserted into the MainFrame 
     * (e.g. AddUserUI)
     * @param title is the title of the frame 
     */
    public static void updateFrame(JPanel panel, String title) {
        contentPanel.removeAll();
        contentPanel.add(panel);
        frame.setTitle(title);
        frame.revalidate();
    }
    
}
