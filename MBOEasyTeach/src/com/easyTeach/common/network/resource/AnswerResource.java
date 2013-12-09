package com.easyTeach.common.network.resource;

import com.easyTeach.common.entity.Answer;

/**
 * <p>
 * The AnswerResource class is for receiving or sending relevant information
 * from or to our server about an answer.
 * </p>
 * 
 * @author Oliver Nielsen
 * @version 1.0
 * @date 9. December, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters).
 */

public class AnswerResource implements Resource {

	private static final long serialVersionUID = 9106078542720756254L;

	private String questionNo;
	private String answerNo;
	private String answer;

	private boolean isCorrect;

	/**
	 * <p>
	 * This constructor for receiving information for an answer from the server
	 * side of the application.
	 * 
	 * @param questionNo
	 *            The question number which the answer is associated with.
	 * @param answer
	 *            The answer text.
	 * @param isCorrect
	 *            A boolean value if the answer is correct (true) or not
	 *            (false).
	 * 
	 * @see Answer
	 */
	public AnswerResource(String questionNo, String answer, boolean isCorrect) {
		this.questionNo = questionNo;
		this.answer = answer;
		this.isCorrect = isCorrect;
	}

	/**
	 * <p>
	 * This constructor is used for sending information for an answer to the
	 * server side of the application.
	 * </p>
	 * 
	 * @param questionNo
	 *            The question number which the answer is associated with.
	 * @param answerNo
	 *            The answer number.
	 * @param answer
	 *            The answer text.
	 * @param isCorrect
	 *            A boolean value if the answer is correct (true) or not
	 *            (false).
	 * 
	 * @see Answer
	 */
	public AnswerResource(String questionNo, String answerNo, String answer,
			boolean isCorrect) {
		this.questionNo = questionNo;
		this.answerNo = answerNo;
		this.answer = answer;
		this.isCorrect = isCorrect;
	}

	public String getQuestionNo() {
		return questionNo;
	}

	public String getAnswerNo() {
		return answerNo;
	}

	public String getAnswer() {
		return answer;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	@Override
	public String getName() {
		return "AnswerResource";
	}
	
	

}
