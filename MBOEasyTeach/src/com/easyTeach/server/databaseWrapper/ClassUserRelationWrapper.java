package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.easyTeach.server.databaseConnector.ConnectionManager;
import com.easyTeach.server.entity.ClassUserRelation;

/**
 * The ClassUserRelationWrapper is the class responsible for handling 
 * all the prepared CRUD SQL statements for manipulating with the
 * ClassUserRelation table residing in the MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see ClassUserRelation
 * @date 30. November, 2013
 */

public class ClassUserRelationWrapper {

    private static Connection conn = 
            ConnectionManager.getInstance().getConnection();
    
    /**
     * Inserts a new ClassUserRelation row into the ClassUserRelation table 
     * within the easyTeach database. The prepared statement needs the 
     * classUserRelation's classNo and userNo.
     * 
     * @param classUserRelationEntity is an instance of the class 
     * ClassUserRelation.
     * @return true if the classUserRelationEntity is successfully inserted 
     * into the easyTeach database, otherwise false.
     * @see ClassUserRelation
     */
    public static boolean insertIntoClassUserRelation(ClassUserRelation classUserRelationEntity) {
        String sql = "{call insertIntoClassUserRelation(?,?)}";

        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, classUserRelationEntity.getClassNo());
            stmt.setString(2, classUserRelationEntity.getUserNo());
            
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
     * Deletes an existing ClassUserRelation row in the ClassUserRelation 
     * table within the easyTeach database. The prepared statement needs 
     * the classUserRelation's classNo and userNo.  
     * 
     * @param classNo is part of the primary key of the ClassUserRelation 
     * table.
     * @param userNo is part of the primary key of the ClassUserRelation 
     * table.
     * @return true if the ClassUserRelation row is successfully deleted in 
     * the easyTeach database, otherwise false.
     * @see ClassUserRelation
     */
    public static boolean deleteClassUserRelationRow(String classNo, String userNo) {
        String sql = "{call deleteClassUserRelationRow(?,?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, classNo);
            stmt.setString(2, userNo);
            
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
