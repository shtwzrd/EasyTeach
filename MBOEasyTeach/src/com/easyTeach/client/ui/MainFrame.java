package com.easyTeach.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

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
 * @version 1.2
 * @date 4. December, 2013
 */

public class MainFrame {

    private static JFrame frame;
    private static JPanel contentPanel;
    private JMenuItem mntmLogOut;
    private JMenuItem mntmQuit;
    private JMenuItem mntmHome;
    
    /**
     * Constructor for creating a new instance of the MainFrame.
     * The constructor calls the buildFrame method which creates the
     * frame, the contentPanel within and a menu bar.
     */
    public MainFrame() {
        buildFrame();
        addActionListeners();
    }
    
    /**
     * The buildFrame method constructs the JFrame and the contentPanel within
     * for the main frame of the MBO EasyTeach application. The frame also has
     * a menu bar that is common among all UIs.
     */
    private void buildFrame() {
        frame = new JFrame("MBO EasyTeach");
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 800);
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        
        buildMenuBar();
        
        frame.add(contentPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Method for creating the Menu bar for the easyTeach application.
     * The Menu bar will be used to log out, exit and navigate. Moreover,
     * the color of the MenuBar changes depending on what operating system
     * one is using.
     */
    private void buildMenuBar() {
        Color color = UIColors.lightBlue;
        if (UIManager.getSystemLookAndFeelClassName().equals(
                "com.apple.laf.AquaLookAndFeel")) {
            color = UIColors.darkBlue;
        }
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(color);
        frame.setJMenuBar(menuBar);
         
        JMenu mnFile = new JMenu("File");
        mnFile.setBackground(color);
        menuBar.add(mnFile);
        
        mntmLogOut = new JMenuItem("Log out");
        mnFile.add(mntmLogOut);
        
        mntmQuit = new JMenuItem("Quit");
        mnFile.add(mntmQuit);
        
        JMenu mnNavigation = new JMenu("Navigation");
        mnNavigation.setBackground(color);
        menuBar.add(mnNavigation);
        
        mntmHome = new JMenuItem("Home");
        mnNavigation.add(mntmHome);
    }
    
    /**
     * Method for adding an ActionListener of type MainFrameListener
     * to the various JMenuItems.
     */
    private void addActionListeners() {
        MainFrameListener listener = new MainFrameListener();
        mntmHome.addActionListener(listener);
        mntmLogOut.addActionListener(listener);
        mntmQuit.addActionListener(listener);
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
    
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - // 
    
    /**
     * <p>
     * The inner private MainFrameListener class is in charge of listening
     * for events happening in the MainFrame (e.g. an user clicking the quit
     * MenuItem in the Menu). 
     * </p>
     * 
     * @author Morten Faarkrog
     * @version 1.0
     * @see ActionListener
     * @date 4. December, 2013
     */
    private class MainFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == mntmHome) {
                // Go to home UI
            }
            
            else if (e.getSource() == mntmLogOut) {
                // Log out
            }
            
            else if (e.getSource() == mntmQuit) {
                int reply = JOptionPane.showConfirmDialog(null, 
                        "Are you sure you want to exit the \"MBO EasyTeach\" application?", 
                        "Exit Message", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    System.exit(1);
                }
            }
        }   
        
    }
    
}
