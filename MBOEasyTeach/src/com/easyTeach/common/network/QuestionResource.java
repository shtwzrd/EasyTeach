package com.easyTeach.common.network;

public class QuestionResource implements Resource {

	private static final long serialVersionUID = 1L;
	private String questionType;
	private String questionText;
	private int points;

    public QuestionResource(String questionType, String questionText, int points) { 
		this.questionType = questionType;
		this.questionText = questionText;
		this.points = points;
    }

	public void setQuestionType(String questionType) {
    	this.questionType = questionType;
	}

	public String getQuestionType() {    
		return questionType;
	}

	public void setQuestionText(String questionText) {
    	this.questionText = questionText;
	}

	public String getQuestionText() {    
		return questionText;
	}

	public void setPoints(int points) {
    	this.points = points;
	}

	public int getPoints() {    
		return points;
	}

}
