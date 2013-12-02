package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

import com.easyTeach.common.entity.ExerciseParameter;
import com.easyTeach.server.databaseConnector.ConnectionManager;

/**
 * The ExerciseParameterWrapper is the class responsible for handling 
 * all the prepared CRUD SQL statements for manipulating with the
 * ExerciseParameter table residing in the MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see ExerciseParameter
 * @date 30. November, 2013
 */

public class ExerciseParameterWrapper {

    private static Connection conn = 
            ConnectionManager.getInstance().getConnection();
    
    /**
     * Inserts a new ExerciseParameter row into the ExerciseParameter table 
     * within the easyTeach database. The prepared statement needs the 
     * exerciseParameter's isTest, isLocked, accessBegin, accessEnd and 
     * timeLimit.  
     * 
     * @param exerciseParameterEntity is an instance of the class 
     * ExerciseParameter.
     * @return true if the exerciseParameterEntity is successfully inserted 
     * into the easyTeach database, otherwise false.
     * @see ExerciseParameter
     */
    public static boolean insertIntoExerciseParameter(ExerciseParameter exerciseParameterEntity) {
        String sql = "{call insertIntoExerciseParameter(?,?,?,?,?)}";

        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setBoolean(1, exerciseParameterEntity.getIsTest());
            stmt.setBoolean(2, exerciseParameterEntity.getIsLocked());
            stmt.setDate(3, exerciseParameterEntity.getAccessBegin());
            stmt.setDate(4, exerciseParameterEntity.getAccessEnd());
            stmt.setInt(5, exerciseParameterEntity.getTimeLimit());
            
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
     * Updates an existing ExerciseParameter row in the ExerciseParameter 
     * table within the easyTeach database. The prepared statement needs 
     * the exerciseParameter's exerciseParameterNo, isTest, isLocked, 
     * accessBegin, accessEnd and timeLimit.
     * 
     * @param exerciseParameterEntity is an instance of the class ExerciseParameter
     * @return true if the ExerciseParameter row is successfully updated in the
     * easyTeach database, otherwise false.
     * @see ExerciseParameter
     */
    public static boolean updateExerciseParameterRow(ExerciseParameter exerciseParameterEntity) {
        String sql = "{call updateExerciseParameterRow(?,?,?,?,?,?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, exerciseParameterEntity.getExerciseParameterNo());
            stmt.setBoolean(2, exerciseParameterEntity.getIsTest());
            stmt.setBoolean(3, exerciseParameterEntity.getIsLocked());
            stmt.setDate(4, exerciseParameterEntity.getAccessBegin());
            stmt.setDate(5, exerciseParameterEntity.getAccessEnd());
            stmt.setInt(6, exerciseParameterEntity.getTimeLimit());
            
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
     * Deletes an existing ExerciseParameter row in the ExerciseParameter 
     * table within the easyTeach database. The prepared statement needs 
     * the exerciseParameter's exerciseParameterNo.  
     * 
     * @param exerciseParameterNo is the primary key of the ExerciseParameter 
     * table.
     * @return true if the ExerciseParameter row is successfully deleted in the
     * easyTeach database, otherwise false.
     * @see ExerciseParameter
     */
    public static boolean deleteExerciseParameterRow(String exerciseParameterNo) {
        String sql = "{call deleteExerciseParameterRow(?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, exerciseParameterNo);
            
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
     * Returns all the rows from the database's ExerciseParameter table in 
     * the form of a TreeSet containing ExerciseParameter entities.   
     * 
     * @return a TreeSet with all the rows in the ExerciseParameter table from 
     * the easyTeach database. The rows are converted into ExerciseParameter 
     * entities.
     * @see ExerciseParameter
     */
    public static TreeSet<ExerciseParameter> getExerciseParameterRows() {
        String sql = "{call selectExerciseParameterRows()}";

        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ){

            TreeSet<ExerciseParameter> treeSet = new TreeSet<ExerciseParameter>();
            
            if (rs.next()) {
                ExerciseParameter exerciseParameterEntity = new ExerciseParameter();
                exerciseParameterEntity.setExerciseParameterNo(rs.getString("exerciseParameterNo"));
                exerciseParameterEntity.setIsTest(rs.getBoolean("isTest"));
                exerciseParameterEntity.setIsLocked(rs.getBoolean("isLocked"));
                exerciseParameterEntity.setAccessBegin(rs.getDate("accessBegin"));
                exerciseParameterEntity.setAccessEnd(rs.getDate("accessEnd"));
                exerciseParameterEntity.setTimeLimit(rs.getInt("timeLimit"));
                
                treeSet.add(exerciseParameterEntity);
            }
            return treeSet;
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
    
}
