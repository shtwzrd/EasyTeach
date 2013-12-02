package com.easyTeach.server.entity;

/** 
 * <p>
 * The QuestionTagRelation class represents a row from the UserQuestionState 
 * table in the easyTeach database. The class contains getters and setters 
 * for the two attributes derived from the QuestionTagRelation table, namely; 
 * questionNo and tagNo. The table maps the relation between Questions and 
 * Tags.
 * </p>
 * 
 * <p>
 * In the database the QuestionTagRelation table's primary key is the 
 * composite of questionNo and tagNo.
 * </p>
 * 
 * <p>
 * In the database the QuestionTagRelation table references the primary key 
 * of the Question and Tag tables.
 * </p>
 *  
 * @author Morten Faarkrog
 * @version 1.0
 * @date 26. November, 2013
 * @obvious Comments for methods are omitted as they are self explanatory 
 *          (getters/setters).
 */

public class QuestionTagRelation {
    
    private String questionNo;  // INTEGER in DB
    private String tagNo;       // INTEGER in DB

    public String getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(String questionNo) {
        this.questionNo = questionNo;
    }
    
    public String getTagNo() {
        return tagNo;
    }
    
    public void setTagNo(String tagNo) {
        this.tagNo = tagNo;
    }
    
}