package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
import java.sql.SQLTransientException;
import java.util.HashSet;

import com.easyTeach.common.entity.User;
import com.easyTeach.server.databaseConnector.ConnectionManager;

/**
 * The UserWrapper is the class responsible for handling all the prepared CRUD
 * SQL statements for manipulating with the User table residing in the MBO
 * EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.1
 * @see User
 * @date 06. December, 2013
 */

public class UserWrapper {

    private static Connection conn = ConnectionManager.getInstance()
            .getConnection();

    /**
     * Inserts a new User row into the User table within the easyTeach database.
     * The prepared statement needs the user's userNo, email, userType,
     * firstName, lastName, password and dateAdded.
     * 
     * @param userEntity
     *            is an instance of the class User
     * @return true if the userEntity is successfully inserted into the
     *         easyTeach database, otherwise false.
     * @see User
     */
    public static boolean insertIntoUser(User userEntity) {
        String sql = "{call insertIntoUser(?,?,?,?,?,?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, userEntity.getUserNo());
            stmt.setString(2, userEntity.getEmail());
            stmt.setString(3, userEntity.getUserType());
            stmt.setString(4, userEntity.getFirstName());
            stmt.setString(5, userEntity.getLastName());
            stmt.setString(6, userEntity.getPassword());

            stmt.execute();
            return true;
            
        } catch (SQLTransientConnectionException SQLtce) {
            return insertIntoUser(userEntity);
        } catch (SQLTransientException SQLte) {
            return insertIntoUser(userEntity);
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    /**
     * Updates an existing User row in the User table within the easyTeach
     * database. The prepared statement needs the user's uesrNo, email,
     * userType, firstName, lastName and password.
     * 
     * @param userEntity
     *            is an instance of the class User
     * @return true if the User row is successfully updated in the easyTeach
     *         database, otherwise false.
     * @see User
     */
    public static boolean updateUserRow(User userEntity) {
        String sql = "{call updateUserRow(?,?,?,?,?,?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, userEntity.getUserNo());
            stmt.setString(2, userEntity.getUserType());
            stmt.setString(3, userEntity.getEmail());
            stmt.setString(4, userEntity.getFirstName());
            stmt.setString(5, userEntity.getLastName());
            stmt.setString(6, userEntity.getPassword());

            stmt.execute();
            return true;
            
        } catch (SQLTransientConnectionException SQLtce) {
            return updateUserRow(userEntity);
        } catch (SQLTransientException SQLte) {
            return updateUserRow(userEntity);
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    /**
     * Updates an existing User's password in the User table within the
     * easyTeach database. The prepared statement needs the user's userNo and
     * the new password.
     * 
     * @param userNo
     *            is the primary key of the User table;
     * @param password
     *            is the new password
     * @return true if the User row is successfully updated in the easyTeach
     *         database, otherwise false.
     * @see User
     */
    public static boolean updateUserPassword(String userNo, String password) {
        String sql = "{call updatePassword(?,?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, userNo);
            stmt.setString(2, password);

            stmt.execute();
            return true;
            
        } catch (SQLTransientConnectionException SQLtce) {
            return updateUserPassword(userNo, password);
        } catch (SQLTransientException SQLte) {
            return updateUserPassword(userNo, password);
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    /**
     * Deletes an existing User row in the User table within the easyTeach
     * database. The prepared statement needs the User's userNo.
     * 
     * @param userNo
     *            is the primary key of the User table.
     * @return true if the User row is successfully deleted in the easyTeach
     *         database, otherwise false.
     * @see User
     */
    public static boolean deleteUserRow(String userNo) {
        String sql = "{call deleteUserRow(?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, userNo);

            stmt.execute();
            return true;
            
        } catch (SQLTransientConnectionException SQLtce) {
            return deleteUserRow(userNo);
        } catch (SQLTransientException SQLte) {
            return deleteUserRow(userNo);
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    /**
     * Returns all the rows from the database's User table in the form of a
     * HashSet containing User entities.
     * 
     * @return a HashSet with all the rows in the User table from the easyTeach
     *         database. The rows are converted into User entities.
     * @see User
     */
    public static HashSet<User> getUserRows() {
        String sql = "{call selectUserRows()}";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();) {

            HashSet<User> hashSet = new HashSet<User>();

            while (rs.next()) {
                User userEntity = new User();
                userEntity.setUserNo(rs.getString("userNo"));
                userEntity.setEmail(rs.getString("email"));
                userEntity.setUserType(rs.getString("userType"));
                userEntity.setFirstName(rs.getString("firstName"));
                userEntity.setLastName(rs.getString("lastName"));
                userEntity.setPassword(rs.getString("password"));
                userEntity.setDateAdded(rs.getDate("dateAdded"));

                hashSet.add(userEntity);
            }
            return hashSet;

        } catch (SQLTransientConnectionException SQLtce) {
            return getUserRows();
        } catch (SQLTransientException SQLte) {
            return getUserRows();
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    /**
     * Returns the user's from a specific class.
     * 
     * @param classNo
     *            The primary key of a Class table
     * @return A HashSet of users
     * @see User
     */
    public static HashSet<User> getUserRowsWithClassNo(String classNo) {
        String sql = "{call selectUserRowsWithClassNo(?)}";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, classNo);
            rs = stmt.executeQuery();
            
            HashSet<User> hashSet = new HashSet<User>();

            while (rs.next()) {
                User userEntity = new User();
                userEntity.setUserNo(rs.getString("userNo"));
                userEntity.setEmail(rs.getString("email"));
                userEntity.setUserType(rs.getString("userType"));
                userEntity.setFirstName(rs.getString("firstName"));
                userEntity.setLastName(rs.getString("lastName"));
                userEntity.setPassword(rs.getString("password"));
                userEntity.setDateAdded(rs.getDate("dateAdded"));

                hashSet.add(userEntity);
            }
            return hashSet;

        } catch (SQLTransientConnectionException SQLtce) {
            return getUserRowsWithClassNo(classNo);
        } catch (SQLTransientException SQLte) {
            return getUserRowsWithClassNo(classNo);
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Returns a row from the database's User table with a specific userNo.
     * 
     * @param userNo
     *            is the primary key of the User table.
     * @return An instance of User
     * @see User
     */
    public static User getUserRowWithUserNo(String userNo) {
        String sql = "{call selectUserRowWithUserNo(?)}";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, userNo);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                User userEntity = new User();
                userEntity.setUserNo(rs.getString("userNo"));
                userEntity.setEmail(rs.getString("email"));
                userEntity.setUserType(rs.getString("userType"));
                userEntity.setFirstName(rs.getString("firstName"));
                userEntity.setLastName(rs.getString("lastName"));
                userEntity.setPassword(rs.getString("password"));
                userEntity.setDateAdded(rs.getDate("dateAdded"));
    
                return userEntity;
            }
            
            return null;
            
        } catch (SQLTransientConnectionException SQLtce) {
            return getUserRowWithUserNo(userNo);
        } catch (SQLTransientException SQLte) {
            return getUserRowWithUserNo(userNo);
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Returns a row from the database's User table with a specific email.
     * 
     * @author Oliver Nielsen
     * 
     * @param email
     *            The email of a specific user
     * @return An instance of User with the specific email
     * @see User
     */
    public static User getUserRowWithEmail(String email) {
        String sql = "{call selectUserRowWithEmail(?)}";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                User userEntity = new User();
                userEntity.setUserNo(rs.getString("userNo"));
                userEntity.setEmail(rs.getString("email"));
                userEntity.setUserType(rs.getString("userType"));
                userEntity.setFirstName(rs.getString("firstName"));
                userEntity.setLastName(rs.getString("lastName"));
                userEntity.setPassword(rs.getString("password"));
                userEntity.setDateAdded(rs.getDate("dateAdded"));

                return userEntity;
            }

            return null;

        } catch (SQLTransientConnectionException SQLtce) {
            return getUserRowWithEmail(email);
        } catch (SQLTransientException SQLte) {
            return getUserRowWithEmail(email);
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

}
