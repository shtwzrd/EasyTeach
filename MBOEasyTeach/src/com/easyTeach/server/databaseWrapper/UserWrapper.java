package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

import com.easyTeach.common.entity.Course;
import com.easyTeach.common.entity.User;
import com.easyTeach.server.databaseConnector.ConnectionManager;

/**
 * The UserWrapper is the class responsible for handling all the prepared 
 * CRUD SQL statements for manipulating with the User table residing in 
 * the MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see User
 * @date 30. November, 2013
 */

public class UserWrapper {

    private static Connection conn = 
            ConnectionManager.getInstance().getConnection();
    
    /**
     * Inserts a new User row into the User table within the easyTeach 
     * database. The prepared statement needs the user's email, userType,
     * firstName, lastName, password and dateAdded.
     * 
     * @param userEntity is an instance of the class User
     * @return true if the userEntity is successfully inserted into the
     * easyTeach database, otherwise false.
     * @see User
     */
    public static boolean insertIntoUser(User userEntity) {
        String sql = "{call insertIntoUser(?,?,?,?,?,?)}";

        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, userEntity.getEmail());
            stmt.setString(2, userEntity.getUserType());
            stmt.setString(3, userEntity.getFirstName());
            stmt.setString(4, userEntity.getLastName());
            stmt.setString(5, userEntity.getPassword());
            stmt.setDate(6, userEntity.getDateAdded());
            
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
     * Updates an existing User row in the User table within the easyTeach
     * database. The prepared statement needs the user's uesrNo, email, 
     * userType, firstName, lastName and password.
     * 
     * @param userEntity is an instance of the class User
     * @return true if the User row is successfully updated in the
     * easyTeach database, otherwise false.
     * @see User
     */
    public static boolean updateUserRow(User userEntity) {
        String sql = "{call updateUserRow(?,?,?,?,?,?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, userEntity.getUserNo());
            stmt.setString(2, userEntity.getUserType());
            stmt.setString(3, userEntity.getEmail());
            stmt.setString(4, userEntity.getFirstName());
            stmt.setString(5, userEntity.getLastName());
            stmt.setString(6, userEntity.getPassword());
            
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
     * Updates an existing User's password in the User table within the 
     * easyTeach database. The prepared statement needs the user's userNo
     * and the new password.
     * 
     * @param userNo is the primary key of the User table;
     * @param password is the new password 
     * @return true if the User row is successfully updated in the
     * easyTeach database, otherwise false.
     * @see User
     */
    public static boolean updateUserPassword(String userNo, String password) {
        String sql = "{call updatePassword(?,?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, userNo);
            stmt.setString(2, password);
            
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
     * Deletes an existing User row in the User table within the easyTeach
     * database. The prepared statement needs the User's userNo.
     * 
     * @param userNo is the primary key of the User table.
     * @return true if the User row is successfully deleted in the
     * easyTeach database, otherwise false.
     * @see User
     */
    public static boolean deleteuserRow(String userNo) {
        String sql = "{call deleteUserRow(?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, userNo);
            
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
     * Returns all the rows from the database's User table in the form of a 
     * TreeSet containing User entities.   
     * 
     * @return a TreeSet with all the rows in the User table from the
     * easyTeach database. The rows are converted into User entities.
     * @see User
     */
    public static TreeSet<User> getUserRows() {
        String sql = "{call selectUserRows()}";

        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ){

            TreeSet<User> treeSet = new TreeSet<User>();
            
            if (rs.next()) {
                User userEntity = new User();
                userEntity.setUserNo(rs.getString("userNo"));
                userEntity.setEmail(rs.getString("email"));
                userEntity.setUserType(rs.getString("userType"));
                userEntity.setFirstName(rs.getString("firstName"));
                userEntity.setLastName(rs.getString("lastName"));
                userEntity.setPassword(rs.getString("password"));
                userEntity.setDateAdded(rs.getDate("dateAdded"));
                
                treeSet.add(userEntity);
            }
            return treeSet;
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
    
}
