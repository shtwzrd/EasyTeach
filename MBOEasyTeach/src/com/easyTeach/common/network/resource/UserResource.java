package com.easyTeach.common.network.resource;

import java.util.HashSet;

import com.easyTeach.common.entity.User;

/**
 * <p>
 * The UserResource class is for receiving or sending relevant information from
 * or to our server about an user.
 * </p>
 * 
 * @author Oliver Nielsen
 * @version 1.0
 * @date 9. December, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters).
 */

public class UserResource implements Resource {

	private static final long serialVersionUID = -2136388195433328989L;

	private String email;
	private String userType;
	private String firstName;
	private String lastName;
	private String password;

	private java.sql.Timestamp dateAdded;

	private HashSet<ClassResource> classes;

	/**
	 * <p>
	 * This constructor for receiving information for a user from the server
	 * side of the application.
	 * </p>
	 * 
	 * @param email
	 *            The email for the specific user.
	 * @param firstName
	 *            The first name of the specific user.
	 * @param lastName
	 *            The last name of the specific user.
	 * @param dateAdded
	 *            The date the user was added to the database.
	 * @param classes
	 *            The classes the specific user attends and have attended.
	 * 
	 * @see User
	 */
	public UserResource(String email, String firstName, String lastName,
			java.sql.Timestamp dateAdded, HashSet<ClassResource> classes) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateAdded = dateAdded;
		this.classes = classes;
	}

	/**
	 * <p>
	 * This constructor for sending information for a user to the server side of
	 * the application.
	 * </p>
	 * 
	 * @param email
	 *            The email for the specific user.
	 * @param userType
	 *            The type of user of the specific user.
	 * @param firstName
	 *            The first name of the specific user.
	 * @param lastName
	 *            The last name of the specific user.
	 * @param password
	 *            The password for the specific user.
	 * @param classes
	 *            The classes the specific user attends and have attended.
	 * 
	 * @see User
	 */
	public UserResource(String email, String userType, String firstName,
			String lastName, String password, HashSet<ClassResource> classes) {
		this.email = email;
		this.userType = userType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.classes = classes;
	}

	public String getEmail() {
		return email;
	}

	public String getUserType() {
		return userType;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public java.sql.Timestamp getDateAdded() {
		return dateAdded;
	}

	public HashSet<ClassResource> getClasses() {
		return classes;
	}

	@Override
	public String getName() {
		return "UserResource";
	}

}
