package com.easyTeach.test;

import java.util.TreeSet;

import com.easyTeach.common.entity.User;
import com.easyTeach.server.databaseWrapper.UserWrapper;

	/**
	 * <p>
	 * See all the wrappers and entity classes for reference to the testing information.
	 * </p>
	 * 
	 * @author Tonni Hyldgaard
	 * @version 1.0
	 * @data 3. December, 2013.
	 */

public class DatabaseConnectJavaTestEnitityOne {
	
	public static void main(String[] args) {
		new DatabaseConnectJavaTestEnitityOne();
	}
	
	public DatabaseConnectJavaTestEnitityOne() {
		testUserWrapper();
		
	}
	
	public void testUserWrapper()  {
	
		// Create an instance of a userEntity and insert it into the database
		// through the UserWrapper
		User user1 = new User();
		user1.setEmail("tonni@test.com");
		user1.setUserType("Student");
		user1.setFirstName("Tonni");
		user1.setLastName("Kristensen");
		user1.setPassword("randompassword123#");
		
		User user2 = new User();
		user2.setEmail("morten@test.com");
		user2.setUserType("Admin");
		user2.setFirstName("Morten");
		user2.setLastName("Faarkrog");
		user2.setPassword("randompassword456#");
		
		UserWrapper userWrap = new UserWrapper();
		userWrap.insertIntoUser(user1);
		userWrap.insertIntoUser(user2);
		
		// This is supposed to retrieve the data for a user by using
		// a select statement, but it does not work yet. 
		TreeSet<User> treeSetUser = userWrap.getUserRows();
		for (User u : treeSetUser) {
			System.out.println(u.getUserNo());
			System.out.println(u.getEmail());
			System.out.println(u.getUserType());
			System.out.println(u.getFirstName());
			System.out.println(u.getLastName());
			System.out.println(u.getPassword());
		}
		// Update an userEntity
		//userWrap.updateUserRow(user1);
		
		
	}

}
