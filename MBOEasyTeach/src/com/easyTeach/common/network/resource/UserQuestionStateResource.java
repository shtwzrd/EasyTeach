package com.easyTeach.common.network.resource;


/**
 * <p>
 * The UserQuestionStateResource class is for receiving or sending relevant
 * information from or to our server about an users question state.
 * </p>
 * 
 * @author Oliver Nielsen
 * @version 1.0
 * @date 9. December, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters).
 */

public class UserQuestionStateResource implements Resource {

	private static final long serialVersionUID = 1147390704665718441L;

	private String userNo;
	private String questionNo;

	private boolean hasCompleted;

	/**
	 * <p>
	 * This constructor for receiving information for an users question state
	 * from the server side of the application.
	 * </p>
	 * 
	 * <p>
	 * This constructor is also used for sending information for an users
	 * question state to the server side of the application.
	 * </p>
	 * 
	 * @param userNo
	 *            The user.
	 * @param questionNo
	 *            The question number.
	 * @param hasCompleted
	 *            A boolean value which is true if the user has answered the
	 *            question, false otherwise.
	 * 
	 * @see UserQuestionState
	 */
	public UserQuestionStateResource(String userNo, String questionNo,
			boolean hasCompleted) {
		this.userNo = userNo;
		this.questionNo = questionNo;
		this.hasCompleted = hasCompleted;
	}

	public String getUserNo() {
		return userNo;
	}

	public String getQuestionNo() {
		return questionNo;
	}

	public boolean isHasCompleted() {
		return hasCompleted;
	}

	@Override
	public String getName() {
		return "UserQuestionStateResource";
	}

}
