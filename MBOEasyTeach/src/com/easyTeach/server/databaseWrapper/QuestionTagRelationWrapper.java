package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.easyTeach.common.entity.QuestionTagRelation;
import com.easyTeach.server.databaseConnector.ConnectionManager;

/**
 * The QuestionTagRelationWrapper is the class responsible for handling all the
 * prepared CRUD SQL statements for manipulating with the QuestionTagRelation
 * table residing in the MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see QuestionTagRelation
 * @date 1. December, 2013
 */

public class QuestionTagRelationWrapper {

    private static Connection conn = ConnectionManager.getInstance()
            .getConnection();

    /**
     * Inserts a new QuestionTagRelation row into the QuestionTagRelation table
     * within the easyTeach database. The prepared statement needs the
     * QuestionTagRelation's questionNo and tagNo.
     * 
     * @param questionTagRelationEntity
     *            is an instance of the class QuestionTagRelation
     * @return true if the questionTagRelationEntity is successfully inserted
     *         into the easyTeach database, otherwise false.
     * @see QuestionTagRelation
     */
    public static boolean insertIntoQuestionTagRelation(
            QuestionTagRelation questionTagRelationEntity) {
        String sql = "{call insertIntoQuestionTagRelation(?,?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, questionTagRelationEntity.getQuestionNo());
            stmt.setString(2, questionTagRelationEntity.getTagNo());

            int affected = stmt.executeUpdate();
            if (affected == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    /**
     * Deletes an existing QuestionTagRelation row in the QuestionTagRelation
     * table within the easyTeach database. The prepared statement needs the
     * QuestionTagRelation's userNo and questionNo.
     * 
     * @param questionNo
     *            is part of the primary key of the QuestionTagRelation table.
     * @param tagNo
     *            is part of the primary key of the QuestionTagRelation table.
     * @return true if the QuestionTagRelation row is successfully deleted in
     *         the easyTeach database, otherwise false.
     * @see QuestionTagRelation
     */
    public static boolean deleteQuestionTagRelationRow(String questionNo,
            String tagNo) {
        String sql = "{call deleteQuestionTagRelationRow(?, ?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, questionNo);
            stmt.setString(2, tagNo);

            int affected = stmt.executeUpdate();
            if (affected == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    /**
     * Returns all the rows from the database's QuestionTagRelation table in the
     * form of a HashSet containing QuestionTagRelation entities.
     * 
     * @return a HashSet with all the rows in the QuestionTagRelation table from
     *         the easyTeach database. The rows are converted into
     *         QuestionTagRelation entities.
     * @see QuestionTagRelation
     */
    public static HashSet<QuestionTagRelation> getQuestionTagRelationRows() {
        String sql = "{call selectQuestionTagRelationRows()}";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();) {

            HashSet<QuestionTagRelation> hashSet = new HashSet<QuestionTagRelation>();

            while (rs.next()) {
                QuestionTagRelation questionTagRelationEntity = new QuestionTagRelation();
                questionTagRelationEntity.setQuestionNo(rs
                        .getString("questionNo"));
                questionTagRelationEntity.setTagNo(rs.getString("tagNo"));

                hashSet.add(questionTagRelationEntity);
            }
            return hashSet;

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    /**
     * Returns all the rows from the database's QuestionTagRelation table with a
     * specific questionNo in the form of a HashSet containing
     * QuestionTagRelation entities.
     * 
     * @param questionNo
     *            is part of the primary key of the QuestionTagRelation table
     * @return a HashSet with all the matching rows in the QuestionTagRelation
     *         table from the easyTeach database. The rows are converted into
     *         QuestionTagRelation entities.
     * @see QuestionTagRelation
     */
    public static HashSet<QuestionTagRelation> getQuestionTagRelationRowsWithQuestionNo(
            String questionNo) {
        String sql = "{call selectQuestionTagRelationRowsWithQuestionNo(?)}";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, questionNo);
            rs = stmt.executeQuery();

            HashSet<QuestionTagRelation> hashSet = new HashSet<QuestionTagRelation>();

            while (rs.next()) {
                QuestionTagRelation questionTagRelationEntity = new QuestionTagRelation();
                questionTagRelationEntity.setQuestionNo(rs
                        .getString("questionNo"));
                questionTagRelationEntity.setTagNo(rs.getString("tagNo"));

                hashSet.add(questionTagRelationEntity);
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
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Returns all the rows from the database's QuestionTagRelation table with a
     * specific tagNo in the form of a HashSet containing QuestionTagRelation
     * entities.
     * 
     * @param tagNo
     *            is part of the primary key of the QuestionTagRelation table
     * @return a HashSet with all the matching rows in the QuestionTagRelation
     *         table from the easyTeach database. The rows are converted into
     *         QuestionTagRelation entities.
     * @see QuestionTagRelation
     */
    public static HashSet<QuestionTagRelation> getQuestionTagRelationRowsWithTagNo(
            String tagNo) {
        String sql = "{call selectQuestionTagRelationRowsWithTagNo(?)}";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, tagNo);
            rs = stmt.executeQuery();

            HashSet<QuestionTagRelation> hashSet = new HashSet<QuestionTagRelation>();

            while (rs.next()) {
                QuestionTagRelation questionTagRelationEntity = new QuestionTagRelation();
                questionTagRelationEntity.setQuestionNo(rs
                        .getString("questionNo"));
                questionTagRelationEntity.setTagNo(rs.getString("tagNo"));

                hashSet.add(questionTagRelationEntity);
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
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

}
