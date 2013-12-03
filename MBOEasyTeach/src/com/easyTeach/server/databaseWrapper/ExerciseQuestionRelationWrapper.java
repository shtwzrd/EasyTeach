package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.easyTeach.common.entity.ExerciseQuestionRelation;
import com.easyTeach.server.databaseConnector.ConnectionManager;

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
    
    /**
     * Returns all the rows from the database's ExerciseQuestionRelation table in the 
     * form of a HashSet containing ExerciseQuestionRelation entities.   
     * 
     * @return a HashSet with all the rows in the ExerciseQuestionRelation table from the
     * easyTeach database. The rows are converted into ExerciseQuestionRelation entities.
     * @see ExerciseQuestionRelation
     */
    public static HashSet<ExerciseQuestionRelation> getExerciseQuestionRelationRows() {
        String sql = "{call selectExerciseQuestionRelationRows()}";

        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ){

            HashSet<ExerciseQuestionRelation> hashSet = new HashSet<ExerciseQuestionRelation>();
            
            while (rs.next()) {
                ExerciseQuestionRelation exerciseQuestionRelationEntity = new ExerciseQuestionRelation();
                exerciseQuestionRelationEntity.setExerciseNo(rs.getString("exerciseNo"));
                exerciseQuestionRelationEntity.setQuestionNo(rs.getString("questionNo"));
                
                hashSet.add(exerciseQuestionRelationEntity);
            }
            return hashSet;
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
    
    /**
     * Returns all the rows from the database's ExerciseQuestionRelation table 
     * with a specific exerciseNo in the form of a HashSet containing 
     * ExerciseQuestionRelation entities.   
     * 
     * @param exerciseNo is part of the primary key of the ExerciseQuestionRelation table
     * @return a HashSet with all the matching rows in the ExerciseQuestionRelation 
     * table from the easyTeach database. The rows are converted into 
     * ExerciseQuestionRelation entities.
     * @see ExerciseQuestionRelation
     */
    public static HashSet<ExerciseQuestionRelation> getExerciseQuestionRelationRowsWithExerciseNo(String exerciseNo) {
        String sql = "{call selectExerciseQuestionRelationRowsWithExerciseNo(?)}";
        ResultSet rs = null;
        
        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {
            stmt.setString(1, exerciseNo);
            rs = stmt.executeQuery();
            
            HashSet<ExerciseQuestionRelation> hashSet = new HashSet<ExerciseQuestionRelation>();
            
            while (rs.next()) {
                ExerciseQuestionRelation exerciseQuestionRelationEntity = new ExerciseQuestionRelation();
                exerciseQuestionRelationEntity.setExerciseNo(rs.getString("exerciseNo"));
                exerciseQuestionRelationEntity.setQuestionNo(rs.getString("questionNo"));
                
                hashSet.add(exerciseQuestionRelationEntity);
            }
                
            return hashSet;
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException e) {
                System.err.println(e);                
            }
        }
    }
    
    /**
     * Returns all the rows from the database's ExerciseQuestionRelation table 
     * with a specific questionNo in the form of a HashSet containing 
     * ExerciseQuestionRelation entities.   
     * 
     * @param questionNo is part of the primary key of the ExerciseQuestionRelation table
     * @return a HashSet with all the matching rows in the ExerciseQuestionRelation 
     * table from the easyTeach database. The rows are converted into 
     * ExerciseQuestionRelation entities.
     * @see ExerciseQuestionRelation
     */
    public static HashSet<ExerciseQuestionRelation> getExerciseQuestionRelationRowsWithQuestionNo(String questionNo) {
        String sql = "{call selectExerciseQuestionRelationRowsWithQuestionNo(?)}";
        ResultSet rs = null;
        
        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {
            stmt.setString(1, questionNo);
            rs = stmt.executeQuery();
            
            HashSet<ExerciseQuestionRelation> hashSet = new HashSet<ExerciseQuestionRelation>();
            
            while (rs.next()) {
                ExerciseQuestionRelation exerciseQuestionRelationEntity = new ExerciseQuestionRelation();
                exerciseQuestionRelationEntity.setExerciseNo(rs.getString("exerciseNo"));
                exerciseQuestionRelationEntity.setQuestionNo(rs.getString("questionNo"));
                
                hashSet.add(exerciseQuestionRelationEntity);
            }
                
            return hashSet;
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException e) {
                System.err.println(e);                
            }
        }
    }
    
}
