package com.easyTeach.common.entity;

/** 
 * <p>
 * The ClassUserRelation class represents a row from the 
 * ClassUserRelation table in the easyTeach database. The class 
 * contains getters and setters for the two attributes derived from the 
 * ClassUserRelation table, namely; classNo and userNo. The table maps
 * the relation between Classes and Users.
 * </p>
 * 
 * <p>
 * In the database the ClassUserRelation table's primary key is the 
 * composite of classNo and userNo.
 * </p>
 * 
 * <p>
 * In the database the ClassUserRelation table references the 
 * primary key of the Class and User tables.
 * </p>
 *  
 * @author Morten Faarkrog
 * @version 1.0
 * @date 26. November, 2013
 * @obvious Comments for methods are omitted as they are self explanatory 
 *          (getters/setters).
 */

public class ClassUserRelation {
    
    private String classNo;   // INTEGER in DB
    private String userNo;    // INTEGER in DB
    
    public String getClassNo() {
        return classNo;
    }
    
    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }
    
    public String getUserNo() {
        return userNo;
    }
    
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

}