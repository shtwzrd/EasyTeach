package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.easyTeach.server.databaseConnector.ConnectionManager;
import com.easyTeach.server.entity.Course;

/**
 * The CourseWrapper is the class responsible for handling all the prepared 
 * CRUD SQL statements for manipulating with the Course table residing in 
 * the MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see Course
 * @date 30. November, 2013
 */

public class CourseWrapper {

    private static Connection conn = 
            ConnectionManager.getInstance().getConnection();
    
    /**
     * Inserts a new Course row into the course table within the easyTeach 
     * database. The prepared statement needs the course's courseName.
     * 
     * @param courseEntity is an instance of the class Course
     * @return true if the courseEntity is successfully inserted into the
     * easyTeach database, otherwise false.
     * @see Course
     */
    public static boolean insertIntoCourse(Course courseEntity) {
        String sql = "{call insertIntoCourse(?)}";

        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, courseEntity.getCourseName());
            
            int affected = stmt.executeUpdate();
            if (affected == 1) {
                return true;
            } else {
                return false;
            }
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    
    /**
     * Updates an existing Course row in the Course table within the easyTeach
     * database. The prepared statement needs the Course's courseNo, 
     * and courseName.
     * 
     * @param courseEntity is an instance of the class Course
     * @return true if the Course row is successfully updated in the
     * easyTeach database, otherwise false.
     * @see Course
     */
    public static boolean updateCourseRow(Course courseEntity) {
        String sql = "{call updateCourseRow(?,?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, courseEntity.getCourseNo());
            stmt.setString(2, courseEntity.getCourseName());
            
            int affected = stmt.executeUpdate();
            if (affected == 1) {
                return true;
            } else {
                return false;
            }
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        } 
    }
    
    /**
     * Deletes an existing Course row in the Course table within the easyTeach
     * database. The prepared statement needs the Course's courseNo.
     * 
     * @param courseNo is the primary key of the Course table.
     * @return true if the Course row is successfully deleted in the
     * easyTeach database, otherwise false.
     * @see Course
     */
    public static boolean deleteCourseRow(String courseNo) {
        String sql = "{call deleteCourseRow(?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, courseNo);
            
            int affected = stmt.executeUpdate();
            if (affected == 1) {
                return true;
            } else {
                return false;
            }
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    
}
