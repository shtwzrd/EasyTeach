package com.easyTeach.common.entity;

/**
 * <p>
 * The Question class represents a row from the Question table in the easyTeach
 * database. The class contains getters and setters for the four attributes
 * derived from the Question table, namely; questionNo, questionType, question
 * and points.
 * </p>
 * 
 * <p>
 * In the database the primary key of the Question table is the questionNo.
 * </p>
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 26. November, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters/setters).
 */

public class Question implements Resource {

	private static final long serialVersionUID = -4370845973860797037L;
	private String questionNo; 

	private String questionType;
	private String question;
	private int points;

	public Question() {

	}

	public Question(String questionNo, String questionType, String question,
			int points) {
		this.questionNo = questionNo;
		this.questionType = questionType;
		this.question = question;
		this.points = points;
	}

	public String getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(String questionNo) {
		this.questionNo = questionNo;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String getName() {
		return "Question";
	}

}
