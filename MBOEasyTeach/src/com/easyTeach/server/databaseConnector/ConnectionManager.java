package com.easyTeach.server.databaseConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** 
 * <p>
 * The ConnectionManager class is a singleton class for handling the
 * connection to the database used for the "MBO EasyTeach "application.
 * Because it is a singleton class only one connection to the database
 * can be present at one moment in time within a single application.
 * </p>
 * 
 * <p>
 * The class has a private empty constructor along with methods for opening,
 * closing and getting the connection to the database.
 * </p>
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 26. November, 2013
 */

public class ConnectionManager {
    
    private static ConnectionManager instance = null;

    private static final String USERNAME = "dbuser";
    private static final String PASSWORD = "dbpassword";
    private static final String CONN_STRING = "jdbc:mysql://localhost/easyTeach";
    private Connection conn = null;
    
    /** 
     * Private constructor made so that an instance of ConnectionManager can
     * only be created from within the class itself. 
     */
    private ConnectionManager() {
        // Empty constructor...
    }

    /** 
     * Used to get the instance of the ConnectionManager when needed 
     * in other classes. If the instance is not already instantiated 
     * (i.e. if it is null), it will be. 
     * @return an instance of ConnectionManager 
     */
    public static synchronized ConnectionManager getInstance() {
        if (instance == null) { 
            instance = new ConnectionManager();
        }
        return instance;
    }

    /** 
     * Opens the connection to the database by using the constants for
     * the database's address along with the username and password of
     * the database user.
     * @return true if the connection was successfully made, otherwise false.
     */
    private synchronized boolean openConnection() { 
        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            return true;
        }
        catch (SQLException e) { 
            System.err.println(e);
            return false;
        }
    }

    /**
     * Returns the Connection object used to communicate with the database.
     * If the connection is null a new connection will try to be opened using
     * the {@link #openConnection()} method.
     * @see #openConnection()
     * @return the connection to the database.
     */
    public Connection getConnection() {
        if (conn == null) {
            if (openConnection()) {
                System.out.println("Connection opened");
                return conn;
            } else {
                return null;
            }
        }
        return conn;
    }

    /**
     * Closes the connection to the database and set's the global
     * connection variable to null.
     */
    public void close() {
        System.out.println("Closing connection");
        try {
            conn.close();
            conn = null;
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
}
