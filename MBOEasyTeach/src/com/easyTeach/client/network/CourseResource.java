package com.easyTeach.client.network;

import java.util.HashSet;

import com.easyTeach.common.entity.Course;
import com.easyTeach.common.network.Resource;

/**
 * <p>
 * The CourseResource class is for receiving or sending relevant information
 * from or to our server about a course.
 * </p>
 * 
 * @author Oliver Nielsen
 * @version 0.2
 * @date 8. December, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters).
 */

public class CourseResource implements Resource {

	private static final long serialVersionUID = 4552928039993955515L;

	private String courseName;

	private HashSet<ClassResource> classes;

	/**
	 * <p>
	 * Constructor for receiving information for a course from the server side
	 * of the application.
	 * </p>
	 * 
	 * <p>
	 * The constructor is also used for receiving information for a course from
	 * the server side of the application.
	 * </p>
	 * 
	 * @param courseName
	 *            The name of the course.
	 * @param classes
	 *            The classes that can take the specific course.
	 * 
	 * @see Course
	 */
	public CourseResource(String courseName, HashSet<ClassResource> classes) {
		this.courseName = courseName;
		this.classes = classes;
	}

	public String getCourseName() {
		return courseName;
	}

	public HashSet<ClassResource> getClasses() {
		return classes;
	}
}
