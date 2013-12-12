package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
import java.sql.SQLTransientException;
import java.util.HashSet;

import com.easyTeach.common.entity.ClassUserRelation;
import com.easyTeach.server.databaseConnector.ConnectionManager;

/**
 * The ClassUserRelationWrapper is the class responsible for handling all the
 * prepared CRUD SQL statements for manipulating with the ClassUserRelation
 * table residing in the MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see ClassUserRelation
 * @date 30. November, 2013
 */

public class ClassUserRelationWrapper {

    private static Connection conn = ConnectionManager.getInstance()
            .getConnection();

    /**
     * Inserts a new ClassUserRelation row into the ClassUserRelation table
     * within the easyTeach database. The prepared statement needs the
     * classUserRelation's classNo and userNo.
     * 
     * @param classUserRelationEntity
     *            is an instance of the class ClassUserRelation.
     * @return true if the classUserRelationEntity is successfully inserted into
     *         the easyTeach database, otherwise false.
     * @see ClassUserRelation
     */
    public static boolean insertIntoClassUserRelation(
            ClassUserRelation classUserRelationEntity) {
        String sql = "{call insertIntoClassUserRelation(?,?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, classUserRelationEntity.getClassNo());
            stmt.setString(2, classUserRelationEntity.getUserNo());

            stmt.execute();
            return true;
            
        } catch (SQLTransientConnectionException SQLtce) {
            return insertIntoClassUserRelation(classUserRelationEntity);
        } catch (SQLTransientException SQLte) {
            return insertIntoClassUserRelation(classUserRelationEntity);
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    /**
     * Deletes an existing ClassUserRelation row in the ClassUserRelation table
     * within the easyTeach database. The prepared statement needs the
     * classUserRelation's classNo and userNo.
     * 
     * @param classNo
     *            is part of the primary key of the ClassUserRelation table.
     * @param userNo
     *            is part of the primary key of the ClassUserRelation table.
     * @return true if the ClassUserRelation row is successfully deleted in the
     *         easyTeach database, otherwise false.
     * @see ClassUserRelation
     */
    public static boolean deleteClassUserRelationRow(String classNo,
            String userNo) {
        String sql = "{call deleteClassUserRelationRow(?,?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, classNo);
            stmt.setString(2, userNo);

            stmt.execute();
            return true;
            
        } catch (SQLTransientConnectionException SQLtce) {
            return deleteClassUserRelationRow(classNo, userNo);
        } catch (SQLTransientException SQLte) {
            return deleteClassUserRelationRow(classNo, userNo);
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    /**
     * Returns all the rows from the database's ClassUserRelation table in the
     * form of a HashSet containing ClassUserRelation entities.
     * 
     * @return a HashSet with all the rows in the ClassUserRelation table from
     *         the easyTeach database. The rows are converted into
     *         ClassUserRelation entities.
     * @see ClassUserRelation
     */
    public static HashSet<ClassUserRelation> getClassUserRelationRows() {
        String sql = "{call selectClassUserRelationRows()}";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();) {

            HashSet<ClassUserRelation> hashSet = new HashSet<ClassUserRelation>();

            while (rs.next()) {
                ClassUserRelation classUserRelationEntity = new ClassUserRelation();
                classUserRelationEntity.setClassNo(rs.getString("classNo"));
                classUserRelationEntity.setUserNo(rs.getString("userNo"));

                hashSet.add(classUserRelationEntity);
            }
            return hashSet;
            
        } catch (SQLTransientConnectionException SQLtce) {
            return getClassUserRelationRows();
        } catch (SQLTransientException SQLte) {
            return getClassUserRelationRows();
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    /**
     * Returns all the rows from the database's ClassUserRelation table with a
     * specific classNo in the form of a HashSet containing ClassUserRelation
     * entities.
     * 
     * @param classNo
     *            is part of the primary key of the ClassUserRelation table
     * @return a HashSet with all the matching rows in the ClassUserRelation
     *         table from the easyTeach database. The rows are converted into
     *         ClassUserRelation entities.
     * @see ClassUserRelation
     */
    public static HashSet<ClassUserRelation> getClassUserRelationRowsWithClassNo(
            String classNo) {
        String sql = "{call selectClassUserRelationRowsWithClassNo(?)}";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, classNo);
            rs = stmt.executeQuery();

            HashSet<ClassUserRelation> hashSet = new HashSet<ClassUserRelation>();

            while (rs.next()) {
                ClassUserRelation classUserRelationEntity = new ClassUserRelation();
                classUserRelationEntity.setClassNo(rs.getString("classNo"));
                classUserRelationEntity.setUserNo(rs.getString("userNo"));

                hashSet.add(classUserRelationEntity);
            }

            return hashSet;

        } catch (SQLTransientConnectionException SQLtce) {
            return getClassUserRelationRowsWithClassNo(classNo);
        } catch (SQLTransientException SQLte) {
            return getClassUserRelationRowsWithClassNo(classNo);
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    /**
     * Returns all the rows from the database's ClassUserRelation table with a
     * specific userNo in the form of a HashSet containing ClassUserRelation
     * entities.
     * 
     * @param userNo
     *            is part of the primary key of the ClassUserRelation table
     * @return a HashSet with all the matching rows in the ClassUserRelation
     *         table from the easyTeach database. The rows are converted into
     *         ClassUserRelation entities.
     * @see ClassUserRelation
     */
    public static HashSet<ClassUserRelation> getClassUserRelationRowsWithUserNo(
            String userNo) {
        String sql = "{call selectClassUserRelationRowsWithUserNo(?)}";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, userNo);
            rs = stmt.executeQuery();

            HashSet<ClassUserRelation> hashSet = new HashSet<ClassUserRelation>();

            while (rs.next()) {
                ClassUserRelation classUserRelationEntity = new ClassUserRelation();
                classUserRelationEntity.setClassNo(rs.getString("classNo"));
                classUserRelationEntity.setUserNo(rs.getString("userNo"));

                hashSet.add(classUserRelationEntity);
            }

            return hashSet;

        } catch (SQLTransientConnectionException SQLtce) {
            return getClassUserRelationRowsWithUserNo(userNo);
        } catch (SQLTransientException SQLte) {
            return getClassUserRelationRowsWithUserNo(userNo);
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
