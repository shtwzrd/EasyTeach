package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

import com.easyTeach.common.entity.Exercise;
import com.easyTeach.server.databaseConnector.ConnectionManager;

/**
 * The ExerciseWrapper is the class responsible for handling 
 * all the prepared CRUD SQL statements for manipulating with the
 * Exercise table residing in the MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see Exercise
 * @date 30. November, 2013
 */

public class ExerciseWrapper {

    private static Connection conn = 
            ConnectionManager.getInstance().getConnection();
    
    /**
     * Inserts a new Exercise row into the Exercise table within the easyTeach 
     * database. The prepared statement needs the Exercise's courseNo, author,
     * exerciseParameterNo, exerciseName, dateAdded and password.
     * 
     * @param exerciseEntity is an instance of the class Exercise
     * @return true if the exerciseEntity is successfully inserted into the
     * easyTeach database, otherwise false.
     * @see Exercise
     */
    public static boolean insertIntoExercise(Exercise exerciseEntity) {
        String sql = "{call insertIntoExercise(?,?,?,?,?,?)}";

        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, exerciseEntity.getCourseNo());
            stmt.setString(2, exerciseEntity.getAuthor());
            stmt.setString(3, exerciseEntity.getExerciseParameterNo());
            stmt.setString(4, exerciseEntity.getExerciseName());
            stmt.setDate(5, exerciseEntity.getDateAdded());
            stmt.setString(6, exerciseEntity.getPassword());
            
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
     * Updates an existing Exercise row in the Exercise table within the 
     * easyTeach database. The prepared statement needs the Exercise's 
     * exerciseNo, courseNo, author, exerciseParameterNo, exerciseName 
     * and password.
     * 
     * @param exerciseEntity is an instance of the class Exercise
     * @return true if the Exercise row is successfully updated in the
     * easyTeach database, otherwise false.
     * @see Exercise
     */
    public static boolean updateExerciseRow(Exercise exerciseEntity) {
        String sql = "{call updateExerciseRow(?,?,?,?,?,?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, exerciseEntity.getExerciseNo());
            stmt.setString(2, exerciseEntity.getCourseNo());
            stmt.setString(3, exerciseEntity.getExerciseParameterNo());
            stmt.setString(4, exerciseEntity.getExerciseName());
            stmt.setString(5, exerciseEntity.getPassword());
            
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
     * Deletes an existing Exercise row in the Exercise table within the 
     * easyTeach database. The prepared statement needs the Exercise's 
     * exerciseNo.
     * 
     * @param exerciseNo is the primary key of the Exercise table.
     * @return true if the Exercise row is successfully deleted in the
     * easyTeach database, otherwise false.
     * @see Exercise
     */
    public static boolean deleteExerciseRow(String exerciseNo) {
        String sql = "{call deleteExerciseRow(?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, exerciseNo);
            
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
     * Returns all the rows from the database's Exercise table in the form of a
     * TreeSet containing Exercise entities.   
     * 
     * @return a TreeSet with all the rows in the Exercise table from the
     * easyTeach database. The rows are converted into Exercise entities.
     * @see Exercise
     */
    public static TreeSet<Exercise> getExerciseRows() {
        String sql = "{call selectExerciseRows()}";

        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ){

            TreeSet<Exercise> treeSet = new TreeSet<Exercise>();
            
            if (rs.next()) {
                Exercise exerciseEntity = new Exercise();
                exerciseEntity.setExerciseNo(rs.getString("exerciseNo"));
                exerciseEntity.setCourseNo(rs.getString("courseNo"));
                exerciseEntity.setAuthor(rs.getString("author"));
                exerciseEntity.setExerciseParameterNo(rs.getString("exerciseParameterNo"));
                exerciseEntity.setExerciseName(rs.getString("exerciseName"));
                exerciseEntity.setDateAdded(rs.getDate("dateAdded"));
                exerciseEntity.setPassword(rs.getString("password"));
                
                treeSet.add(exerciseEntity);
            }
            return treeSet;
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
    
}
