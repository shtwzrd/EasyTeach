package com.easyTeach.server.entity;

/** 
 * <p>
 * The Exercise class represents a row from the Exercise table in the 
 * easyTeach database. The class contains getters and setters for the 
 * seven attributes derived from the Exercise table, namely; exerciseNo, 
 * courseNo, author, exerciseParameterNo, exerciseName, dateAdded, password.
 * </p>
 * 
 * <p>
 * In the database the Exercise table's primary key is the composite 
 * of exerciseNo and courseNo.
 * </p>
 * 
 * <p>
 * In the database the courseNo references the primary key (PK) of the Course 
 * table, the author references the PK of the User table and the 
 * exerciseParameterNo references the PK of the ExerciseParameter table.
 * </p>
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 26. November, 2013
 * @obvious Comments for methods are omitted as they are self explanatory 
 *          (getters/setters).
 */

public class Exercise {

    private String exerciseNo;          // INTEGER in DB
    private String courseNo;            // INTEGER in DB
    private String author;              // INTEGER in DB
    private String exerciseParameterNo; // INTEGER in DB
    private String exerciseName;
    private java.sql.Date dateAdded;
    private String password;
    
    public String getExerciseNo() {
        return exerciseNo;
    }
    
    public void setExerciseNo(String exerciseNo) {
        this.exerciseNo = exerciseNo;
    }
    
    public String getCourseNo() {
        return courseNo;
    }
    
    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getExerciseParameterNo() {
        return exerciseParameterNo;
    }
    
    public void setExerciseParameterNo(String exerciseParameterNo) {
        this.exerciseParameterNo = exerciseParameterNo;
    }
    
    public String getExerciseName() {
        return exerciseName;
    }
    
    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }
    
    public java.sql.Date getDateAdded() {
        return dateAdded;
    }
    
    public void setDateAdded(java.sql.Date dateAdded) {
        this.dateAdded = dateAdded;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
}
