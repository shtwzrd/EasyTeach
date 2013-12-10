package com.easyTeach.common.entity;

/** 
 * <p>
 * The Course class represents a row from the Course table in the easyTeach 
 * database. It contains getters and setters for the two attributes derived 
 * from the Course table, namely; courseNo and courseName.
 * </p>
 * 
 * <p>
 * In the database the Course table's primary key is courseNo.
 * </p>
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 26. November, 2013
 * @obvious Comments for methods are omitted as they are self explanatory 
 *          (getters/setters).
 */

public class Course implements Resource {

	private static final long serialVersionUID = 4525515384143342071L;

	private String courseNo;    
    private String courseName;
    
    public Course() {
    	
    }

	public Course(String courseNo, String courseName) {
		this.courseNo = courseNo;
		this.courseName = courseName;
	}

    public String getCourseNo() {
        return courseNo;
    }
    
    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

	@Override
	public String getName() {
		return "Course";
	}
    
}
