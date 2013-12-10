package com.easyTeach.common.network.resource;


import java.util.HashSet;

import com.easyTeach.common.entity.Question;

/**
 * <p>
 * The QuestionResource class is for receiving or sending relevant information
 * from or to our server about a question.
 * </p>
 * 
 * @author Oliver Nielsen
 * @version 1.0
 * @date 9. December, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters).
 */

public class QuestionResource implements Resource {

	private static final long serialVersionUID = -3141482955108176998L;

	private String questionNo;
	private String questionType;
	private String question;

	private int points;

	private HashSet<TagResource> tags;

	private HashSet<AnswerResource> answer;

	/**
	 * <p>
	 * This constructor for receiving information for a question from the server
	 * side of the application.
	 * </p>
	 * 
	 * @param questionType
	 *            The type of the question.
	 * @param question
	 *            The question text.
	 * @param points
	 *            The points for a correct answer for this question.
	 * @param tags
	 *            The tag(s) associated with the question.
	 * @param answers
	 *            The answer(s) for the particular question.
	 * @see Question
	 */
	public QuestionResource(String questionType, String question, int points,
			HashSet<TagResource> tags, HashSet<AnswerResource> answers) {
		this.questionType = questionType;
		this.question = question;
		this.points = points;
		this.tags = tags;
		this.answer = answers;
	}

	/**
	 * <p>
	 * This constructor is used for sending information for a question to the
	 * server side of the application.
	 * </p>
	 * 
	 * @param questionNo
	 *            The question number.
	 * @param questionType
	 *            The type of the question.
	 * @param question
	 *            The question text.
	 * @param points
	 *            The points for a correct answer for this question.
	 * @param tags
	 *            The tag(s) associated with the question.
	 * @param answers
	 *            The answer(s) for the particular question.
	 * @see Question
	 */
	public QuestionResource(String questionNo, String questionType,
			String question, int points, HashSet<TagResource> tags,
			HashSet<AnswerResource> answers) {
		this.questionNo = questionNo;
		this.questionType = questionType;
		this.question = question;
		this.points = points;
		this.tags = tags;
		this.answer = answers;
	}
	
	/**
	 * <p>
	 * This constructor is used for sending information for a question to the
	 * server side of the application.
	 * </p>
	 * 
	 * @param questionNo
	 *            The question number.
	 * @param questionType
	 *            The type of the question.
	 * @param question
	 *            The question text.
	 * @param points
	 *            The points for a correct answer for this question.
	 * @see Question
	 */
	public QuestionResource(String questionNo, String questionType,
			String question, int points) {
		this.questionNo = questionNo;
		this.questionType = questionType;
		this.question = question;
		this.points = points;
	}

	public String getQuestionNo() {
		return questionNo;
	}

	public String getQuestionType() {
		return questionType;
	}

	public String getQuestion() {
		return question;
	}

	public int getPoints() {
		return points;
	}

	public HashSet<TagResource> getTags() {
		return tags;
	}

	public HashSet<AnswerResource> getAnswer() {
		return answer;
	}

	@Override
	public String getName() {
		return "QuestionResource";
	}

}
