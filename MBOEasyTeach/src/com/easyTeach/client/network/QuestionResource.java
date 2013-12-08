package com.easyTeach.client.network;

import java.util.HashSet;

import com.easyTeach.common.entity.Question;
import com.easyTeach.common.network.Resource;

/**
 * <p>
 * The QuestionResource class is for receiving or sending relevant information
 * from or to our server about a question.
 * </p>
 * 
 * @author Oliver Nielsen
 * @version 0.2
 * @date 8. December, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters).
 */

public class QuestionResource implements Resource {

	private static final long serialVersionUID = -3141482955108176998L;

	private String questionType;
	private String question;

	private int points;

	private HashSet<TagResource> tags;

	private HashSet<AnswerResource> answer;

	/**
	 * <p>
	 * Constructor for receiving information for a question from the server side
	 * of the application.
	 * </p>
	 * 
	 * <p>
	 * The constructor is also used for sending information for a question to
	 * the server side of the application.
	 * </p>
	 * 
	 * @param questionType
	 *            The type of the question.
	 * @param question
	 *            The question.
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

}
