package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.easyTeach.common.entity.Course;
import com.easyTeach.server.databaseConnector.ConnectionManager;

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
    
    /**
     * Returns all the rows from the database's Course table in the form of a 
     * HashSet containing Course entities.   
     * 
     * @return a HashSet with all the rows in the Course table from the
     * easyTeach database. The rows are converted into Course entities.
     * @see Course
     */
    public static HashSet<Course> getCourseRows() {
        String sql = "{call selectCourseRows()}";

        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ){

            HashSet<Course> hashSet = new HashSet<Course>();
            
            while (rs.next()) {
                Course courseEntity = new Course();
                courseEntity.setCourseNo(rs.getString("courseNo"));
                courseEntity.setCourseName(rs.getString("courseName"));
                
                hashSet.add(courseEntity);
            }
            return hashSet;
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
    
    /**
     * Returns a row from the database's Course table with a specific courseNo.
     * 
     * @param courseNo is the primary key of the Course table.
     * @return An instance of Course
     * @see Course
     */
    public static Course getCourseRowWithCourseNo(String courseNo) throws SQLException {
        String sql = "{call selectCourseRowWithCourseNo(?)}";
        ResultSet rs = null;
        
        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {
            stmt.setString(1, courseNo);
            rs = stmt.executeQuery();
            rs.next();
            
            Course courseEntity = new Course();
            courseEntity.setCourseNo(rs.getString("courseNo"));
            courseEntity.setCourseName(rs.getString("courseName"));
            
            return courseEntity;
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            rs.close();
        }
    }
    
}
