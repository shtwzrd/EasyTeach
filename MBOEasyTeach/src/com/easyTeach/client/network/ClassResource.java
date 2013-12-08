package com.easyTeach.client.network;

import com.easyTeach.common.network.Resource;

/**
 * <p>
 * The ClassResource class is for receiving or sending relevant information from
 * or to our server about a class.
 * </p>
 * 
 * @author Oliver Nielsen
 * @version 0.2
 * @date 8. December, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters).
 */

public class ClassResource implements Resource {

	private static final long serialVersionUID = 1808246972558712395L;

	private int year;

	private String className;

	/**
	 * <p>
	 * Constructor for receiving information for a class from the server side of
	 * the application.
	 * </p>
	 * 
	 * <p>
	 * The constructor is also used for receiving information for a class from
	 * the server side of the application.
	 * </p>
	 * 
	 * @param className
	 *            The name of the class
	 * @param year
	 *            The year of which the class started.
	 * 
	 * @see Class
	 */
	public ClassResource(String className, int year) {
		this.className = className;
		this.year = year;
	}

	public int getYear() {
		return year;
	}

	public String getClassName() {
		return className;
	}

}
