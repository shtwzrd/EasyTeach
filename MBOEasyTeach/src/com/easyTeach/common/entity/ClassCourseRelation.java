package com.easyTeach.common.entity;

/** 
 * <p>
 * The ClassCourseRelation class represents a row from the 
 * ClassCourseRelation table in the easyTeach database. The class 
 * contains getters and setters for the two attributes derived from the 
 * ClassCourseRelation table, namely; classNo and courseNo. The table maps
 * the relation between Classes and Courses.
 * </p>
 * 
 * <p>
 * In the database the ClassCourseRelation table's primary key is the 
 * composite of classNo and courseNo.
 * </p>
 * 
 * <p>
 * In the database the ClassCourseRelation table references the 
 * primary key of the Class and Course tables.
 * </p>
 *  
 * @author Morten Faarkrog
 * @version 1.0
 * @date 26. November, 2013
 * @obvious Comments for methods are omitted as they are self explanatory 
 *          (getters/setters).
 */

public class ClassCourseRelation implements Resource {
    
	private static final long serialVersionUID = 6937447384747520214L;
	private String classNo;   // INTEGER in DB
    private String courseNo;  // INTEGER in DB
    
    
    public ClassCourseRelation() {
    	
    }
    
    public ClassCourseRelation(String classNo, String courseNo) {
    	this.classNo = classNo;
    	this.courseNo = courseNo;
    }
    
    public String getClassNo() {
        return classNo;
    }
    
    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }
    
    public String getCourseNo() {
        return courseNo;
    }
    
    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

	@Override
	public String getName() {
		return "ClassCourseRelation";
	}

}
