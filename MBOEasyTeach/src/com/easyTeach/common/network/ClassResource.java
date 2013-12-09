package com.easyTeach.common.network;

import java.util.HashSet;

import com.easyTeach.common.network.resource.Resource;

/**
 * <p>
 * The ClassResource class is for receiving or sending relevant information from
 * or to our server about a class.
 * </p>
 * 
 * @author Oliver Nielsen
 * @version 1.0
 * @date 9. December, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters).
 */

public class ClassResource implements Resource {

	private static final long serialVersionUID = 1808246972558712395L;

	private String classNo;

	private int year;

	private String className;

	private HashSet<UserResource> users;

	/**
	 * <p>
	 * This constructor for receiving information for a class from the server
	 * side of the application.
	 * </p>
	 * 
	 * @param className
	 *            The name of the class.
	 * @param year
	 *            The year of which the class started.
	 * @param users
	 *            A set of users.
	 * 
	 * @see Class
	 */
	public ClassResource(String className, int year, HashSet<UserResource> users) {
		this.className = className;
		this.year = year;
		this.users = users;
	}

	/**
	 * <p>
	 * This constructor is used for sending information for a class to the
	 * server side of the application.
	 * </p>
	 * 
	 * @param classNo
	 *            The class number.
	 * @param className
	 *            The name of the class.
	 * @param year
	 *            The year of which the class started.
	 * @param users
	 *            A set of users.
	 * 
	 * @see Class
	 */
	public ClassResource(String classNo, String className, int year,
			HashSet<UserResource> users) {
		this.classNo = classNo;
		this.className = className;
		this.year = year;
		this.users = users;
	}

	public String getClassNo() {
		return classNo;
	}

	public int getYear() {
		return year;
	}

	public String getClassName() {
		return className;
	}

	public HashSet<UserResource> getUsers() {
		return users;
	}

}
