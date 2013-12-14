package com.easyTeach.client.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.easyTeach.client.presenter.HelpPresenter;
import com.easyTeach.client.presenter.LoginPresenter;
import com.easyTeach.common.ui.UIColors;

/**
 * <p>
 * The LoginUI class is one of the User Interface (UI) classes for the
 * "MBO EasyTeach "application. It is part of the client side of the application
 * and will be located on users' computers.
 * </p>
 * 
 * <p>
 * The class has a private inner class implements ActionListener for handling
 * triggered events.
 * </p>
 * 
 * @author Morten Faarkrog, Brandon Lucas
 * @version 1.2
 * @date 4. December, 2013
 */

public class LoginUI {

	LoginPresenter loginPresenter;
	JFrame frame;
	JTextField txtUsername;
	JPasswordField txtPassword;
	JButton btnLogin;
	JButton btnHelp;
	JButton btnQuit;

	/**
	 * Constructor for creating new instances of the Login UI. The constructor
	 * calls the buildUI method which creates all the JComponents for the UI.
	 */
	public LoginUI() {
		this.loginPresenter = new LoginPresenter();
		buildUI();
	}

	/**
	 * The buildUI method constructs the JFrame and all of the components for
	 * the LoginUI. Moreover, an ActionListener that listens to events is added
	 * to all fields and buttons.
	 */
	public void buildUI() {
		this.frame = new JFrame("EasyTeach - Login");
		this.frame.setSize(280, 140);
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		this.frame.setContentPane(contentPane);

		// Center panel
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(UIColors.lightBlue);
		centerPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP));
		// GridBagLayout is used to position things more precisely.
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWeights = new double[] { 0, 0, 1,
				Double.MIN_VALUE };
		gbl_centerPanel.rowWeights = new double[] { 0, 0, Double.MIN_VALUE };
		centerPanel.setLayout(gbl_centerPanel);

		JLabel lblUsername = new JLabel("Username");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		centerPanel.add(lblUsername, gbc_lblUsername);

		this.txtUsername = new JTextField();
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.insets = new Insets(0, 0, 5, 0);
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.gridx = 2;
		gbc_txtUsername.gridy = 0;
		centerPanel.add(this.txtUsername, gbc_txtUsername);
		this.txtUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		centerPanel.add(lblPassword, gbc_lblPassword);

		this.txtPassword = new JPasswordField();
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 2;
		gbc_txtPassword.gridy = 1;
		centerPanel.add(this.txtPassword, gbc_txtPassword);

		contentPane.add(centerPanel, BorderLayout.CENTER);

		// Button panel - South border
		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(UIColors.darkBlue);
		btnPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		contentPane.add(btnPanel, BorderLayout.SOUTH);

		this.btnLogin = new JButton("Login");
		btnPanel.add(this.btnLogin);

		this.btnHelp = new JButton("Help");
		btnPanel.add(this.btnHelp);

		this.btnQuit = new JButton("Quit");
		btnPanel.add(this.btnQuit);

		// Button listeners
		addActionListeners();

		this.frame.setResizable(false);
		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}

	/**
	 * Method in charge of adding an ActionListener to the various buttons and
	 * fields in the LoginUI.
	 */
	private void addActionListeners() {
		LoginUIListener listener = new LoginUIListener();
		this.btnLogin.addActionListener(listener);
		this.btnHelp.addActionListener(listener);
		this.btnQuit.addActionListener(listener);
		this.txtUsername.addActionListener(listener);
		this.txtPassword.addActionListener(listener);
		
	}
	
	void dispose() {
		this.frame.dispose();
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

	/**
	 * <p>
	 * The inner private LoginUIListener class is in charge of listening for
	 * events happening in the LoginUI (e.g. an user clicking the help button).
	 * When an event occurs the LoginUIListener will send a signal to the
	 * LoginPresenter which will in return act upon the event.
	 * </p>
	 * 
	 * @author Morten Faarkrog
	 * @version 1.0
	 * @see ActionListener
	 * @date 28. November, 2013
	 */
	private class LoginUIListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == LoginUI.this.btnLogin) {
				String username = LoginUI.this.txtUsername.getText();
				String password = new String(LoginUI.this.txtPassword.getPassword());

				if (LoginUI.this.loginPresenter.validateUsername(username)
						&& LoginUI.this.loginPresenter.canLogin(username,
								password)) {
					if (LoginUI.this.loginPresenter.getAuthorizationLevel() != null) {
						new MainFrame();
						switch (LoginUI.this.loginPresenter
								.getAuthorizationLevel()) {
						case ADMIN:
							MainFrame.updateFrame(
									new AdminManagerUI().getAdminManagerUI(),
									"Admin Manager");
							break;
						case TEACHER:
							MainFrame.updateFrame(new TeacherManagerUI()
									.getTeacherManagerUI(), "Teacher Manager");
							break;
						case STUDENT:
							MainFrame.updateFrame(new StudentManagerUI()
									.getStudentManagerUI(), "Student Manager");
							break;
						}
					LoginUI.this.frame.dispose();
					} else {
						JOptionPane
								.showMessageDialog(null,
										"Username and Password do not match. Please try again.");
					}

				} else {
					JOptionPane
							.showMessageDialog(null,
									"Username and Password do not match. Please try again.");
				}
			}

			else if (e.getSource() == LoginUI.this.txtPassword) {
				LoginUI.this.btnLogin.doClick();
			}

			else if (e.getSource() == LoginUI.this.txtUsername) {
				LoginUI.this.btnLogin.doClick();
			}

			else if (e.getSource() == LoginUI.this.btnHelp) {
				JOptionPane.showMessageDialog(null,
						HelpPresenter.getLoginHelp(),
						HelpPresenter.getLoginTitle(),
						JOptionPane.PLAIN_MESSAGE, HelpPresenter.getHelpIcon());
			}

			else if (e.getSource() == LoginUI.this.btnQuit) {
				int reply = JOptionPane
						.showConfirmDialog(
								null,
								"Are you sure you want to go to quit the \"MBO EasyTeach\" application?",
								"Quit Message", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					LoginUI.this.dispose();
				}
			}
		}
	}
}