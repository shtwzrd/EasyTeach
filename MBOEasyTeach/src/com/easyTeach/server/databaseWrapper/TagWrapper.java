package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

import com.easyTeach.common.entity.Tag;
import com.easyTeach.server.databaseConnector.ConnectionManager;

/**
 * The TagWrapper is the class responsible for handling all the prepared 
 * CRUD SQL statements for manipulating with the Tag table residing in 
 * the MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see Tag
 * @date 30. November, 2013
 */

public class TagWrapper {

    private static Connection conn = 
            ConnectionManager.getInstance().getConnection();
    
    /**
     * Inserts a new Tag row into the Tag table within the easyTeach 
     * database. The prepared statement needs the Tag's tag.
     * 
     * @param tagEntity is an instance of the class Tag
     * @return true if the tagEntity is successfully inserted into the
     * easyTeach database, otherwise false.
     * @see Tag
     */
    public static boolean insertIntoTag(Tag tagEntity) {
        String sql = "{call insertIntoTag(?)}";

        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, tagEntity.getTag());
            
            int affected = stmt.executeUpdate();
            if (affected == 1) {
                return true;
            } else {
                return false;
            }
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    
    /**
     * Updates an existing Tag row in the Tag table within the 
     * easyTeach database. The prepared statement needs the Tag's 
     * tagNo and tag. 
     * 
     * @param tagEntity is an instance of the class Tag
     * @return true if the Tag row is successfully updated in the
     * easyTeach database, otherwise false.
     * @see Tag
     */
    public static boolean updateTagRow(Tag tagEntity) {
        String sql = "{call updateTagRow(?,?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, tagEntity.getTagNo());
            stmt.setString(2, tagEntity.getTag());
            
            int affected = stmt.executeUpdate();
            if (affected == 1) {
                return true;
            } else {
                return false;
            }
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        } 
    }
    
    /**
     * Deletes an existing Tag row in the Tag table within the 
     * easyTeach database. The prepared statement needs the Tag's 
     * tagNo.
     * 
     * @param tagNo is the primary key of the Tag table.
     * @return true if the Tag row is successfully deleted in the
     * easyTeach database, otherwise false.
     * @see Tag
     */
    public static boolean deleteTagRow(String tagNo) {
        String sql = "{call deleteTagRow(?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, tagNo);
            
            int affected = stmt.executeUpdate();
            if (affected == 1) {
                return true;
            } else {
                return false;
            }
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    
    /**
     * Returns all the rows from the database's Tag table in the form of a 
     * TreeSet containing Tag entities.   
     * 
     * @return a TreeSet with all the rows in the Tag table from the
     * easyTeach database. The rows are converted into Tag entities.
     * @see Tag
     */
    public static TreeSet<Tag> getTagRows() {
        String sql = "{call selectTagRows()}";

        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ){

            TreeSet<Tag> treeSet = new TreeSet<Tag>();
            
            if (rs.next()) {
                Tag tagEntity = new Tag();
                tagEntity.setTagNo(rs.getString("tagNo"));
                tagEntity.setTag(rs.getString("tag"));
                
                treeSet.add(tagEntity);
            }
            return treeSet;
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
    
}
