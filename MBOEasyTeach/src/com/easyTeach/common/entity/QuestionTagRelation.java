package com.easyTeach.common.entity;

/**
 * <p>
 * The QuestionTagRelation class represents a row from the UserQuestionState
 * table in the easyTeach database. The class contains getters and setters for
 * the two attributes derived from the QuestionTagRelation table, namely;
 * questionNo and tagNo. The table maps the relation between Questions and Tags.
 * </p>
 * 
 * <p>
 * In the database the QuestionTagRelation table's primary key is the composite
 * of questionNo and tagNo.
 * </p>
 * 
 * <p>
 * In the database the QuestionTagRelation table references the primary key of
 * the Question and Tag tables.
 * </p>
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 26. November, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters/setters).
 */

public class QuestionTagRelation implements Resource {

	private static final long serialVersionUID = -7709188094760631201L;

	private String questionNo;
	private String tagNo;

	public QuestionTagRelation() {

	}

	public QuestionTagRelation(String questionNo, String tagNo) {
		this.questionNo = questionNo;
		this.tagNo = tagNo;
	}

	public String getQuestionNo() {
		return this.questionNo;
	}

	public void setQuestionNo(String questionNo) {
		this.questionNo = questionNo;
	}

	public String getTagNo() {
		return this.tagNo;
	}

	public void setTagNo(String tagNo) {
		this.tagNo = tagNo;
	}

	@Override
	public String getName() {
		return "QuestionTagRelation";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		QuestionTagRelation relation = (QuestionTagRelation) obj;
		return this.questionNo.equals(relation.questionNo)
				&& (this.tagNo.equals(relation.tagNo));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.questionNo == null) ? 0 : this.questionNo.hashCode());
		result = prime * result + this.tagNo.hashCode();
		return result;
	}

}