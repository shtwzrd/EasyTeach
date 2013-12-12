package com.easyTeach.server.domainLogic;

import com.easyTeach.common.entity.Answer;
import com.easyTeach.common.entity.Question;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.server.databaseWrapper.AnswerWrapper;

/**
 * 
 * Class used for manipulating with {@link Answer} entities. It contains, among
 * other things, the logic for calling the CRUD procedures from the
 * {@link AnswerWrapper}. The constructor is private as there should never be
 * created an instance of the AnswerRules class itself.
 * 
 * @author Oliver Nielsen
 * @version 0.1
 * @date 12. December, 2013
 */

public class AnswerRules {

	private AnswerRules() {
		// Empty constructor
	}

	/**
	 * @param questionEntity
	 *            Question entity containing the questionNo for the question
	 *            that should be returned.
	 * @return A Response object with a success status and a {@link ResourceSet}
	 *         of all the answers associated with a questionNo.
	 */
	public static Response getAnswers(Question questionEntity) {
		ResourceSet answers = new ResourceSet();

		for (Answer answer : AnswerWrapper
				.getAnswerRowsWithQuestionNo(questionEntity.getQuestionNo())) {
			answers.add(answer);
		}
		return new Response(ResponseStatus.SUCCESS, answers);
	}

	/**
	 * @param answerEntity
	 *            Answer entity containing the questionNo.
	 * @return A Response object with a success status and the correct answer
	 *         for a specific questionNo.
	 */
	public static Response getCorrectAnswer(Answer answerEntity) {
		Answer correctAnswer = new Answer();

		for (Answer answer : AnswerWrapper
				.getAnswerRowsWithQuestionNo(answerEntity.getQuestionNo())) {
			if (answer.getIsCorrect()) {
				correctAnswer = answer;
				break;
			}
		}
		return new Response(ResponseStatus.SUCCESS, correctAnswer);
	}

	/**
	 * @param answerEntity
	 *            Answer entity that contains new information for what that
	 *            should be updated for a specific answerNo and questionNo.
	 * @return A Response object with a success status if the Answer was
	 *         updated. If not false.
	 */
	public static Response updateAnswer(Answer answerEntity) {
		if (AnswerWrapper.updateAnswerRow(answerEntity)) {
			return new Response(ResponseStatus.SUCCESS);
		}
		return new Response(ResponseStatus.FAILURE);
	}

	/**
	 * @param answerEntity
	 *            Answer entity that contains (at least) the answerNo and the
	 *            questionNo.
	 * @return A Response object with a success status if the Answer was
	 *         deleted. If not false.
	 */
	public static Response deleteAnswer(Answer answerEntity) {
		if (AnswerWrapper.deleteAnswerRow(answerEntity.getQuestionNo(),
				answerEntity.getAnswerNo())) {
			return new Response(ResponseStatus.SUCCESS);
		}
		return new Response(ResponseStatus.FAILURE);
	}

	/**
	 * @param answerEntity
	 *            Answer entity that should be added.
	 * @return A Response object with a success status if the Answer was added.
	 *         If not false.
	 */
	public static Response addAnswer(Answer answerEntity) {
		if (AnswerWrapper.insertIntoAnswer(answerEntity)) {
			return new Response(ResponseStatus.SUCCESS);
		}
		return new Response(ResponseStatus.FAILURE);
	}

}
