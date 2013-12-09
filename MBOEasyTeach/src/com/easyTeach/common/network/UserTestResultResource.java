package com.easyTeach.common.network;

import com.easyTeach.common.entity.UserTestResult;
import com.easyTeach.common.network.resource.Resource;

/**
 * <p>
 * The UserTestResultResource class is for receiving or sending relevant
 * information from or to our server about an users test result.
 * </p>
 * 
 * @author Oliver Nielsen
 * @version 1.0
 * @date 9. December, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters).
 */

public class UserTestResultResource implements Resource {

	private static final long serialVersionUID = 1603382769834372491L;

	private String userNo;
	private String exerciseNo;

	private int score;

	/**
	 * <p>
	 * This constructor for receiving information for an users test result from
	 * the server side of the application.
	 * </p>
	 * 
	 * <p>
	 * This constructor is also used for sending information for an users test
	 * result to the server side of the application.
	 * </p>
	 * 
	 * @param userNo
	 *            The user number.
	 * @param exerciseNo
	 *            The exercise number.
	 * @param score
	 *            The score the user got from the exercise.
	 * 
	 * @see UserTestResult
	 */
	public UserTestResultResource(String userNo, String exerciseNo, int score) {
		this.userNo = userNo;
		this.exerciseNo = exerciseNo;
		this.score = score;
	}

	public String getUserNo() {
		return userNo;
	}

	public String getExerciseNo() {
		return exerciseNo;
	}

	public int getScore() {
		return score;
	}

	@Override
	public String getName() {
		return "UserTestResultResource";
	}

}
