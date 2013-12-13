package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
import java.sql.SQLTransientException;
import java.util.HashSet;

import com.easyTeach.common.entity.ClassCourseRelation;
import com.easyTeach.server.databaseConnector.ConnectionManager;

/**
 * The ClassCourseRelationWrapper is the class responsible for handling all the
 * prepared CRUD SQL statements for manipulating with the ClassCourseRelation
 * table residing in the MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see ClassCourseRelation
 * @date 1. December, 2013
 */

public class ClassCourseRelationWrapper {

    private static Connection conn = ConnectionManager.getInstance()
            .getConnection();

    /**
     * Inserts a new ClassCourseRelation row into the ClassCourseRelation table
     * within the easyTeach database. The prepared statement needs the
     * classCourseRelation's classNo and courseNo.
     * 
     * @param classCourseRelationEntity
     *            is an instance of the class ClassCourseRelation.
     * @return true if the classCourseRelationEntity is successfully inserted
     *         into the easyTeach database, otherwise false.
     * @see ClassCourseRelation
     */
    public static boolean insertIntoClassCourseRelation(
            ClassCourseRelation classCourseRelationEntity) {
        String sql = "{call insertIntoClassCourseRelation(?,?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, classCourseRelationEntity.getClassNo());
            stmt.setString(2, classCourseRelationEntity.getCourseNo());

            stmt.execute();
            return true;
            
        } catch (SQLTransientConnectionException SQLtce) {
            return insertIntoClassCourseRelation(classCourseRelationEntity);
        } catch (SQLTransientException SQLte) {
            return insertIntoClassCourseRelation(classCourseRelationEntity);
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
     * Deletes an existing ClassCourseRelation row in the ClassCourseRelation
     * table within the easyTeach database. The prepared statement needs the
     * classCourseRelation's classNo and courseNo.
     * 
     * @param classNo
     *            is part of the primary key of the ClassCourseRelation table.
     * @param courseNo
     *            is part of the primary key of the ClassCourseRelation table.
     * @return true if the ClassCourseRelation row is successfully deleted in
     *         the easyTeach database, otherwise false.
     * @see ClassCourseRelation
     */
    public static boolean deleteClassCourseRelationRow(String classNo,
            String courseNo) {
        String sql = "{call deleteClassCourseRelationRow(?,?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, classNo);
            stmt.setString(2, courseNo);

            stmt.execute();
            return true;
            
        } catch (SQLTransientConnectionException SQLtce) {
            return deleteClassCourseRelationRow(classNo, courseNo);
        } catch (SQLTransientException SQLte) {
            return deleteClassCourseRelationRow(classNo, courseNo);
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
     * Returns all the rows from the database's ClassCourseRelation table in the
     * form of a HashSet containing ClassCourseRelation entities.
     * 
     * @return a HashSet with all the rows in the ClassCourseRelation table from
     *         the easyTeach database. The rows are converted into
     *         ClassCourseRelation entities.
     * @see ClassCourseRelation
     */
    public static HashSet<ClassCourseRelation> getClassCourseRelationRows() {
        String sql = "{call selectClassCourseRelationRows()}";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();) {

            HashSet<ClassCourseRelation> hashSet = new HashSet<ClassCourseRelation>();

            while (rs.next()) {
                ClassCourseRelation classCourseRelationEntity = new ClassCourseRelation();
                classCourseRelationEntity.setClassNo(rs.getString("classNo"));
                classCourseRelationEntity.setCourseNo(rs.getString("courseNo"));

                hashSet.add(classCourseRelationEntity);
            }
            return hashSet;

        } catch (SQLTransientConnectionException SQLtce) {
            return getClassCourseRelationRows();
        } catch (SQLTransientException SQLte) {
            return getClassCourseRelationRows();
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
     * Returns all the rows from the database's ClassCourseRelation table with a
     * specific classNo in the form of a HashSet containing ClassCourseRelation
     * entities.
     * 
     * @param classNo
     *            is part of the primary key of the ClassCourseRelation table
     * @return a HashSet with all the matching rows in the ClassCourseRelation
     *         table from the easyTeach database. The rows are converted into
     *         ClassCourseRelation entities.
     * @see ClassCourseRelation
     */
    public static HashSet<ClassCourseRelation> getClassCourseRelationRowsWithClassNo(
            String classNo) {
        String sql = "{call selectClassCourseRelationRowsWithClassNo(?)}";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, classNo);
            rs = stmt.executeQuery();

            HashSet<ClassCourseRelation> hashSet = new HashSet<ClassCourseRelation>();

            while (rs.next()) {
                ClassCourseRelation classCourseRelationEntity = new ClassCourseRelation();
                classCourseRelationEntity.setClassNo(rs.getString("classNo"));
                classCourseRelationEntity.setCourseNo(rs.getString("courseNo"));

                hashSet.add(classCourseRelationEntity);
            }

            return hashSet;

        } catch (SQLTransientConnectionException SQLtce) {
            return getClassCourseRelationRowsWithClassNo(classNo);
        } catch (SQLTransientException SQLte) {
            return getClassCourseRelationRowsWithClassNo(classNo);
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
     * Returns all the rows from the database's ClassCourseRelation table with a
     * specific courseNo in the form of a HashSet containing ClassCourseRelation
     * entities.
     * 
     * @param courseNo
     *            is part of the primary key of the ClassCourseRelation table
     * @return a HashSet with all the matching rows in the ClassCourseRelation
     *         table from the easyTeach database. The rows are converted into
     *         ClassCourseRelation entities.
     * @see ClassCourseRelation
     */
    public static HashSet<ClassCourseRelation> getClassCourseRelationRowsWithCourseNo(
            String courseNo) {
        String sql = "{call selectClassCourseRelationRowsWithCourseNo(?)}";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, courseNo);
            rs = stmt.executeQuery();

            HashSet<ClassCourseRelation> hashSet = new HashSet<ClassCourseRelation>();

            while (rs.next()) {
                ClassCourseRelation classCourseRelationEntity = new ClassCourseRelation();
                classCourseRelationEntity.setClassNo(rs.getString("classNo"));
                classCourseRelationEntity.setCourseNo(rs.getString("courseNo"));

                hashSet.add(classCourseRelationEntity);
            }

            return hashSet;

        } catch (SQLTransientConnectionException SQLtce) {
            return getClassCourseRelationRowsWithCourseNo(courseNo);
        } catch (SQLTransientException SQLte) {
            return getClassCourseRelationRowsWithCourseNo(courseNo);
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
