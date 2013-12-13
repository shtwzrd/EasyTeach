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
import com.easyTeach.common.entity.Question;
import com.easyTeach.server.databaseConnector.ConnectionManager;

/**
 * The ClassCourseRelationWrapper is the class responsible for handling all the
 * prepared CRUD SQL statements for manipulating with the ClassCourseRelation
 * table residing in the MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.1
 * @see ClassCourseRelation
 * @date 30. November, 2013
 */

public class QuestionWrapper {

    private static Connection conn = ConnectionManager.getInstance()
            .getConnection();

    /**
     * Inserts a new Question row into the Question table within the easyTeach
     * database. The prepared statement needs the Question's questionNo,
     * questionType, question and points.
     * 
     * @param questionEntity
     *            is an instance of the class Question
     * @return true if the questionEntity is successfully inserted into the
     *         easyTeach database, otherwise false.
     * @see Question
     */
    public static boolean insertIntoQuestion(Question questionEntity) {
        String sql = "{call insertIntoQuestion(?,?,?,?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, questionEntity.getQuestionNo());
            stmt.setString(2, questionEntity.getQuestionType());
            stmt.setString(3, questionEntity.getQuestion());
            stmt.setInt(4, questionEntity.getPoints());

            stmt.execute();
            return true;

        } catch (SQLTransientConnectionException SQLtce) {
            return insertIntoQuestion(questionEntity);
        } catch (SQLTransientException SQLte) {
            return insertIntoQuestion(questionEntity);
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
     * Updates an existing Question row in the Question table within the
     * easyTeach database. The prepared statement needs the Question's
     * questionNo, questionType, question and points.
     * 
     * @param questionEntity
     *            is an instance of the class Question
     * @return true if the Question row is successfully updated in the easyTeach
     *         database, otherwise false.
     * @see Question
     */
    public static boolean updateQuestionRow(Question questionEntity) {
        String sql = "{call updateQuestionRow(?,?,?,?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, questionEntity.getQuestionNo());
            stmt.setString(2, questionEntity.getQuestionType());
            stmt.setString(3, questionEntity.getQuestion());
            stmt.setInt(4, questionEntity.getPoints());

            stmt.execute();
            return true;

        } catch (SQLTransientConnectionException SQLtce) {
            return updateQuestionRow(questionEntity);
        } catch (SQLTransientException SQLte) {
            return updateQuestionRow(questionEntity);
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
     * Deletes an existing Question row in the Question table within the
     * easyTeach database. The prepared statement needs the Question's
     * questionNo.
     * 
     * @param questionNo
     *            is the primary key of the question table.
     * @return true if the Question row is successfully deleted in the easyTeach
     *         database, otherwise false.
     * @see Question
     */
    public static boolean deleteQuestionRow(String questionNo) {
        String sql = "{call deleteQuestionRow(?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, questionNo);

            stmt.execute();
            return true;

        } catch (SQLTransientConnectionException SQLtce) {
            return deleteQuestionRow(questionNo);
        } catch (SQLTransientException SQLte) {
            return deleteQuestionRow(questionNo);
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
     * Returns all the rows from the database's Question table in the form of a
     * HashSet containing Question entities.
     * 
     * @return a HashSet with all the rows in the Question table from the
     *         easyTeach database. The rows are converted into Question
     *         entities.
     * @see Question
     */
    public static HashSet<Question> getQuestionRows() {
        String sql = "{call selectQuestionRows()}";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();) {

            HashSet<Question> hashSet = new HashSet<Question>();

            while (rs.next()) {
                Question questionEntity = new Question();
                questionEntity.setQuestionNo(rs.getString("questionNo"));
                questionEntity.setQuestionType(rs.getString("questionType"));
                questionEntity.setQuestion(rs.getString("question"));
                questionEntity.setPoints(rs.getInt("points"));

                hashSet.add(questionEntity);
            }

            return hashSet;

        } catch (SQLTransientConnectionException SQLtce) {
            return getQuestionRows();
        } catch (SQLTransientException SQLte) {
            return getQuestionRows();
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
     * Returns a set of rows from the database's Question table with a specific
     * questionNo.
     * 
     * @param questionNo
     *            is the primary key of the Question table.
     * @return An instance of Question
     * @see Question
     */
    public static Question getQuestionRowWithQuestionNo(String questionNo)
            throws SQLException {
        String sql = "{call selectQuestionRowWithQuestionNo(?)}";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, questionNo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Question questionEntity = new Question();
                questionEntity.setQuestionNo(rs.getString("questionNo"));
                questionEntity.setQuestionType(rs.getString("questionType"));
                questionEntity.setQuestion(rs.getString("question"));
                questionEntity.setPoints(rs.getInt("points"));

                return questionEntity;
            }

            return null;

        } catch (SQLTransientConnectionException SQLtce) {
            return getQuestionRowWithQuestionNo(questionNo);
        } catch (SQLTransientException SQLte) {
            return getQuestionRowWithQuestionNo(questionNo);
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
     * Returns a set of rows from the database's Question table with a specific
     * tagNo.
     * 
     * @param tagNo
     *            is the primary key of the Tag table.
     * @return A Set of Question with a specific tagNo
     * @see Question
     * @see Tag
     */
    public static HashSet<Question> getQuestionRowsWithTagNo(String tagNo) {
        String sql = "{call selectQuestionRowWithTagNo(?)}";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, tagNo);
            rs = stmt.executeQuery();

            HashSet<Question> hashSet = new HashSet<Question>();

            while (rs.next()) {
                Question questionEntity = new Question();
                questionEntity.setQuestionNo(rs.getString("questionNo"));
                questionEntity.setQuestionType(rs.getString("questionType"));
                questionEntity.setQuestion(rs.getString("question"));
                questionEntity.setPoints(rs.getInt("points"));

                hashSet.add(questionEntity);
            }
            return hashSet;

        } catch (SQLTransientConnectionException SQLtce) {
            return getQuestionRowsWithTagNo(tagNo);
        } catch (SQLTransientException SQLte) {
            return getQuestionRowsWithTagNo(tagNo);
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
     * Returns a row from the database's Question table with a specific tag.
     * 
     * @param tag
     *            unique key of the Tag table
     * @return A Set of Question with a specific tag
     * @see Question
     * @see Tag
     */
    public static HashSet<Question> getQuestionRowsWithTag(String tag) {
        String sql = "{call selectQuestionRowWithTag(?)}";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, tag);
            rs = stmt.executeQuery();

            HashSet<Question> hashSet = new HashSet<Question>();

            while (rs.next()) {
                Question questionEntity = new Question();
                questionEntity.setQuestionNo(rs.getString("questionNo"));
                questionEntity.setQuestionType(rs.getString("questionType"));
                questionEntity.setQuestion(rs.getString("question"));
                questionEntity.setPoints(rs.getInt("points"));

                hashSet.add(questionEntity);
            }
            return hashSet;

        } catch (SQLTransientConnectionException SQLtce) {
            return getQuestionRowsWithTag(tag);
        } catch (SQLTransientException SQLte) {
            return getQuestionRowsWithTag(tag);
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
