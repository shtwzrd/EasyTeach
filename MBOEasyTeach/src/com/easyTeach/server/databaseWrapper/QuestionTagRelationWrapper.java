package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.easyTeach.server.databaseConnector.ConnectionManager;
import com.easyTeach.server.entity.QuestionTagRelation;

/**
 * The QuestionTagRelationWrapper is the class responsible for handling 
 * all the prepared CRUD SQL statements for manipulating with the
 * QuestionTagRelation table residing in the MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see QuestionTagRelation
 * @date 1. December, 2013
 */

public class QuestionTagRelationWrapper {

    private static Connection conn = 
            ConnectionManager.getInstance().getConnection();
    
    /**
     * Inserts a new QuestionTagRelation row into the QuestionTagRelation
     * table within the easyTeach database. The prepared statement needs the 
     * QuestionTagRelation's questionNo and tagNo. 
     * 
     * @param questionTagRelationEntity is an instance of the class 
     * QuestionTagRelation
     * @return true if the questionTagRelationEntity is successfully 
     * inserted into the easyTeach database, otherwise false.
     * @see QuestionTagRelation
     */
    public static boolean insertIntoQuestionTagRelation(QuestionTagRelation questionTagRelationEntity) {
        String sql = "{call insertIntoQuestionTagRelation(?,?)}";

        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, questionTagRelationEntity.getQuestionNo());
            stmt.setString(2, questionTagRelationEntity.getTagNo());
            
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
     * Deletes an existing QuestionTagRelation row in the QuestionTagRelation 
     * table within the easyTeach database. The prepared statement needs the 
     * QuestionTagRelation's userNo and questionNo.
     * 
     * @param questionNo is part of the primary key of the QuestionTagRelation 
     * table.
     * @param tagNo is part of the primary key of the QuestionTagRelation 
     * table.
     * @return true if the QuestionTagRelation row is successfully deleted in 
     * the easyTeach database, otherwise false.
     * @see QuestionTagRelation
     */
    public static boolean deleteQuestionTagRelationRow(String questionNo, String tagNo) {
        String sql = "{call deleteQuestionTagRelationRow(?, ?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, questionNo);
            stmt.setString(2, tagNo);
            
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
