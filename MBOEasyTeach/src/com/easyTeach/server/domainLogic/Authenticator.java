package com.easyTeach.server.domainLogic;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.easyTeach.common.entity.User;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.common.network.Session;
import com.easyTeach.common.network.resource.RoleResource;
import com.easyTeach.common.network.resource.RoleResource.Role;
import com.easyTeach.server.databaseWrapper.UserWrapper;


/** 
 * <p>
 * The Authenticate class is a class that authenticates and Authorizes
 *  different operations sent to the server via Requests.
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
	 * Authenticates and Authorizes a user requesting interaction with the
	 * Server.
	 * 
	 * @param session Session token containing the credentials of a user.
	 * @return Returns a Response object with a Success or Failure status,
	 * and the level of authorization given to the user provided
	 * authentication was successful.
	 */
	
	public static Response authenticateUser(Session session) {
		StrongPasswordEncryptor pEncrypt = new StrongPasswordEncryptor();

		String username = session.getPassword();
		String password = session.getPassword();

		User user = UserWrapper.getUserRowWithEmail(username);
		
		
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
