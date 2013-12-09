package com.easyTeach.common.network;

import java.util.HashSet;

import com.easyTeach.common.entity.Exercise;
import com.easyTeach.common.network.Resource;

/**
 * <p>
 * The ExerciseResource class is for receiving or sending relevant information
 * from or to our server about an exercise.
 * </p>
 * 
 * @author Oliver Nielsen
 * @version 1.0
 * @date 9. December, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters).
 */

public class ExerciseResource implements Resource {

	private static final long serialVersionUID = -1099918936461854937L;

	private String exerciseNo;
	private String courseNo;
	private String userNo; // Author
	private String exerciseName;

	private java.sql.Date dateAdded;

	private String password;

	private boolean isTest;
	private boolean isLocked;

	private java.sql.Timestamp accessBegin;
	private java.sql.Timestamp accessEnd;

	private int timeLimit;

	private HashSet<QuestionResource> questions;

	// RECEIVING INFORMATION
	/**
	 * <p>
	 * This constructor for receiving information for a test from the server
	 * side of the application.
	 * </p>
	 * 
	 * @param courseNo
	 *            The number for the specific course the test is for.
	 * @param userNo
	 *            The user number of who made the test. (Author)
	 * @param exerciseName
	 *            The name of the exercise.
	 * @param dateAdded
	 *            The date of the creating of the exercise.
	 * @param isLocked
	 *            If the exercise is locked, true otherwise false.
	 * @param accessBegin
	 *            The date and time of when the exercise can be taken.
	 * @param accessEnd
	 *            The date and time of when the exercise end.
	 * @param timeLimit
	 *            The time limit of a test in minutes.
	 * @param questions
	 *            The question(s) associated with the exercise.
	 * 
	 * @see Exercise
	 */
	public ExerciseResource(String courseNo, String userNo,
			String exerciseName, java.sql.Date dateAdded, boolean isLocked,
			java.sql.Timestamp accessBegin, java.sql.Timestamp accessEnd,
			int timeLimit, HashSet<QuestionResource> questions) {
		this.courseNo = courseNo;
		this.userNo = userNo;
		this.exerciseName = exerciseName;
		this.dateAdded = dateAdded;
		this.isLocked = isLocked;
		this.accessBegin = accessBegin;
		this.accessEnd = accessEnd;
		this.timeLimit = timeLimit;
		this.questions = questions;
		this.isTest = true;
	}

	/**
	 * <p>
	 * This constructor for receiving information for a quiz from the server
	 * side of the application.
	 * </p>
	 * 
	 * @param courseNo
	 *            The number for the specific course the test is for.
	 * @param userNo
	 *            The user number of who made the test. (Author)
	 * @param exerciseName
	 *            The name of the exercise.
	 * @param dateAdded
	 *            The date of the creating of the exercise.
	 * @param questions
	 *            The question(s) associated with the exercise.
	 * 
	 * @see Exercise
	 */
	public ExerciseResource(String exerciseNo, String courseNo, String userNo,
			String exerciseName, java.sql.Date dateAdded,
			HashSet<QuestionResource> questions) {
		this.courseNo = courseNo;
		this.userNo = userNo;
		this.exerciseName = exerciseName;
		this.dateAdded = dateAdded;
		this.questions = questions;
		this.isTest = false;
	}

	// SENDING INFORMATION
	/**
	 * <p>
	 * This constructor for sending information for a test to the server side of
	 * the application.
	 * </p>
	 * 
	 * @param exerciseNo
	 *            The exercise number.
	 * @param courseNo
	 *            The number for the specific course the test is for.
	 * @param userNo
	 *            The user number of who made the test. (Author)
	 * @param exerciseName
	 *            The name of the exercise.
	 * @param password
	 *            Password for the exercise.
	 * @param isLocked
	 *            If the exercise is locked, true otherwise false.
	 * @param accessBegin
	 *            The date and time of when the exercise can be taken.
	 * @param accessEnd
	 *            The date and time of when the exercise end.
	 * @param timeLimit
	 *            The time limit of a test in minutes.
	 * @param questions
	 *            The question(s) associated with the exercise.
	 * 
	 * @see Exercise
	 */
	public ExerciseResource(String exerciseNo, String courseNo, String userNo,
			String exerciseName, String password, boolean isLocked,
			java.sql.Timestamp accessBegin, java.sql.Timestamp accessEnd,
			int timeLimit, HashSet<QuestionResource> questions) {
		this.exerciseNo = exerciseNo;
		this.courseNo = courseNo;
		this.userNo = userNo;
		this.exerciseName = exerciseName;
		this.password = password;
		this.isLocked = isLocked;
		this.accessBegin = accessBegin;
		this.accessEnd = accessEnd;
		this.timeLimit = timeLimit;
		this.questions = questions;
		this.isTest = true;
	}

	/**
	 * <p>
	 * This constructor for sending information for a quiz to the server side of
	 * the application.
	 * </p>
	 * 
	 * @param exerciseNo
	 *            The exercise number.
	 * @param courseNo
	 *            The number for the specific course the test is for.
	 * @param userNo
	 *            The user number of who made the test. (Author)
	 * @param exerciseName
	 *            The name of the exercise.
	 * @param questions
	 *            The question(s) associated with the exercise.
	 * 
	 * @see Exercise
	 */
	public ExerciseResource(String exerciseNo, String courseNo, String userNo,
			String exerciseName, HashSet<QuestionResource> questions) {
		this.exerciseNo = exerciseNo;
		this.courseNo = courseNo;
		this.userNo = userNo;
		this.exerciseName = exerciseName;
		this.questions = questions;
		this.isTest = false;
	}

	public String getExerciseNo() {
		return exerciseNo;
	}

	public String getCourseNo() {
		return courseNo;
	}

	public String getUserNo() {
		return userNo;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public String getPassword() {
		return password;
	}

	public java.sql.Date getDateAdded() {
		return dateAdded;
	}

	public boolean getIsTest() {
		return isTest;
	}

	public boolean getIsLocked() {
		return isLocked;
	}

	public java.sql.Timestamp getAccessBegin() {
		return accessBegin;
	}

	public java.sql.Timestamp getAccessEnd() {
		return accessEnd;
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	public HashSet<QuestionResource> getQuestions() {
		return questions;
	}

}
