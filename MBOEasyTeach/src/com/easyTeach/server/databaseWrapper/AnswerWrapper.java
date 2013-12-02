package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

import com.easyTeach.common.entity.Answer;
import com.easyTeach.server.databaseConnector.ConnectionManager;

/**
 * The AnswerWrapper is the class responsible for handling all the
 * prepared CRUD SQL statements for manipulating with the Answer
 * table residing in the MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see Answer
 * @date 30. November, 2013
 */

public class AnswerWrapper {

    private static Connection conn = 
            ConnectionManager.getInstance().getConnection();
    
    /**
     * Inserts a new Answer row into the Answer table within the easyTeach 
     * database. The prepared statement needs the answer's questionNo, 
     * answerNo, answer and isCorrect.  
     * 
     * @param answerEntity is an instance of the class Answer
     * @return true if the answerEntity is successfully inserted into the
     * easyTeach database, otherwise false.
     * @see Answer
     */
    public static boolean insertIntoAnswer(Answer answerEntity) {
        String sql = "{call insertIntoAnswer(?,?,?,?)}";

        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, answerEntity.getQuestionNo());
            stmt.setString(2, answerEntity.getAnswerNo());
            stmt.setString(3, answerEntity.getAnswer());
            stmt.setBoolean(4, answerEntity.getIsCorrect());
            
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
     * Updates an existing Answer row in the Answer table within the easyTeach
     * database. The prepared statement needs the answer's questionNo, 
     * answerNo, answer and isCorrect.  
     * 
     * @param answerEntity is an instance of the class Answer
     * @return true if the Answer row is successfully updated in the
     * easyTeach database, otherwise false.
     * @see Answer
     */
    public static boolean updateAnswerRow(Answer answerEntity) {
        String sql = "{call updateAnswerRow(?,?,?,?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, answerEntity.getQuestionNo());
            stmt.setString(2, answerEntity.getAnswerNo());
            stmt.setString(3, answerEntity.getAnswer());
            stmt.setBoolean(4, answerEntity.getIsCorrect());
            
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
     * Deletes an existing Answer row in the Answer table within the easyTeach
     * database. The prepared statement needs the answer's questionNo and 
     * answerNo.  
     * 
     * @param questionNo is part of the primary key of the Answer table.
     * @param answerNo is part of the primary key of the Answer table'.
     * @return true if the Answer row is successfully deleted in the
     * easyTeach database, otherwise false.
     * @see Answer
     */
    public static boolean deleteAnswerRow(String questionNo, String answerNo) {
        String sql = "{call deleteAnswerRow(?,?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, questionNo);
            stmt.setString(2, answerNo);
            
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
     * Returns all the rows from the database's Answer table in the form of a 
     * TreeSet containing Answer entities.   
     * 
     * @return a TreeSet with all the rows in the Answer table from the
     * easyTeach database. The rows are converted into Answer entities.
     * @see Answer
     */
    public static TreeSet<Answer> getAnswerRows() {
        String sql = "{call selectAnswerRows()}";

        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ){

            TreeSet<Answer> treeSet = new TreeSet<Answer>();
            
            if (rs.next()) {
                Answer answerEntity = new Answer();
                answerEntity.setQuestionNo(rs.getString("questionNo"));
                answerEntity.setAnswerNo(rs.getString("answerNo"));
                answerEntity.setAnswer(rs.getString("answer"));
                answerEntity.setIsCorrect(rs.getBoolean("isCorrect"));
                
                treeSet.add(answerEntity);
            }
            return treeSet;
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
    
}
