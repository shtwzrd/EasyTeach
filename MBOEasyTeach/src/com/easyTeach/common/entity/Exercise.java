package com.easyTeach.common.entity;

import java.sql.Timestamp;

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

public class Exercise implements Resource {

	private static final long serialVersionUID = -3283333993528405399L;
	private String exerciseNo;          
	private String courseNo;            
    private String author;              
    private String exerciseParameterNo; 
    private String exerciseName;
    private Timestamp dateAdded;
    private String password;
    
    public Exercise() {
    	
    }
    
    public Exercise(String exerciseNo, String courseNo, String author,
			String exerciseParameterNo, String exerciseName, Timestamp dateAdded,
			String password) {
		this.exerciseNo = exerciseNo;
		this.courseNo = courseNo;
		this.author = author;
		this.exerciseParameterNo = exerciseParameterNo;
		this.exerciseName = exerciseName;
		this.dateAdded = dateAdded;
		this.password = password;
	}
    
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
    
    public java.sql.Timestamp getDateAdded() {
        return dateAdded;
    }
    
    public void setDateAdded(java.sql.Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

	@Override
	public String getName() {
		return "Exercise";
	}
    
}
