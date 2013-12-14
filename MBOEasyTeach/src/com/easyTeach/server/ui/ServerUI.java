package com.easyTeach.server.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.easyTeach.common.ui.UIColors;
/**
 * <p>
 * The ServerUI class is one of the User Interface (UI) class for the Server. It
 * is part of the server side of the application and will be located on server.
 * </p>
 * 
 * <p>
 * The class has a private inner class implements ActionListener for handling
 * triggered events.
 * </p>
 * 
 * @author Morten Faarkrog
 * @version 1.2
 * @date 13. December, 2013
 */

public class ServerUI {

    private JFrame frame;
    private JTextArea textArea;

    /**
     * Constructor for creating new instances of the Server UI. The constructor
     * calls the buildUI method which creates all the JComponents for the UI.
     */
    public ServerUI() {
        buildUI();
    }

    /**
     * The buildUI method constructs the JFrame and all of the components for
     * the ServerUI.
     */
    public void buildUI() {
        this.frame = new JFrame("EasyTeach - Server");
        this.frame.setSize(600, 300);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        this.frame.setContentPane(contentPane);

        // Center panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        this.textArea = new JTextArea();
        this.textArea.setWrapStyleWord(true);
        this.textArea.setLineWrap(true);
        this.textArea.setEditable(false);
        centerPanel.add(new JScrollPane(this.textArea));
        centerPanel.setBackground(UIColors.lightBlue);
        centerPanel.setBorder(new TitledBorder(null, "Server Communication",
                TitledBorder.CENTER, TitledBorder.TOP));

        contentPane.add(centerPanel, BorderLayout.CENTER);

        // South panel - South border
        JPanel southPanel = new JPanel();
        southPanel.setBackground(UIColors.darkBlue);
        southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        contentPane.add(southPanel, BorderLayout.SOUTH);

        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    /**
     * Updates the textArea of the ServerUI by concatenation a String to the end
     * of the messages already appearing in it.
     * 
     * @param update the message to be concatenated.
     */
    public void updateTextArea(String update) {
        this.textArea.setText(this.textArea.getText() + update + "\n");
    }

}