package com.easyTeach.client.network;

import com.easyTeach.common.network.Resource;

/**
 * <p>
 * The ExerciseParameterResource class is for receiving or sending relevant
 * information from our or to server about an exercise parameter which will be
 * handled by the presenter if receiving information or the network class in the
 * server package if sending information.
 * </p>
 * 
 * @author Oliver Nielsen
 * @version 0.2
 * @date 8. December, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters).
 */

public class ExerciseParameterResource implements Resource {

	private static final long serialVersionUID = -6168082755644043893L;

	private boolean isTest;
	private boolean isLocked;

	private java.sql.Date accessBegin;
	private java.sql.Date accessEnd;

	private int timeLimit;

	/**
	 * <p>
	 * Constructor for receiving information for an exercise parameter from the
	 * server side of the application.
	 * </p>
	 * 
	 * <p>
	 * The constructor is also used for sending information for a course to the
	 * server side of the application.
	 * </p>
	 * 
	 * @param isTest
	 *            If the exercise is a test, true otherwise false.
	 * @param isLocked
	 *            If the exercise is locked, true otherwise false.
	 * @param accessBegin
	 *            The date and time of when the exercise can be taken.
	 * @param accessEnd
	 *            The date and time of when the exercise end.
	 * @param timeLimit
	 *            The time limit of a test in minutes.
	 * 
	 * @see ExerciseParameter
	 */
	public ExerciseParameterResource(boolean isTest, boolean isLocked,
			java.sql.Timestamp accessBegin, java.sql.Timestamp accessEnd, int timeLimit) {
		this.isTest = isTest;
		this.isLocked = isLocked;
		this.accessBegin = accessBegin;
		this.accessEnd = accessEnd;
		this.timeLimit = timeLimit;
	}

	public boolean isTest() {
		return isTest;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public java.sql.Date getAccessBegin() {
		return accessBegin;
	}

	public java.sql.Date getAccessEnd() {
		return accessEnd;
	}

	public int getTimeLimit() {
		return timeLimit;
	}

}
