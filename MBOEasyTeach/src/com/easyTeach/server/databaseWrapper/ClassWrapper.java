package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.easyTeach.server.databaseConnector.ConnectionManager;
import com.easyTeach.server.entity.Class;

/**
 * The ClassWrapper is the class responsible for handling all the
 * prepared CRUD SQL statements for manipulating with the Class
 * table residing in MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see Class
 * @date 30. November, 2013
 */

public class ClassWrapper {

    private static Connection conn = 
            ConnectionManager.getInstance().getConnection();
    
    /**
     * Inserts a new Class row into the Class table within the easyTeach 
     * database. The prepared statement needs the class' year and name.  
     * 
     * @param classEntity is an instance of the class Class
     * @return true if the classEntity is successfully inserted into the
     * easyTeach database, otherwise false.
     * @see Class
     */
    public static boolean insertIntoClass(Class classEntity) {
        String sql = "{call insertIntoClass(?,?)}";

        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setInt(1, classEntity.getYear());
            stmt.setString(2, classEntity.getClassName());
            
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
     * Updates an existing Class row in the Class table within the easyTeach 
     * database. The prepared statement needs the class' year and name.  
     * 
     * @param classEntity is an instance of the class Class
     * @return true if the Class row is successfully updated in the
     * easyTeach database, otherwise false.
     * @see Class
     */
    public static boolean updateClassRow(Class classEntity) {
        String sql = "{call updateClassRow(?,?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setInt(1, classEntity.getYear());
            stmt.setString(2, classEntity.getClassName());
            
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
     * Deletes an existing Class row in the Class table within the easyTeach 
     * database. The prepared statement needs the class' classNo.  
     * 
     * @param classNo is the primary key of the Class table.
     * @return true if the Class row is successfully deleted in the
     * easyTeach database, otherwise false.
     * @see Class
     */
    public static boolean deleteClassRow(String classNo) {
        String sql = "{call deleteClassRow(?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, classNo);
            
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
     * Returns all the rows from the database's Class table in the form of a 
     * HashSet containing Class entities.   
     * 
     * @return a HashSet with all the rows in the Class table from the
     * easyTeach database. The rows are converted into Class entities.
     * @see Class
     */
    public static HashSet<Class> getClassRows() {
        String sql = "{call getClassRows()}";

        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ){

            HashSet<Class> hashSet = new HashSet<Class>();
            
            if (rs.next()) {
                Class classEntity = new Class();
                classEntity.setClassNo(rs.getString("classNo"));
                classEntity.setYear(rs.getInt("year"));
                classEntity.setClassName(rs.getString("className"));
                
                hashSet.add(classEntity);
            }
            return hashSet;
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
    
}
