package com.easyTeach.common.entity;

import java.sql.Date;

/**
 * <p>
 * The User class represents a row from the User table in the easyTeach
 * database. A User could be an administrator, a teacher or a student. The class
 * contains getters and setters for the seven attributes derived from the User
 * table, namely; userNo, email, userType, firstName, lastName, password and
 * dateAdded.
 * </p>
 * 
 * <p>
 * In the database the primary key of the User table is the userNo.
 * </p>
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 26. November, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters/setters).
 */

public class User implements Resource {

	private static final long serialVersionUID = -3219392603446174749L;

	private String userNo; 
	private String email;
	private String userType;
	private String firstName;
	private String lastName;
	private String password;
	private java.sql.Date dateAdded;

	public User() {

	}

	public User(String userNo, String email, String userType, String firstName,
			String lastName, String password, Date dateAdded) {
		this.userNo = userNo;
		this.email = email;
		this.userType = userType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.dateAdded = dateAdded;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public java.sql.Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(java.sql.Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	@Override
	public String getName() {
		return "User";
	}

}
