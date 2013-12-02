package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.easyTeach.server.databaseConnector.ConnectionManager;
import com.easyTeach.server.entity.ExerciseQuestionRelation;

/**
 * The ExerciseQuestionRelationWrapper is the class responsible for handling 
 * all the prepared CRUD SQL statements for manipulating with the
 * ExerciseQuestionRelation table residing in the MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see ExerciseQuestionRelation
 * @date 30. November, 2013
 */

public class ExerciseQuestionRelationWrapper {

    private static Connection conn = 
            ConnectionManager.getInstance().getConnection();
    
    /**
     * Inserts a new ExerciseQuestionRelation row into the 
     * ExerciseQuestionRelation table within the easyTeach database. 
     * The prepared statement needs the ExerciseQuestionRelation's 
     * exerciseNo and questionNo. 
     * 
     * @param exerciseQuestionRelationEntity is an instance of the class 
     * ExerciseQuestionRelation
     * @return true if the exerciseQuestionRelationEntity is successfully 
     * inserted into the easyTeach database, otherwise false.
     * @see ExerciseQuestionRelation
     */
    public static boolean insertIntoExerciseQuestionRelation(ExerciseQuestionRelation exerciseQuestionRelationEntity) {
        String sql = "{call insertIntoExerciseQuestionRelation(?,?)}";

        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, exerciseQuestionRelationEntity.getExerciseNo());
            stmt.setString(2, exerciseQuestionRelationEntity.getQuestionNo());
            
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
     * Deletes an existing ExerciseQuestionRelation row in the 
     * ExerciseQuestionRelation table within the easyTeach database. The 
     * prepared statement needs the ExerciseQuestionRelation's userNo 
     * and questionNo.
     * 
     * @param exerciseNo is part of the primary key of the 
     * ExerciseQuestionRelation table.
     * @param questionNo is part of the primary key of the 
     * ExerciseQuestionRelation table.
     * @return true if the ExerciseQuestionRelation row is successfully 
     * deleted in the easyTeach database, otherwise false.
     * @see ExerciseQuestionRelation
     */
    public static boolean deleteExerciseQuestionRelationRow(String exerciseNo, String questionNo) {
        String sql = "{call deleteExerciseQuestionRelationRow(?,?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, exerciseNo);
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
