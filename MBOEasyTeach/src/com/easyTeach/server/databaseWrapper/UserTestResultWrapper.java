package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.easyTeach.server.databaseConnector.ConnectionManager;
import com.easyTeach.server.entity.UserTestResult;

/**
 * The UserTestResultWrapper is the class responsible for handling 
 * all the prepared CRUD SQL statements for manipulating with the
 * UserTestResult table residing in MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see UserTestResult
 * @date 30. November, 2013
 */

public class UserTestResultWrapper {
    
    private static Connection conn = 
            ConnectionManager.getInstance().getConnection();
    
    /**
     * Inserts a new UserTestResult row into the UserTestResult table 
     * within the easyTeach database. The prepared statement needs the 
     * userTestResult's userNo, exerciseNo and score.
     * 
     * @param userTestResultEntity is an instance of the class 
     * UserTestResult.
     * @return true if the userTestResultEntity is successfully inserted 
     * into the easyTeach database, otherwise false.
     * @see UserTestResult
     */
    public static boolean insertIntoUserTestResult(UserTestResult userTestResultEntity) {
        String sql = "{call insertIntoUserTestResult(?,?,?)}";

        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, userTestResultEntity.getUserNo());
            stmt.setString(2, userTestResultEntity.getExerciseNo());
            stmt.setInt(3, userTestResultEntity.getScore());
            
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
     * Deletes an existing UserTestResult row in the UserTestResult 
     * table within the easyTeach database. The prepared statement needs 
     * the userTestResult's userNo and exerciseNo.  
     * 
     * @param userNo is part of the primary key of the UserTestResult 
     * table.
     * @param exerciseNo is part of the primary key of the UserTestResult 
     * table.
     * @return true if the UserTestResult row is successfully deleted in 
     * the easyTeach database, otherwise false.
     * @see UserTestResult
     */
    public static boolean deleteUserTestResultRow(String userNo, String exerciseNo) {
        String sql = "{call deleteUserTestResultRow(?,?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, userNo);
            stmt.setString(2, exerciseNo);
            
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