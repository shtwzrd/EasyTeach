package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
import java.sql.SQLTransientException;
import java.util.HashSet;

import com.easyTeach.common.entity.Class;
import com.easyTeach.server.databaseConnector.ConnectionManager;

/**
 * The ClassWrapper is the class responsible for handling all the prepared CRUD
 * SQL statements for manipulating with the Class table residing in MBO
 * EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see Class
 * @date 30. November, 2013
 */

public class ClassWrapper {

    /**
     * Inserts a new Class row into the Class table within the easyTeach
     * database. The prepared statement needs the class' classNo, year and name.
     * 
     * @param classEntity
     *            is an instance of the class Class
     * @return true if the classEntity is successfully inserted into the
     *         easyTeach database, otherwise false.
     * @see Class
     */
    public static boolean insertIntoClass(Class classEntity) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call insertIntoClass(?,?,?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, classEntity.getClassNo());
            stmt.setInt(2, classEntity.getYear());
            stmt.setString(3, classEntity.getClassName());

            stmt.execute();
            return true;

        } catch (SQLTransientConnectionException SQLtce) {
            return insertIntoClass(classEntity);
        } catch (SQLTransientException SQLte) {
            return insertIntoClass(classEntity);
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates an existing Class row in the Class table within the easyTeach
     * database. The prepared statement needs the class' classNo, year and name.
     * 
     * @param classEntity
     *            is an instance of the class Class
     * @return true if the Class row is successfully updated in the easyTeach
     *         database, otherwise false.
     * @see Class
     */
    public static boolean updateClassRow(Class classEntity) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call updateClassRow(?,?,?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, classEntity.getClassNo());
            stmt.setInt(2, classEntity.getYear());
            stmt.setString(3, classEntity.getClassName());

            stmt.execute();
            return true;

        } catch (SQLTransientConnectionException SQLtce) {
            return updateClassRow(classEntity);
        } catch (SQLTransientException SQLte) {
            return updateClassRow(classEntity);
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Deletes an existing Class row in the Class table within the easyTeach
     * database. The prepared statement needs the class' classNo.
     * 
     * @param classNo
     *            is the primary key of the Class table.
     * @return true if the Class row is successfully deleted in the easyTeach
     *         database, otherwise false.
     * @see Class
     */
    public static boolean deleteClassRow(String classNo) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call deleteClassRow(?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, classNo);
            stmt.execute();
            return true;

        } catch (SQLTransientConnectionException SQLtce) {
            return deleteClassRow(classNo);
        } catch (SQLTransientException SQLte) {
            return deleteClassRow(classNo);
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns all the rows from the database's Class table in the form of a
     * HashSet containing Class entities.
     * 
     * @return a HashSet with all the rows in the Class table from the easyTeach
     *         database. The rows are converted into Class entities.
     * @see Class
     */
    public static HashSet<Class> getClassRows() {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call selectClassRows()}";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();) {

            HashSet<Class> hashSet = new HashSet<>();

            while (rs.next()) {
                Class classEntity = new Class();
                classEntity.setClassNo(rs.getString("classNo"));
                classEntity.setYear(rs.getInt("year"));
                classEntity.setClassName(rs.getString("className"));

                hashSet.add(classEntity);
            }
            return hashSet;

        } catch (SQLTransientConnectionException SQLtce) {
            return getClassRows();
        } catch (SQLTransientException SQLte) {
            return getClassRows();
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns a row from the database's Class table with a specific classNo.
     * 
     * @param classNo
     *            is the primary key of the Class table.
     * @return An instance of Class
     * @see Class
     */
    public static Class getClassRowByClassNo(String classNo) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call selectClassRowWithClassNo(?)}";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, classNo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Class classEntity = new Class();
                classEntity.setClassNo(rs.getString("classNo"));
                classEntity.setYear(rs.getInt("year"));
                classEntity.setClassName(rs.getString("className"));

                return classEntity;
            }

            return null;

        } catch (SQLTransientConnectionException SQLtce) {
            return getClassRowByClassNo(classNo);
        } catch (SQLTransientException SQLte) {
            return getClassRowByClassNo(classNo);
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Returns a row from the database's Class table with a specific className.
     * 
     * @param className
     *            is a unique row in the Class table.
     * @return An instance of Class
     * @see Class
     */
    public static Class getClassRowByClassName(String className) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call selectClassRowWithClassName(?)}";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, className);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Class classEntity = new Class();
                classEntity.setClassNo(rs.getString("classNo"));
                classEntity.setYear(rs.getInt("year"));
                classEntity.setClassName(rs.getString("className"));

                return classEntity;
            }

            return null;

        } catch (SQLTransientConnectionException SQLtce) {
            return getClassRowByClassName(className);
        } catch (SQLTransientException SQLte) {
            return getClassRowByClassName(className);
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Returns all the rows from the database's Class table in the form of a
     * HashSet containing Class entities.
     * 
     * @return a HashSet with all the rows in the Class table from the easyTeach
     *         database. The rows are converted into Class entities.
     * @see Class
     */
    public static HashSet<Class> getClassesWithUserNo(String userNo) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call selectClassWithUserNo(?)}";

        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, userNo);
            rs = stmt.executeQuery();

            HashSet<Class> hashSet = new HashSet<Class>();

            while (rs.next()) {
                Class classEntity = new Class();
                classEntity.setClassNo(rs.getString("classNo"));
                classEntity.setYear(rs.getInt("year"));
                classEntity.setClassName(rs.getString("className"));

                hashSet.add(classEntity);
            }
            return hashSet;

        } catch (SQLTransientConnectionException SQLtce) {
            return getClassesWithUserNo(userNo);
        } catch (SQLTransientException SQLte) {
            return getClassesWithUserNo(userNo);
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Returns all the classes within a specific course.
     * 
     * @param courseNo
     *            the primary key of the Course table.
     * @return a HashSet containing all classes within a specific course.
     * @see Class
     * @see Course
     */
    public static HashSet<Class> getClassesWithCourseNo(String courseNo) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call selectClassesWithCourseNo(?)}";

        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, courseNo);
            rs = stmt.executeQuery();

            HashSet<Class> hashSet = new HashSet<Class>();

            while (rs.next()) {
                Class classEntity = new Class();
                classEntity.setClassNo(rs.getString("classNo"));
                classEntity.setYear(rs.getInt("year"));
                classEntity.setClassName(rs.getString("className"));

                hashSet.add(classEntity);
            }
            return hashSet;

        } catch (SQLTransientConnectionException SQLtce) {
            return getClassesWithCourseNo(courseNo);
        } catch (SQLTransientException SQLte) {
            return getClassesWithCourseNo(courseNo);
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

}
