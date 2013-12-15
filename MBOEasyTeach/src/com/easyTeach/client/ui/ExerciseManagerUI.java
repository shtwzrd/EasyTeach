package com.easyTeach.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.easyTeach.client.presenter.ExerciseManagerPresenter;
import com.easyTeach.client.presenter.HelpPresenter;
import com.easyTeach.common.ui.UIColors;

/**
 * The ExerciseManagerUI class constructs a JPanel with all the different
 * JComponents needed to maintain exercises (Add, edit and delete).
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 5. December, 2013
 */

public class ExerciseManagerUI {

	private final String[] FILTER = { "Author", "Exercise Name" };
	private JPanel exerciseManagerPanel;
	ExerciseManagerPresenter presenter;
	JButton btnHelp;
	JButton btnEditExercise;
	JButton btnDeleteExercise;
	JButton btnAddExercise;
	JPanel centerPanel;
	JPanel lowerCenterPanel;
	JTable exerciseTable;
	JPanel filterPanel;
	JComboBox<String> filterBox;
	JButton btnFilter;
	JPanel questionTablePanel;
	JTextField txtFilter;
	JButton btnDuplicateExercise;

	/**
	 * Constructor for building the exerciseManagerPanel. The panel is built by
	 * calling the buildPanel method. The constructor also calls a method adding
	 * actionListeners to all buttons.
	 */
	public ExerciseManagerUI() {
		this.presenter = new ExerciseManagerPresenter();
		buildPanel();
		addActionListeners();
	}

	/**
	 * The getExerciseManagerUI returns the JPanel containing all the components
	 * needed to maintain exercises.
	 * 
	 * @return the JPanel, exerciseManagerPanel. Meant to be used in another
	 *         frame.
	 */
	public JPanel getExerciseManagerUI() {
		return this.exerciseManagerPanel;
	}

	/**
	 * The method responsible for building the exerciseManagerPanel. The panel
	 * consists of all the JComponenets needed to maintain exercises.
	 */
	public void buildPanel() {
		this.exerciseManagerPanel = new JPanel(new BorderLayout());
		this.exerciseManagerPanel.setBackground(UIColors.lightBlue);

		buildCenterPanel();
		buildSouthPanel();
	}

	/**
	 * Method for creating the south panel with buttons for getting help and
	 * editing/deleting/adding exercises.
	 */
	private void buildSouthPanel() {
		JPanel southButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		southButtonPanel.setBackground(UIColors.darkBlue);

		this.btnHelp = new JButton("Help");
		southButtonPanel.add(this.btnHelp);

		this.btnEditExercise = new JButton("Edit Exercise");
		southButtonPanel.add(this.btnEditExercise);

		this.btnDuplicateExercise = new JButton("Duplicate Exercise");
		southButtonPanel.add(this.btnDuplicateExercise);

		this.btnDeleteExercise = new JButton("Delete Exercise");
		southButtonPanel.add(this.btnDeleteExercise);

		this.btnAddExercise = new JButton("Add Exercise");
		southButtonPanel.add(this.btnAddExercise);

		this.exerciseManagerPanel.add(southButtonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Builds the filter panel for filtering exercises.
	 */
	private void buildFilterPanel() {
		this.filterPanel = new JPanel();
		this.filterPanel.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, new Color(0, 0, 0), null),
				"Filter Exercises", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Tahoma", Font.PLAIN, 20)));
		this.filterPanel.setBackground(UIColors.lightBlue);

		JLabel lblFilter = new JLabel("Filter");
		this.filterPanel.add(lblFilter);

		this.filterBox = new JComboBox<>();
		this.filterBox.setModel(new DefaultComboBoxModel<>(this.FILTER));
		this.filterPanel.add(this.filterBox);

		JLabel lblBy = new JLabel("by");
		this.filterPanel.add(lblBy);

		this.txtFilter = new JTextField();
		this.filterPanel.add(this.txtFilter);
		this.txtFilter.setColumns(14);

		this.btnFilter = new JButton("Filter");
		this.filterPanel.add(this.btnFilter);
		this.centerPanel.add(this.filterPanel, BorderLayout.SOUTH);
	}

	/**
	 * Builds the center panel with a JTable for all exercises.
	 */
	private void buildCenterPanel() {
		this.centerPanel = new JPanel(new BorderLayout());
		this.centerPanel.setBackground(UIColors.lightBlue);
		this.centerPanel.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, new Color(0, 0, 0), null), "Exercises",
				TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma",
						Font.PLAIN, 20)));

		this.exerciseTable = new JTable();
		this.exerciseTable.setModel(this.presenter.getExerciseTableModel());

		buildFilterPanel();

		JScrollPane exerciseScroll = new JScrollPane(this.exerciseTable);
		exerciseScroll.getViewport().setBackground(UIColors.white);
		this.centerPanel.add(exerciseScroll, BorderLayout.CENTER);

		this.exerciseManagerPanel.add(this.centerPanel);
	}

	/**
	 * Adds an ActionListener of type ExerciseManagerUIListener to all the
	 * JButtons from the exerciseManagerPanel.
	 */
	private void addActionListeners() {
		ExerciseManagerUIListener listener = new ExerciseManagerUIListener();
		this.btnAddExercise.addActionListener(listener);
		this.btnDeleteExercise.addActionListener(listener);
		this.btnEditExercise.addActionListener(listener);
		this.btnHelp.addActionListener(listener);
		this.btnFilter.addActionListener(listener);
		this.btnDuplicateExercise.addActionListener(listener);
		this.exerciseTable.addMouseListener(listener);
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

	/**
	 * The ExerciseManagerUIListener class is the class in charge of listening
	 * for events happening in the ExerciseManagerUI (e.g. an user clicking the
	 * help button). When an event occurs the ExerciseManagerUIListener will
	 * send a signal to the ExerciseManagerPresenter which will in return act
	 * upon the event.
	 * 
	 * @author Morten Faarkrog
	 * @version 0.1
	 * @see ActionListener
	 * @see ExerciseManagerPresenter
	 * @date 5. December, 2013
	 */
	private class ExerciseManagerUIListener implements ActionListener,
			MouseListener {

		public ExerciseManagerUIListener() {
			// Empty Constructor
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == ExerciseManagerUI.this.btnAddExercise) {
				MainFrame.updateFrame(
						new ManageExerciseInfoUI().getManageExerciseInfoUI(),
						"Manage Exercise Info");
			}

			else if (e.getSource() == ExerciseManagerUI.this.btnDeleteExercise) {
				int reply = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to perform a Delete?",
						"Delete Confirmation", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					ExerciseManagerUI.this.presenter.delete();
				}
			}

			else if (e.getSource() == ExerciseManagerUI.this.btnEditExercise) {
				// TODO
			}

			else if (e.getSource() == ExerciseManagerUI.this.btnHelp) {
				JOptionPane.showMessageDialog(null,
						HelpPresenter.getExerciseManagerHelp(),
						HelpPresenter.getExerciseManagerTitle(),
						JOptionPane.PLAIN_MESSAGE, HelpPresenter.getHelpIcon());
			}

			else if (e.getSource() == ExerciseManagerUI.this.btnFilter) {
				String column = ExerciseManagerUI.this.filterBox
						.getSelectedItem().toString();
				String by = ExerciseManagerUI.this.txtFilter.getText();

				ExerciseManagerUI.this.presenter.filter(column, by);
			}

			else if (e.getSource() == ExerciseManagerUI.this.btnDuplicateExercise) {
				ExerciseManagerUI.this.presenter.duplicate();
			}
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getSource() == ExerciseManagerUI.this.exerciseTable) {
			ExerciseManagerUI.this.presenter
					.setSelectedExercise(ExerciseManagerUI.this.exerciseTable
							.rowAtPoint(arg0.getPoint()));
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) { /* Do nothing */
		}

		@Override
		public void mouseExited(MouseEvent arg0) { /* Do nothing */
		}

		@Override
		public void mousePressed(MouseEvent arg0) { /* Do nothing */
		}

		@Override
		public void mouseReleased(MouseEvent arg0) { /* Do nothing */
		}

	}

}