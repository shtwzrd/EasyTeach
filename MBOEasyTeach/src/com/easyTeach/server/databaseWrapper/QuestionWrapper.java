package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.easyTeach.common.entity.ClassCourseRelation;
import com.easyTeach.common.entity.Question;
import com.easyTeach.common.entity.Tag;
import com.easyTeach.server.databaseConnector.ConnectionManager;

/**
 * The ClassCourseRelationWrapper is the class responsible for handling 
 * all the prepared CRUD SQL statements for manipulating with the
 * ClassCourseRelation table residing in the MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.1
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
     * @param questionNo is the primary key of the question table.
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
    
    /**
     * Returns all the rows from the database's Question table in the form of a 
     * HashSet containing Question entities.   
     * 
     * @return a HashSet with all the rows in the Question table from the
     * easyTeach database. The rows are converted into Question entities.
     * @see Question
     */
    public static HashSet<Question> getQuestionRows() {
        String sql = "{call selectQuestionRows()}";

        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ){

            // 
            
            HashSet<Question> hashSet = new HashSet<Question>();
            
            while (rs.next()) {
                Question questionEntity = new Question();
                questionEntity.setQuestionNo(rs.getString("questionNo"));
                questionEntity.setQuestionType(rs.getString("questionType"));
                questionEntity.setQuestion(rs.getString("question"));
                questionEntity.setPoints(rs.getInt("points"));
                
                hashSet.add(questionEntity);
            }
            return hashSet;
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
    
    /**
     * Returns a row from the database's Question table with a specific 
     * questionNo.
     * 
     * @param questionNo is the primary key of the Question table.
     * @return An instance of Question
     * @see Question
     */
    public static Question getQuestionRowWithQuestionNo(String questionNo) throws SQLException {
        String sql = "{call selectQuestionRowWithQuestionNo(?)}";
        ResultSet rs = null;
        
        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {
            stmt.setString(1, questionNo);
            rs = stmt.executeQuery();
            rs.next();
            
            Question questionEntity = new Question();
            questionEntity.setQuestionNo(rs.getString("questionNo"));
            questionEntity.setQuestionType(rs.getString("questionType"));
            questionEntity.setQuestion(rs.getString("question"));
            questionEntity.setPoints(rs.getInt("points"));
            
            return questionEntity;
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            rs.close();
        }
    }
    
}
