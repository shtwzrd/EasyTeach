package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
import java.sql.SQLTransientException;
import java.util.HashSet;

import com.easyTeach.common.entity.ExerciseQuestionRelation;
import com.easyTeach.server.databaseConnector.ConnectionManager;

/**
 * The ExerciseQuestionRelationWrapper is the class responsible for handling all
 * the prepared CRUD SQL statements for manipulating with the
 * ExerciseQuestionRelation table residing in the MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see ExerciseQuestionRelation
 * @date 30. November, 2013
 */

public class ExerciseQuestionRelationWrapper {

    /**
     * Inserts a new ExerciseQuestionRelation row into the
     * ExerciseQuestionRelation table within the easyTeach database. The
     * prepared statement needs the ExerciseQuestionRelation's exerciseNo and
     * questionNo.
     * 
     * @param exerciseQuestionRelationEntity
     *            is an instance of the class ExerciseQuestionRelation
     * @return true if the exerciseQuestionRelationEntity is successfully
     *         inserted into the easyTeach database, otherwise false.
     * @see ExerciseQuestionRelation
     */
    public static boolean insertIntoExerciseQuestionRelation(
            ExerciseQuestionRelation exerciseQuestionRelationEntity) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call insertIntoExerciseQuestionRelation(?,?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, exerciseQuestionRelationEntity.getExerciseNo());
            stmt.setString(2, exerciseQuestionRelationEntity.getQuestionNo());

            stmt.execute();
            return true;

        } catch (SQLTransientConnectionException SQLtce) {
            return insertIntoExerciseQuestionRelation(exerciseQuestionRelationEntity);
        } catch (SQLTransientException SQLte) {
            return insertIntoExerciseQuestionRelation(exerciseQuestionRelationEntity);
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
     * Deletes an existing ExerciseQuestionRelation row in the
     * ExerciseQuestionRelation table within the easyTeach database. The
     * prepared statement needs the ExerciseQuestionRelation's userNo and
     * questionNo.
     * 
     * @param exerciseNo
     *            is part of the primary key of the ExerciseQuestionRelation
     *            table.
     * @param questionNo
     *            is part of the primary key of the ExerciseQuestionRelation
     *            table.
     * @return true if the ExerciseQuestionRelation row is successfully deleted
     *         in the easyTeach database, otherwise false.
     * @see ExerciseQuestionRelation
     */
    public static boolean deleteExerciseQuestionRelationRow(String exerciseNo,
            String questionNo) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call deleteExerciseQuestionRelationRow(?,?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, exerciseNo);
            stmt.setString(2, questionNo);

            stmt.execute();
            return true;

        } catch (SQLTransientConnectionException SQLtce) {
            return deleteExerciseQuestionRelationRow(exerciseNo, questionNo);
        } catch (SQLTransientException SQLte) {
            return deleteExerciseQuestionRelationRow(exerciseNo, questionNo);
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
     * Returns all the rows from the database's ExerciseQuestionRelation table
     * in the form of a HashSet containing ExerciseQuestionRelation entities.
     * 
     * @return a HashSet with all the rows in the ExerciseQuestionRelation table
     *         from the easyTeach database. The rows are converted into
     *         ExerciseQuestionRelation entities.
     * @see ExerciseQuestionRelation
     */
    public static HashSet<ExerciseQuestionRelation> getExerciseQuestionRelationRows() {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call selectExerciseQuestionRelationRows()}";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();) {

            HashSet<ExerciseQuestionRelation> hashSet = new HashSet<ExerciseQuestionRelation>();

            while (rs.next()) {
                ExerciseQuestionRelation exerciseQuestionRelationEntity = new ExerciseQuestionRelation();
                exerciseQuestionRelationEntity.setExerciseNo(rs
                        .getString("exerciseNo"));
                exerciseQuestionRelationEntity.setQuestionNo(rs
                        .getString("questionNo"));

                hashSet.add(exerciseQuestionRelationEntity);
            }
            return hashSet;

        } catch (SQLTransientConnectionException SQLtce) {
            return getExerciseQuestionRelationRows();
        } catch (SQLTransientException SQLte) {
            return getExerciseQuestionRelationRows();
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
     * Returns all the rows from the database's ExerciseQuestionRelation table
     * with a specific exerciseNo in the form of a HashSet containing
     * ExerciseQuestionRelation entities.
     * 
     * @param exerciseNo
     *            is part of the primary key of the ExerciseQuestionRelation
     *            table
     * @return a HashSet with all the matching rows in the
     *         ExerciseQuestionRelation table from the easyTeach database. The
     *         rows are converted into ExerciseQuestionRelation entities.
     * @see ExerciseQuestionRelation
     */
    public static HashSet<ExerciseQuestionRelation> getExerciseQuestionRelationRowsWithExerciseNo(
            String exerciseNo) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call selectExerciseQuestionRelationRowsWithExerciseNo(?)}";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, exerciseNo);
            rs = stmt.executeQuery();

            HashSet<ExerciseQuestionRelation> hashSet = new HashSet<ExerciseQuestionRelation>();

            while (rs.next()) {
                ExerciseQuestionRelation exerciseQuestionRelationEntity = new ExerciseQuestionRelation();
                exerciseQuestionRelationEntity.setExerciseNo(rs
                        .getString("exerciseNo"));
                exerciseQuestionRelationEntity.setQuestionNo(rs
                        .getString("questionNo"));

                hashSet.add(exerciseQuestionRelationEntity);
            }

            return hashSet;

        } catch (SQLTransientConnectionException SQLtce) {
            return getExerciseQuestionRelationRowsWithExerciseNo(exerciseNo);
        } catch (SQLTransientException SQLte) {
            return getExerciseQuestionRelationRowsWithExerciseNo(exerciseNo);
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
     * Returns all the rows from the database's ExerciseQuestionRelation table
     * with a specific questionNo in the form of a HashSet containing
     * ExerciseQuestionRelation entities.
     * 
     * @param questionNo
     *            is part of the primary key of the ExerciseQuestionRelation
     *            table
     * @return a HashSet with all the matching rows in the
     *         ExerciseQuestionRelation table from the easyTeach database. The
     *         rows are converted into ExerciseQuestionRelation entities.
     * @see ExerciseQuestionRelation
     */
    public static HashSet<ExerciseQuestionRelation> getExerciseQuestionRelationRowsWithQuestionNo(
            String questionNo) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call selectExerciseQuestionRelationRowsWithQuestionNo(?)}";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, questionNo);
            rs = stmt.executeQuery();

            HashSet<ExerciseQuestionRelation> hashSet = new HashSet<ExerciseQuestionRelation>();

            while (rs.next()) {
                ExerciseQuestionRelation exerciseQuestionRelationEntity = new ExerciseQuestionRelation();
                exerciseQuestionRelationEntity.setExerciseNo(rs
                        .getString("exerciseNo"));
                exerciseQuestionRelationEntity.setQuestionNo(rs
                        .getString("questionNo"));

                hashSet.add(exerciseQuestionRelationEntity);
            }

            return hashSet;

        } catch (SQLTransientConnectionException SQLtce) {
            return getExerciseQuestionRelationRowsWithQuestionNo(questionNo);
        } catch (SQLTransientException SQLte) {
            return getExerciseQuestionRelationRowsWithQuestionNo(questionNo);
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
