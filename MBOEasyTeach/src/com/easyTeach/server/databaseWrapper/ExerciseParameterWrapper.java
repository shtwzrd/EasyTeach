package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
import java.sql.SQLTransientException;
import java.util.HashSet;

import com.easyTeach.common.entity.ExerciseParameter;
import com.easyTeach.server.databaseConnector.ConnectionManager;

/**
 * The ExerciseParameterWrapper is the class responsible for handling all the
 * prepared CRUD SQL statements for manipulating with the ExerciseParameter
 * table residing in the MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see ExerciseParameter
 * @date 30. November, 2013
 */

public class ExerciseParameterWrapper {

    /**
     * Inserts a new ExerciseParameter row into the ExerciseParameter table
     * within the easyTeach database. The prepared statement needs the
     * exerciseParameter's exerciseParamaterNo, isTest, isLocked, accessBegin,
     * accessEnd and timeLimit.
     * 
     * @param exerciseParameterEntity
     *            is an instance of the class ExerciseParameter.
     * @return true if the exerciseParameterEntity is successfully inserted into
     *         the easyTeach database, otherwise false.
     * @see ExerciseParameter
     */
    public static boolean insertIntoExerciseParameter(
            ExerciseParameter exerciseParameterEntity) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call insertIntoExerciseParameter(?,?,?,?,?,?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, exerciseParameterEntity.getExerciseParameterNo());
            stmt.setBoolean(2, exerciseParameterEntity.getIsTest());
            stmt.setBoolean(3, exerciseParameterEntity.getIsLocked());
            stmt.setTimestamp(4, exerciseParameterEntity.getAccessBegin());
            stmt.setTimestamp(5, exerciseParameterEntity.getAccessEnd());
            stmt.setInt(6, exerciseParameterEntity.getTimeLimit());

            stmt.execute();
            return true;
            
        } catch (SQLTransientConnectionException SQLtce) {
            return insertIntoExerciseParameter(exerciseParameterEntity);
        } catch (SQLTransientException SQLte) {
            return insertIntoExerciseParameter(exerciseParameterEntity);
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
     * Updates an existing ExerciseParameter row in the ExerciseParameter table
     * within the easyTeach database. The prepared statement needs the
     * exerciseParameter's exerciseParameterNo, isTest, isLocked, accessBegin,
     * accessEnd and timeLimit.
     * 
     * @param exerciseParameterEntity
     *            is an instance of the class ExerciseParameter
     * @return true if the ExerciseParameter row is successfully updated in the
     *         easyTeach database, otherwise false.
     * @see ExerciseParameter
     */
    public static boolean updateExerciseParameterRow(
            ExerciseParameter exerciseParameterEntity) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call updateExerciseParameterRow(?,?,?,?,?,?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, exerciseParameterEntity.getExerciseParameterNo());
            stmt.setBoolean(2, exerciseParameterEntity.getIsTest());
            stmt.setBoolean(3, exerciseParameterEntity.getIsLocked());
            stmt.setTimestamp(4, exerciseParameterEntity.getAccessBegin());
            stmt.setTimestamp(5, exerciseParameterEntity.getAccessEnd());
            stmt.setInt(6, exerciseParameterEntity.getTimeLimit());

            stmt.execute();
            return true;
            
        } catch (SQLTransientConnectionException SQLtce) {
            return updateExerciseParameterRow(exerciseParameterEntity);
        } catch (SQLTransientException SQLte) {
            return updateExerciseParameterRow(exerciseParameterEntity);
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
     * Deletes an existing ExerciseParameter row in the ExerciseParameter table
     * within the easyTeach database. The prepared statement needs the
     * exerciseParameter's exerciseParameterNo.
     * 
     * @param exerciseParameterNo
     *            is the primary key of the ExerciseParameter table.
     * @return true if the ExerciseParameter row is successfully deleted in the
     *         easyTeach database, otherwise false.
     * @see ExerciseParameter
     */
    public static boolean deleteExerciseParameterRow(String exerciseParameterNo) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call deleteExerciseParameterRow(?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, exerciseParameterNo);

            stmt.execute();
            return true;
            
        } catch (SQLTransientConnectionException SQLtce) {
            return deleteExerciseParameterRow(exerciseParameterNo);
        } catch (SQLTransientException SQLte) {
            return deleteExerciseParameterRow(exerciseParameterNo);
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
     * Returns all the rows from the database's ExerciseParameter table in the
     * form of a HashSet containing ExerciseParameter entities.
     * 
     * @return a HashSet with all the rows in the ExerciseParameter table from
     *         the easyTeach database. The rows are converted into
     *         ExerciseParameter entities.
     * @see ExerciseParameter
     */
    public static HashSet<ExerciseParameter> getExerciseParameterRows() {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call selectExerciseParameterRows()}";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();) {

            HashSet<ExerciseParameter> hashSet = new HashSet<ExerciseParameter>();

            while (rs.next()) {
                ExerciseParameter exerciseParameterEntity = new ExerciseParameter();
                exerciseParameterEntity.setExerciseParameterNo(rs
                        .getString("exerciseParameterNo"));
                exerciseParameterEntity.setIsTest(rs.getBoolean("isTest"));
                exerciseParameterEntity.setIsLocked(rs.getBoolean("isLocked"));
                exerciseParameterEntity.setAccessBegin(rs
                        .getTimestamp("accessBegin"));
                exerciseParameterEntity.setAccessEnd(rs.getTimestamp("accessEnd"));
                exerciseParameterEntity.setTimeLimit(rs.getInt("timeLimit"));

                hashSet.add(exerciseParameterEntity);
            }
            return hashSet;

        } catch (SQLTransientConnectionException SQLtce) {
            return getExerciseParameterRows();
        } catch (SQLTransientException SQLte) {
            return getExerciseParameterRows();
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
     * Returns a row from the database's ExerciseParameter table with a specific
     * exerciseParameterNo.
     * 
     * @param exerciseParameterNo
     *            is the primary key of the ExerciseParameter table.
     * @return An instance of ExerciseParameter
     * @see ExerciseParameter
     */
    public static ExerciseParameter getExerciseParameterRowWithExerciseParameterNo(
            String exerciseParameterNo) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call selectExerciseParameterRowWithExerciseParameterNo(?)}";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, exerciseParameterNo);
            rs = stmt.executeQuery();
            rs.next();

            ExerciseParameter exerciseParameterEntity = new ExerciseParameter();
            exerciseParameterEntity.setExerciseParameterNo(rs
                    .getString("exerciseParameterNo"));
            exerciseParameterEntity.setIsTest(rs.getBoolean("isTest"));
            exerciseParameterEntity.setIsLocked(rs.getBoolean("isLocked"));
            exerciseParameterEntity.setAccessBegin(rs.getTimestamp("accessBegin"));
            exerciseParameterEntity.setAccessEnd(rs.getTimestamp("accessEnd"));
            exerciseParameterEntity.setTimeLimit(rs.getInt("timeLimit"));

            return exerciseParameterEntity;
            
        } catch (SQLTransientConnectionException SQLtce) {
            return getExerciseParameterRowWithExerciseParameterNo(exerciseParameterNo);
        } catch (SQLTransientException SQLte) {
            return getExerciseParameterRowWithExerciseParameterNo(exerciseParameterNo);
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
