package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.easyTeach.server.databaseConnector.ConnectionManager;
import com.easyTeach.server.entity.ClassCourseRelation;
import com.easyTeach.server.entity.Question;

/**
 * The ClassCourseRelationWrapper is the class responsible for handling 
 * all the prepared CRUD SQL statements for manipulating with the
 * ClassCourseRelation table residing in the MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see ClassCourseRelation
 * @date 30. November, 2013
 */

public class QuestionWrapper {

    private static Connection conn = 
            ConnectionManager.getInstance().getConnection();
    
    /**
     * Inserts a new Question row into the Question table within the easyTeach 
     * database. The prepared statement needs the Question's questionType,
     * question and points. 
     * 
     * @param questionEntity is an instance of the class Question
     * @return true if the questionEntity is successfully inserted into the
     * easyTeach database, otherwise false.
     * @see Question
     */
    public static boolean insertIntoQuestion(Question questionEntity) {
        String sql = "{call insertIntoQuestion(?,?,?)}";

        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, questionEntity.getQuestionType());
            stmt.setString(2, questionEntity.getQuestion());
            stmt.setInt(3, questionEntity.getPoints());
            
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
     * Updates an existing Question row in the Question table within the 
     * easyTeach database. The prepared statement needs the Question's 
     * questionNo, questionType, question and points. 
     * 
     * @param questionEntity is an instance of the class Question
     * @return true if the Question row is successfully updated in the
     * easyTeach database, otherwise false.
     * @see Question
     */
    public static boolean updateQuestionRow(Question questionEntity) {
        String sql = "{call updateQuestionRow(?,?,?,?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, questionEntity.getQuestionNo());
            stmt.setString(2, questionEntity.getQuestionType());
            stmt.setString(3, questionEntity.getQuestion());
            stmt.setInt(4, questionEntity.getPoints());
            
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
     * Deletes an existing Question row in the Question table within the 
     * easyTeach database. The prepared statement needs the Question's 
     * questionNo.
     * 
     * @param questionNo is the primary key of the Question table.
     * @return true if the Question row is successfully deleted in the
     * easyTeach database, otherwise false.
     * @see Question
     */
    public static boolean deleteQuestionRow(String questionNo) {
        String sql = "{call deleteQuestionRow(?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, questionNo);
            
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
