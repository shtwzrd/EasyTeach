package com.easyTeach.client.presenter;

import com.easyTeach.client.network.EasyTeachClient;
import com.easyTeach.common.entity.Answer;
import com.easyTeach.common.entity.Question;
import com.easyTeach.common.entity.Resource;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.network.Action;
import com.easyTeach.common.network.Action.ActionType;
import com.easyTeach.common.network.Request;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.common.network.Session;

/**
 * The QuestionManagerPresenter communicates with the domain logic on behalf of
 * the QuestionManagerUI, providing all of its logic.
 * 
 * <p>
 * The Listeners in the UI class call the relevant methods in the Presenter in
 * order to update and retrieve information from the domain logic. This is an
 * implementation of the Model View Presenter pattern.
 * </p>
 * 
 * @author Brandon Lucas
 * @version 1.0
 * @date 14. December, 2013
 */
public class QuestionManagerPresenter {

	private QuestionTableModel questionTable;
	private AnswerTableModel answerTable;
	protected ResourceSet filteredQuestionSet;
	protected ResourceSet questionSet;
	protected ResourceSet answers;
	private EasyTeachClient client;
	private Question currentlySelectedQuestion;
	private boolean isFiltered;

	private String[] answerColumnHeaders = { "Answer", "Is Correct" };
	private String[] questionColumnHeaders = { "Question Type", "Question" };

	/**
	 * Initializes sets and table models
	 */
	public QuestionManagerPresenter() {
		this.questionSet = new ResourceSet();
		this.answers = new ResourceSet();
		this.questionTable = new QuestionTableModel(this.questionColumnHeaders,
				this.questionSet);

		this.answerTable = new AnswerTableModel(this.answerColumnHeaders,
				this.answers);

		refreshTable();
	}

	public QuestionTableModel getQuestionTableModel() {
		return this.questionTable;
	}

	public AnswerTableModel getAnswerTableModel() {
		return this.answerTable;
	}

	public void setSelectedQuestion(int row) {
		this.currentlySelectedQuestion = (Question) this.questionTable
				.getResourceAtRow(row);
		getAnswers(this.currentlySelectedQuestion);
	}

	public Question getSelectedQuestion() {
		return this.currentlySelectedQuestion;
	}

	private ResourceSet getAnswers(Question question) {
		Action readAnswers = new Action(ActionType.READ, "answer");
		Request getAnswers = new Request(Session.getInstance(),
				readAnswers, question);

		this.client = new EasyTeachClient(getAnswers);
		this.client.run();
		ResourceSet answerSet = (ResourceSet) this.client.getResponse()
				.getResponse();

		this.answerTable.refreshData(answerSet);
		this.answerTable.fireTableDataChanged();

		return answerSet;
	}

	/**
	 * Sends a {@link Request} to the Server, updating the Presenter's set of
	 * Questions.
	 * 
	 * <p>
	 * It checks if the data in the table should be filtered, and updates the
	 * Table Model accordingly, before firing the dataChanged event and forcing
	 * the table in the UI to update.
	 * </p>
	 */
	private void refreshTable() {

		Action toDo = new Action(ActionType.READ, "all");

		Request getQuestions = new Request(Session.getInstance(), toDo,
				new Question());
		this.client = new EasyTeachClient(getQuestions);
		this.client.run();
		this.client.getResponse();
		if (this.client.getResponse().getStatus() != ResponseStatus.FAILURE) {
			this.questionSet = (ResourceSet) this.client.getResponse()
					.getResponse();
		}
		if (this.isFiltered) {
			this.questionTable.refreshData(this.filteredQuestionSet);
		} else {
			this.questionTable.refreshData(this.questionSet);
		}

		this.questionTable.fireTableDataChanged();
	}

	/**
	 * Logic for filtering the table containing the selection of questions by a
	 * particular string, in a particular column.
	 * 
	 * <p>
	 * Right now, this consists of maintaining a separate, filtered set. This is
	 * somewhat slow. Later, new logic should be added, using RowFilter.
	 * </p>
	 * 
	 * @param column
	 *            The column in the table which should be considered for
	 *            filtering
	 * @param by
	 *            The string by which the aforementioned column should be
	 *            filtered.
	 */
	public void filter(String column, String by) {
		if (by.equals("")) {
			this.isFiltered = false;
		} else {
			this.isFiltered = true;
			this.filteredQuestionSet = new ResourceSet();
			switch (column) {
			case "Question Type":
				for (Resource r : this.questionSet) {
					Question q = (Question) r;
					if (q.getQuestionType().contains(by)) {
						this.filteredQuestionSet.add(q);
					}
				}
				break;
			}
		}
		this.refreshTable();
		this.questionTable.refreshData(this.filteredQuestionSet);
		this.questionTable.fireTableDataChanged();
	}

	/**
	 * Removes a Question from the QuestionTable.
	 */
	public void delete() {
		if (this.currentlySelectedQuestion != null) {
			Action rm = new Action(ActionType.DELETE);
			Request rmExercise = new Request(Session.getInstance(), rm,
					this.currentlySelectedQuestion);

			this.client = new EasyTeachClient(rmExercise);
			this.client.run();
			this.refreshTable();
			this.questionTable.refreshData(this.questionSet);
			this.questionTable.fireTableDataChanged();
		}
	}

	/**
	 * 
	 * @author Brandon Lucas
	 * @version 1.0
	 * @date 14 December, 2013
	 * 
	 *       Presenter-specific implementation of the abstract
	 *       DisplayTableModel.
	 * 
	 *       <p>
	 *       Implementation of the DisplayTableModel on a per-Presenter basis
	 *       allows us to interact rows in the table model as actual domain
	 *       objects, specific to the demands of the particular user-interface.
	 *       </p>
	 * 
	 */
	private class QuestionTableModel extends DisplayTableModel {
		public QuestionTableModel(String[] columnHeaders, ResourceSet resources) {
			super(columnHeaders, resources);
		}

		private static final long serialVersionUID = -2996998684634631118L;

		@Override
		public String getValueAt(int row, int column) {
			Question q = (Question) this.tableData.get(row);
			switch (this.getColumnName(column)) {
			case "Question Type":
				return q.getQuestionType();
			case "Question":
				return q.getQuestion();
			default:
				return new String();
			}
		}
	}

	/**
	 * 
	 * @author Brandon Lucas
	 * @version 1.0
	 * @date 14 December, 2013
	 * 
	 *       Presenter-specific implementation of the abstract
	 *       DisplayTableModel.
	 * 
	 *       <p>
	 *       Implementation of the DisplayTableModel on a per-Presenter basis
	 *       allows us to interact rows in the table model as actual domain
	 *       objects, specific to the demands of the particular user-interface.
	 *       </p>
	 * 
	 */
	private class AnswerTableModel extends DisplayTableModel {
		public AnswerTableModel(String[] columnHeaders, ResourceSet resources) {
			super(columnHeaders, resources);
		}

		private static final long serialVersionUID = -2996998684634631118L;

		@Override
		public String getValueAt(int row, int column) {
			Answer a = (Answer) this.tableData.get(row);
			switch (this.getColumnName(column)) {
			case "Answer":
				return a.getAnswer();
			case "Is Correct":
				return Boolean.toString(a.getIsCorrect());
			default:
				return new String();
			}
		}
	}
}
