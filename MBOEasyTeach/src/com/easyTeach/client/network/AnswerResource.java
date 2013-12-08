package com.easyTeach.client.network;

import com.easyTeach.common.entity.Question;
import com.easyTeach.common.network.Resource;

/**
 * <p>
 * The AnswerResource class is for receiving or sending relevant information
 * from or to our server about an answer.
 * </p>
 * 
 * @author Oliver Nielsen
 * @version 0.2
 * @date 8. December, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters).
 */

public class AnswerResource implements Resource {

	private static final long serialVersionUID = 9106078542720756254L;

	private String questionNo;
	private String answer;

	private boolean isCorrect;

	/**
	 * <p>
	 * Constructor for receiving information for an answer from the server side
	 * of the application.
	 * </p>
	 * 
	 * <p>
	 * The constructor is also used for sending information for an answer to the
	 * server side of the application.
	 * </p>
	 * 
	 * @param questionNo
	 *            The question number which the answer is associated with.
	 * @param answer
	 *            The answer text.
	 * @param isCorrect
	 *            A boolean value if the answer is correct (true) or not
	 *            (false).
	 * 
	 * @see Question
	 */
	public AnswerResource(String questionNo, String answer, boolean isCorrect) {
		this.questionNo = questionNo;
		this.answer = answer;
		this.isCorrect = isCorrect;
	}

	public String getQuestionNo() {
		return questionNo;
	}

	public String getAnswer() {
		return answer;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

}
