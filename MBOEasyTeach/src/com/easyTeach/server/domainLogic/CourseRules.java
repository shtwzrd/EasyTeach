package com.easyTeach.server.domainLogic;

import com.easyTeach.common.entity.Course;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.entity.User;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.server.databaseWrapper.CourseWrapper;

/**
 * Class used for manipulating with {@link Course} entities. It contains, among
 * other things, the logic for calling the CRUD procedures from the
 * {@link CourseWrapper}. The constructor is private as there should never be
 * created an instance of the CourseRules class itself.
 * 
 * @author Oliver Nielsen, Morten Faarkrog
 * @version 0.1
 * @date 12. December, 2013
 */

public class CourseRules {

	private CourseRules() {

	}

	/**
	 * @param courseEntity
	 *            Course entity containing a courseNo for the course that should
	 *            be returned.
	 * @return A Response object with a success status and the course associated
	 *         with the courseNo.
	 */
	public static Response getCourse(Course courseEntity) {
		Course getCourse = CourseWrapper.getCourseRowWithCourseNo(courseEntity
				.getCourseNo());

		if (getCourse != null) {
			return new Response(ResponseStatus.SUCCESS, getCourse);
		}
		return new Response(ResponseStatus.FAILURE);
	}
	
	/**
	 * @param courseEntity
	 *            Course entity containing a courseNo for the course that should
	 *            be returned.
	 * @return A Response object with a success status and the course associated
	 *         with the courseNo.
	 */
	public static Response getCoursesWithUserNo(User userEntity) {
	    ResourceSet courses = new ResourceSet();
	    
	    for (Course course : CourseWrapper.getCoursesByUserNo(userEntity.getUserNo())) {
            courses.add(course);
        }
        return new Response(ResponseStatus.SUCCESS, courses);
	}

	/**
	 * @return A Response object with a success status and a {@link ResourceSet}
	 *         of all the all the courses.
	 */
	public static Response getCourses() {
		ResourceSet courses = new ResourceSet();

		for (Course course : CourseWrapper.getCourseRows()) {
			courses.add(course);
		}
		return new Response(ResponseStatus.SUCCESS, courses);
	}

	/**
	 * @param courseEntity
	 *            Course entity that contains new information for what that
	 *            should be updated for a specific courseNo.
	 * @return A Response object with a success status if the Course was
	 *         updated. If not false.
	 */
	public static Response updateCourse(Course courseEntity) {
		if (CourseWrapper.updateCourseRow(courseEntity)) {
			return new Response(ResponseStatus.SUCCESS);
		}
		return new Response(ResponseStatus.FAILURE);
	}

	/**
	 * @param courseEntity
	 *            Course entity that contains the courseNo that should be
	 *            delete.
	 * @return A Response object with a success status if the Course was
	 *         deleted. If not false.
	 */
	public static Response deleteCourse(Course courseEntity) {
		if (CourseWrapper.deleteCourseRow(courseEntity.getCourseNo())) {
			return new Response(ResponseStatus.SUCCESS);
		}
		return new Response(ResponseStatus.FAILURE);
	}

	/**
	 * @param courseEntity
	 *            Course entity that should be added.
	 * @return A Response object with a success status if the Course was added.
	 *         If not false.
	 */
	public static Response addCourse(Course courseEntity) {
		if (CourseWrapper.insertIntoCourse(courseEntity)) {
			return new Response(ResponseStatus.SUCCESS);
		}
		return new Response(ResponseStatus.FAILURE);
	}

}
