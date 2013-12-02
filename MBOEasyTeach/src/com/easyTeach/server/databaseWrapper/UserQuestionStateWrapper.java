package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.easyTeach.server.databaseConnector.ConnectionManager;
import com.easyTeach.server.entity.UserQuestionState;

/**
 * The UserUserQuestionStateStateWrapper is the class responsible for handling 
 * all the prepared CRUD SQL statements for manipulating with the
 * UserUserQuestionStateState table residing in the MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see UserUserQuestionStateState
 * @date 1. December, 2013
 */

public class UserQuestionStateWrapper {

    private static Connection conn = 
            ConnectionManager.getInstance().getConnection();
    
    /**
     * Inserts a new UserQuestionState row into the UserQuestionState table 
     * within the easyTeach database. The prepared statement needs the 
     * UserQuestionState's userNo, questionNo and hasCompleted. 
     * 
     * @param userQuestionStateEntity is an instance of the class 
     * UserQuestionState
     * @return true if the userQuestionStateEntity is successfully 
     * inserted into the easyTeach database, otherwise false.
     * @see UserQuestionState
     */
    public static boolean insertIntoUserQuestionState(UserQuestionState userQuestionStateEntity) {
        String sql = "{call insertIntoUserQuestionState(?,?,?)}";

        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, userQuestionStateEntity.getUserNo());
            stmt.setString(2, userQuestionStateEntity.getQuestionNo());
            stmt.setBoolean(3, userQuestionStateEntity.getHasCompleted());
            
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
     * Updates an existing UserQuestionState row in the UserQuestionState table 
     * within the easyTeach database. The prepared statement needs the 
     * UserQuestionState's  userNo, questionNo and hasCompleted. 
     * 
     * @param userQuestionStateEntity is an instance of the class UserQuestionState
     * @return true if the UserQuestionState row is successfully updated in the
     * easyTeach database, otherwise false.
     * @see UserQuestionState
     */
    public static boolean updateUserQuestionStateHasCompleted(UserQuestionState userQuestionStateEntity) {
        String sql = "{call updateUserQuestionStateHasCompleted(?,?,?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, userQuestionStateEntity.getUserNo());
            stmt.setString(2, userQuestionStateEntity.getQuestionNo());
            stmt.setBoolean(3, userQuestionStateEntity.getHasCompleted());
            
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
     * Deletes an existing UserQuestionState row in the UserQuestionState table 
     * within the easyTeach database. The prepared statement needs the 
     * UserQuestionState's userNo and questionNo.
     * 
     * @param userNo is part of the primary key of the UserQuestionState table.
     * @param questionNo is part of the primary key of the UserQuestionState table.
     * @return true if the UserQuestionState row is successfully deleted in the
     * easyTeach database, otherwise false.
     * @see UserQuestionState
     */
    public static boolean deleteUserQuestionStateRow(String userNo, String questionNo) {
        String sql = "{call deleteUserQuestionStateRow(?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, userNo);
            stmt.setString(2, questionNo);
            
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
