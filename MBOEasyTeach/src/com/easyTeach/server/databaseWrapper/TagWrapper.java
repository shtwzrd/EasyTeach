package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
import java.sql.SQLTransientException;
import java.util.HashSet;

import com.easyTeach.common.entity.Tag;
import com.easyTeach.server.databaseConnector.ConnectionManager;

/**
 * The TagWrapper is the class responsible for handling all the prepared CRUD
 * SQL statements for manipulating with the Tag table residing in the MBO
 * EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see Tag
 * @date 30. November, 2013
 */

public class TagWrapper {

    private static Connection conn = ConnectionManager.getInstance()
            .getConnection();

    /**
     * Inserts a new Tag row into the Tag table within the easyTeach database.
     * The prepared statement needs the Tag's tagNo and tag.
     * 
     * @param tagEntity
     *            is an instance of the class Tag
     * @return true if the tagEntity is successfully inserted into the easyTeach
     *         database, otherwise false.
     * @see Tag
     */
    public static boolean insertIntoTag(Tag tagEntity) {
        String sql = "{call insertIntoTag(?,?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, tagEntity.getTagNo());
            stmt.setString(2, tagEntity.getTag());

            stmt.execute();
            return true;
            
        } catch (SQLTransientConnectionException SQLtce) {
            return insertIntoTag(tagEntity);
        } catch (SQLTransientException SQLte) {
            return updateTagRow(tagEntity);
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    /**
     * Updates an existing Tag row in the Tag table within the easyTeach
     * database. The prepared statement needs the Tag's tagNo and tag.
     * 
     * @param tagEntity
     *            is an instance of the class Tag
     * @return true if the Tag row is successfully updated in the easyTeach
     *         database, otherwise false.
     * @see Tag
     */
    public static boolean updateTagRow(Tag tagEntity) {
        String sql = "{call updateTagRow(?,?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, tagEntity.getTagNo());
            stmt.setString(2, tagEntity.getTag());

            stmt.execute();
            return true;
            
        } catch (SQLTransientConnectionException SQLtce) {
            return updateTagRow(tagEntity);
        } catch (SQLTransientException SQLte) {
            return updateTagRow(tagEntity);
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    /**
     * Deletes an existing Tag row in the Tag table within the easyTeach
     * database. The prepared statement needs the Tag's tagNo.
     * 
     * @param tagNo
     *            is the primary key of the Tag table.
     * @return true if the Tag row is successfully deleted in the easyTeach
     *         database, otherwise false.
     * @see Tag
     */
    public static boolean deleteTagRow(String tagNo) {
        String sql = "{call deleteTagRow(?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, tagNo);

            stmt.execute();
            return true;
            
        } catch (SQLTransientConnectionException SQLtce) {
            return deleteTagRow(tagNo);
        } catch (SQLTransientException SQLte) {
            return deleteTagRow(tagNo);
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    /**
     * Returns all the rows from the database's Tag table in the form of a
     * HashSet containing Tag entities.
     * 
     * @return a HashSet with all the rows in the Tag table from the easyTeach
     *         database. The rows are converted into Tag entities.
     * @see Tag
     */
    public static HashSet<Tag> getTagRows() {
        String sql = "{call selectTagRows()}";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();) {

            HashSet<Tag> hashSet = new HashSet<Tag>();

            while (rs.next()) {
                Tag tagEntity = new Tag();
                tagEntity.setTagNo(rs.getString("tagNo"));
                tagEntity.setTag(rs.getString("tag"));

                hashSet.add(tagEntity);
            }
            
            return hashSet;
            
        } catch (SQLTransientConnectionException SQLtce) {
            return getTagRows();
        } catch (SQLTransientException SQLte) {
            return getTagRows();
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    /**
     * Returns a row from the database's Tag table with a specific tagNo.
     * 
     * @param tagNo
     *            is the primary key of the Tag table.
     * @return An instance of Tag
     * @see Tag
     */
    public static Tag getTagRowWithTagNo(String tagNo) {
        String sql = "{call selectTagRowWithTagNo(?)}";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, tagNo);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                Tag tagEntity = new Tag();
                tagEntity.setTagNo(rs.getString("tagNo"));
                tagEntity.setTag(rs.getString("tag"));

                return tagEntity;
            }
            
            return null;
            
        } catch (SQLTransientConnectionException SQLtce) {
            return getTagRowWithTagNo(tagNo);
        } catch (SQLTransientException SQLte) {
            return getTagRowWithTagNo(tagNo);
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

}
