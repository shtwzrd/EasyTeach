package com.easyTeach.common.entity;

/** 
 * <p>
 * The Class class represents a row from the Class table in the easyTeach 
 * database. It contains getters and setters for the three attributes derived 
 * from the Class table, namely; classNo, year and className.
 * </p>
 * 
 * <p>
 * In the database the primary key of the Class table is the classNo.
 * </p>
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 26. November, 2013
 * @obvious Comments for methods are omitted as they are self explanatory 
 *          (getters/setters).
 */

public class Class {

    private String classNo;     // INTEGER in DB
    private int year;
    private String className;
    
    public String getClassNo() {
        return classNo;
    }
    
    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }
    
    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }

}
