package com.easyTeach.server.domainLogic;

import java.util.HashSet;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.easyTeach.common.entity.User;
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


public class Authenticate {
	
	public static void main(String args[]) {
		
	}
	
	
	public Authenticate() {
		// Empty constructor
	}
	
	
	/**
	 * Authenticates the login attempt.
	 * 
	 * @param username Which is gotten from a JTextField when logging in.
	 * @param password Which is gotten from a JTextField when logging in.
	 * @return Returns a boolean value. true if the information corresponds,
	 * false if not.
	 */
	
	public static boolean authenticateUser(String username, String password) {
		StrongPasswordEncryptor pEncrypt = new StrongPasswordEncryptor();
		
		User user = new UserWrapper().getUserRowWithEmail(username);
		
		if (pEncrypt.checkPassword(password, user.getPassword())) {
			return true;
		}
		return false;
	}

}
