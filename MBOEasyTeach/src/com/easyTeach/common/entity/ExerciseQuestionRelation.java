package com.easyTeach.common.entity;

/** 
 * <p>
 * The ExerciseQuestionRelation class represents a row from the 
 * ExerciseQuestionRelation table in the easyTeach database. The class 
 * contains getters and setters for the two attributes derived from the 
 * ExerciseQuestionRelation table, namely; exerciseNo and questionNo.
 * The table maps the relation between Exercises and Questions.
 * </p>
 * 
 * <p>
 * In the database the ExerciseQuestionRelation table's primary key is the 
 * composite of exerciseNo and questionNo.
 * </p>
 * 
 * <p>
 * In the database the ExerciseQuestionRelation table references the 
 * primary key of the Exercise and Question tables.
 * </p>
 *  
 * @author Morten Faarkrog
 * @version 1.0
 * @date 26. November, 2013
 * @obvious Comments for methods are omitted as they are self explanatory 
 *          (getters/setters).
 */

public class ExerciseQuestionRelation {
    
    private String exerciseNo;  // INTEGER in DB
    private String questionNo;  // INTEGER in DB
    
    public String getExerciseNo() {
        return exerciseNo;
    }
    
    public void setExerciseNo(String exerciseNo) {
        this.exerciseNo = exerciseNo;
    }
    
    public String getQuestionNo() {
        return questionNo;
    }
    
    public void setQuestionNo(String questionNo) {
        this.questionNo = questionNo;
    }
    
}