package com.easyTeach.common.entity;

/**
 * <p>
 * The UserQuestionState class represents a row from the UserQuestionState table
 * in the easyTeach database. The class contains getters and setters for the two
 * attributes derived from the UserQuestionState table, namely; userNo and
 * questionNo.
 * </p>
 * 
 * <p>
 * In the database the UserQuestionState table's primary key is the composite of
 * userNo and questionNo.
 * </p>
 * 
 * <p>
 * In the database the UserQuestionState table references the primary key of the
 * Question and User tables.
 * </p>
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 26. November, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters/setters).
 */

public class UserQuestionState implements Resource {

	private static final long serialVersionUID = 7334694504064972021L;
	private String userNo; 
	private String questionNo; 
	private boolean hasCompleted;

	public UserQuestionState() {

	}

	public UserQuestionState(String userNo, String questionNo,
			boolean hasCompleted) {
		this.userNo = userNo;
		this.questionNo = questionNo;
		this.hasCompleted = hasCompleted;
	}

	public String getUserNo() {
		return this.userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getQuestionNo() {
		return this.questionNo;
	}

	public void setQuestionNo(String questionNo) {
		this.questionNo = questionNo;
	}

	public boolean getHasCompleted() {
		return this.hasCompleted;
	}

	public void setHasCompleted(boolean hasCompleted) {
		this.hasCompleted = hasCompleted;
	}

	@Override
	public String getName() {
		return "UserQuestionState";
	}

}
