package com.easyTeach.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.easyTeach.client.presenter.HelpPresenter;
import com.easyTeach.client.presenter.ManageUserPresenter;
import com.easyTeach.common.entity.User;
import com.easyTeach.common.ui.UIColors;

/**
 * The ManageUserUI class constructs a JPanel with all the different JComponents
 * needed to add new users and manage old ones.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 4. December, 2013
 */

public class ManageUserUI {

	private JPanel manageUserPanel;
	JComboBox<String> userTypeBox;
	private final String[] USER_TYPES = { "Student", "Teacher", "Admin" };
	JButton btnHelp;
	JButton btnDiscard;
	JButton btnGeneratePassword;
	JButton btnSaveUser;
	JTextField txtFirstName;
	JTextField txtLastname;
	JTextField txtEmail;
	JTable enrolledClassesTable;
	JTable allClassesTable;
	private JPanel centerPanel;
	JButton btnAddClass;
	JButton btnRemoveClass;
	protected ManageUserPresenter presenter;
	private boolean isSyncing;

	/**
	 * Constructor for building the manageUserPanel. The panel is built by
	 * calling the buildPanel method. The constructor also calls a method adding
	 * actionListeners to all buttons.
	 */
	public ManageUserUI() {
		this.presenter = new ManageUserPresenter();
		buildPanel();
		addActionListeners();
	}

	public ManageUserUI(User selectedUser) {
		this.presenter = new ManageUserPresenter(selectedUser);
		buildPanel();
		addActionListeners();
		
		syncWithPresenter();
	}

	/**
	 * Returns the ManageUserPanel with all of the JComponents within.
	 * 
	 * @return the JPanel manageUserPanel
	 */
	public JPanel getManageUserUI() {
		return this.manageUserPanel;
	}

	/**
	 * Builds the ManageUserPanel with all of the JComponenets within.
	 */
	public void buildPanel() {
		this.manageUserPanel = new JPanel(new BorderLayout());

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

		JLabel lblManageUserTitle = new JLabel("Manage User");
		lblManageUserTitle.setForeground(UIColors.white);
		lblManageUserTitle.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		northPanel.add(lblManageUserTitle);

		this.manageUserPanel.add(northPanel, BorderLayout.NORTH);
	}

	/**
	 * Builds the center panel with the needed information to create/edit a new
	 * user (userType, firstname, lastname, email, and enrolled classes).
	 */
	private void buildCenterPanel() {
		this.centerPanel = new JPanel(new BorderLayout());
		this.centerPanel.setBackground(UIColors.lightBlue);

		buildInfoPanel();
		buildClassPanel();

		this.manageUserPanel.add(this.centerPanel, BorderLayout.CENTER);
	}

	/**
	 * Builds the Class panel where a User can be enrolled to classes or be
	 * removed from classes. The panel consists of two tables showing what
	 * classes exist in the system and what classes a user is part of along with
	 * buttons for adding and removing a suer from classes.
	 */
	private void buildClassPanel() {
		JPanel classPanel = new JPanel(new GridLayout(2, 1));
		classPanel.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, new Color(0, 0, 0), null), "Classes",
				TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma",
						Font.PLAIN, 20)));
		classPanel.setBackground(UIColors.lightBlue);
		this.centerPanel.add(classPanel, BorderLayout.CENTER);

		// The panel showing the classes a user is currently enrolled
		JPanel enrolledClassesPanel = new JPanel(new BorderLayout());
		enrolledClassesPanel.setBorder(new TitledBorder(null,
				"Enrolled Classes", TitledBorder.CENTER, TitledBorder.TOP));
		enrolledClassesPanel.setBackground(UIColors.lightBlue);

		// Table showing all enrolled classes
		this.enrolledClassesTable = new JTable();
		this.enrolledClassesTable.setModel(this.presenter.getEnrolledClassesModel());

		JScrollPane enrolledClassesScroll = new JScrollPane(
				this.enrolledClassesTable);
		enrolledClassesScroll.getViewport().setBackground(UIColors.white);
		enrolledClassesPanel.add(enrolledClassesScroll, BorderLayout.CENTER);

		JPanel addRemovePanel = new JPanel();
		addRemovePanel.setBackground(UIColors.lightBlue);
		addRemovePanel.setLayout(new GridLayout(1, 2));

		this.btnRemoveClass = new JButton("Remove User From Class");
		addRemovePanel.add(this.btnRemoveClass);

		this.btnAddClass = new JButton("Add User To Class");
		addRemovePanel.add(this.btnAddClass);

		enrolledClassesPanel.add(addRemovePanel, BorderLayout.SOUTH);

		classPanel.add(enrolledClassesPanel);

		// The panel showing all classes in the system
		JPanel allClassesPanel = new JPanel(new BorderLayout());
		allClassesPanel.setBorder(new TitledBorder(null, "All Classes",
				TitledBorder.CENTER, TitledBorder.TOP));
		allClassesPanel.setBackground(UIColors.lightBlue);
		classPanel.add(allClassesPanel);

		// Table showing all classes
		this.allClassesTable = new JTable();
		this.allClassesTable.setModel(this.presenter.getAllClassesModel());

		JScrollPane allClassesScroll = new JScrollPane(this.allClassesTable);
		allClassesScroll.getViewport().setBackground(UIColors.white);
		allClassesPanel.add(allClassesScroll, BorderLayout.CENTER);
	}

	/**
	 * Builds the Info Panel which is part of the centerPanel. The Info Panel
	 * contains JComponents for inserting the userType, firstName, lastName and
	 * email of a user.
	 */
	private void buildInfoPanel() {
		JPanel infoPanel = new JPanel(new GridLayout(4, 2));
		infoPanel.setBackground(UIColors.lightBlue);
		infoPanel.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, new Color(0, 0, 0), null),
				"User Information", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Tahoma", Font.PLAIN, 20)));

		JLabel lblUserType = new JLabel("User Type:");
		lblUserType.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblUserType.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(lblUserType);

		this.userTypeBox = new JComboBox<>();
		this.userTypeBox.setModel(new DefaultComboBoxModel<>(this.USER_TYPES));
		infoPanel.add(this.userTypeBox);

		JLabel lblFirstName = new JLabel("First name:");
		lblFirstName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(lblFirstName);

		this.txtFirstName = new JTextField();
		infoPanel.add(this.txtFirstName);

		JLabel lblLastName = new JLabel("Last name:");
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		infoPanel.add(lblLastName);

		this.txtLastname = new JTextField();
		infoPanel.add(this.txtLastname);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		infoPanel.add(lblEmail);

		this.txtEmail = new JTextField();
		infoPanel.add(this.txtEmail);

		this.centerPanel.add(infoPanel, BorderLayout.NORTH);
	}

	/**
	 * Builds the South Panel which has buttons for Help, Discard, Generate
	 * Password and Save User.
	 */
	private void buildSouthPanel() {
		JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		southPanel.setBackground(UIColors.darkBlue);

		this.btnHelp = new JButton("Help");
		southPanel.add(this.btnHelp);

		this.btnDiscard = new JButton("Discard");
		southPanel.add(this.btnDiscard);

		this.btnGeneratePassword = new JButton("Generate Password");
		southPanel.add(this.btnGeneratePassword);

		this.btnSaveUser = new JButton("Save User");
		southPanel.add(this.btnSaveUser);

		this.manageUserPanel.add(southPanel, BorderLayout.SOUTH);
	}

	/**
	 * Adds an ActionListener of type ManageUserListener to all the JButtons
	 * from the manageUserPanel.
	 */
	private void addActionListeners() {
		ManageUserListener listener = new ManageUserListener();
		this.btnDiscard.addActionListener(listener);
		this.btnGeneratePassword.addActionListener(listener);
		this.btnHelp.addActionListener(listener);
		this.btnSaveUser.addActionListener(listener);
		
		// Oliver Nielsen start
		this.btnAddClass.addActionListener(listener);
		this.btnRemoveClass.addActionListener(listener);
		
		this.enrolledClassesTable.addMouseListener(listener);
		this.allClassesTable.addMouseListener(listener);
		// Oliver Nielsen end
	}

	protected synchronized void syncWithPresenter() {
		if (!this.isSyncing) {
			this.isSyncing = true;
			
			// Oliver Nielsen start
			if (this.txtFirstName.getText().equals("")) {
				this.txtFirstName.setText(this.presenter.getEditUserFirstName());
			}
			if (this.txtLastname.getText().equals("")) {
				this.txtLastname.setText(this.presenter.getEditUserLastName());
			}
			if (this.txtEmail.getText().equals("")) {
				this.txtEmail.setText(this.presenter.getEditUserEmail());
			}
			// Oliver Nielsen end
			
			this.enrolledClassesTable.setModel(this.presenter
					.getEnrolledClassesModel());
			this.allClassesTable.setModel(this.presenter.getAllClassesModel());
			this.isSyncing = false;
		}
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

	/**
	 * The ManageUserListener class is the class in charge of listening for
	 * events happening in the ManageUserUI (e.g. an user clicking the help
	 * button). When an event occurs the ManageUserListener will send a signal
	 * to the ManageUserPresenter which will in return act upon the event.
	 * 
	 * @author Tonni Hyldgaard, Oliver Nielsen
	 * @version 0.9
	 * @see ActionListener
	 * @date 13. December, 2013
	 */
	private class ManageUserListener implements ActionListener, MouseListener {

		public ManageUserListener() {
			// Empty
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == ManageUserUI.this.btnDiscard) {
				int reply = JOptionPane.showConfirmDialog(null,
						"Are you sure you want discard what you have made?\n"
								+ "Warning: It will not be saved.",
						"Discard Message", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					MainFrame.updateFrame(
							new AdminManagerUI().getAdminManagerUI(),
							"Admin Manager");
				}
			}

			else if (e.getSource() == ManageUserUI.this.btnGeneratePassword) {
				JOptionPane.showMessageDialog(null, "Feature under development!");
			}

			else if (e.getSource() == ManageUserUI.this.btnSaveUser) {
				ManageUserUI.this.presenter.save(
						(String) ManageUserUI.this.userTypeBox
								.getSelectedItem(),
						ManageUserUI.this.txtFirstName.getText(),
						ManageUserUI.this.txtLastname.getText(),
						ManageUserUI.this.txtEmail.getText());
				syncWithPresenter();
			}

			else if (e.getSource() == ManageUserUI.this.btnAddClass) {
				ManageUserUI.this.presenter.add();
				syncWithPresenter();
			}

			else if (e.getSource() == ManageUserUI.this.btnRemoveClass) {
				ManageUserUI.this.presenter.remove();
				syncWithPresenter();
			}

			else if (e.getSource() == ManageUserUI.this.btnHelp) {
				JOptionPane.showMessageDialog(null,
						HelpPresenter.getManageUserHelp(),
						HelpPresenter.getManageUserTitle(),
						JOptionPane.PLAIN_MESSAGE, HelpPresenter.getHelpIcon());
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == ManageUserUI.this.allClassesTable) {
				String email = ManageUserUI.this.allClassesTable.getValueAt(
						ManageUserUI.this.allClassesTable.rowAtPoint(e
								.getPoint()), 0).toString();
				ManageUserUI.this.presenter.setSelectedClassInSelection(email);
			}
			if (e.getSource() == ManageUserUI.this.enrolledClassesTable) {
				String email = ManageUserUI.this.enrolledClassesTable
						.getValueAt(
								ManageUserUI.this.enrolledClassesTable.rowAtPoint(e
										.getPoint()), 0).toString();
				ManageUserUI.this.presenter.setSelectedClassInEnrolled(email);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			//
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			//
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			//
		}

		@Override
		public void mouseExited(MouseEvent e) {
			//
		}
	}

}
