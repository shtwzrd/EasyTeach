package com.easyTeach.server.domainLogic;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.easyTeach.common.entity.User;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.common.network.resource.RoleResource;
import com.easyTeach.common.network.resource.RoleResource.Role;
import com.easyTeach.server.databaseWrapper.UserWrapper;


/** 
 * <p>
 * The Authenticate class is a class that authenticate different operations
 * to be sure user interactions is allowed for them.
 * </p>
 * 
 * @author Oliver Nielsen
 * @version 0.1
 * @date 06. December, 2013
 */


public class Authenticator {
	
	public Authenticator() {
		// Empty constructor
	}
	
	public static void main(String[] args) {
		StrongPasswordEncryptor pEncrypt = new StrongPasswordEncryptor();
		User user = new User();
		user.setPassword(pEncrypt.encryptPassword("test"));
		user.setEmail("tester@test.com");
		user.setFirstName("TestFirst");
		user.setLastName("TestLast");
		user.setUserType("Student");
		user.setDateAdded(new java.sql.Date(2014-12-01));
//		new UserWrapper().insertIntoUser(user);
		System.out.println(pEncrypt.checkPassword("test", user.getPassword()));
	}
	
	
	/**
	 * Authenticates the login attempt.
	 * 
	 * @param username Which is gotten from a JTextField when logging in.
	 * @param password Which is gotten from a JTextField when logging in.
	 * @return Returns a boolean value. true if the information corresponds,
	 * false if not.
	 */
	
	public static Response authenticateUser(String username, String password) {
		StrongPasswordEncryptor pEncrypt = new StrongPasswordEncryptor();
		
		User user = new UserWrapper().getUserRowWithEmail(username);
		
		
		if (user != null && pEncrypt.checkPassword(password, user.getPassword())) {
			RoleResource role = new RoleResource(Role.STUDENT);
			switch(user.getUserType()) {
				case "Student" : role = new RoleResource(Role.STUDENT);
				break;
				case "Teacher" : role = new RoleResource(Role.TEACHER);
				break;
				case "Admin"   : role = new RoleResource(Role.ADMIN);
				break;
			}
			return new Response(ResponseStatus.SUCCESS, role); 
		}
		return new Response(ResponseStatus.FAILURE);
	}

}
