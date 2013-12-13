package com.easyTeach.server.domainLogic;

import java.util.HashSet;

import com.easyTeach.common.entity.ClassCourseRelation;
import com.easyTeach.common.entity.ClassUserRelation;
import com.easyTeach.common.entity.Resource;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.server.databaseWrapper.ClassCourseRelationWrapper;

/**
 * Class used for manipulating with {@link CourseCourseRelation} entities. It
 * contains, among other things, the logic for calling the CRUD procedures from
 * the {@link ClassCourseRelationWrapper}. The constructor is private as there
 * should never be created an instance of the ClassCourseRelationRules class
 * itself.
 * 
 * @author Oliver Nielsen
 * @version 0.1
 * @date 12. December, 2013
 */

public class ClassCourseRelationRules {

	private ClassCourseRelationRules() {
		// Empty constructor
	}

	/**
	 * Updates {@link ClassCourseRelation} rows in the database by a specific
	 * class.
	 * 
	 * @param relations
	 *            The HashSet of ClassCourseRelations that should be
	 *            synchronised with the database.
	 * @return A Response object with a success status if the class user
	 *         relations was updated.
	 */
	public static Response updateRelationByClass(ResourceSet relations) {
		try {
			ClassCourseRelation ccrEntity = (ClassCourseRelation) relations
					.toArray()[0];
			String classNo = ccrEntity.getClassNo();

			HashSet<ClassCourseRelation> courseWithClassNo = ClassCourseRelationWrapper
					.getClassCourseRelationRowsWithClassNo(classNo);

			sync(courseWithClassNo, relations);
		} catch (Exception e) {
			return new Response(ResponseStatus.FAILURE);
		}

		return new Response(ResponseStatus.SUCCESS);
	}

	/**
	 * Updates {@link ClassCourseRelation} rows in the database by a specific
	 * course.
	 * 
	 * @param relations
	 *            The HashSet of ClassCourseRelations that should be
	 *            synchronised with the database.
	 * @return A Response object with a success status if the class course
	 *         relations was updated.
	 */
	public static Response updateRelationByCourse(ResourceSet relations) {
		try {
			ClassCourseRelation ccrEntity = (ClassCourseRelation) relations
					.toArray()[0];
			String courseNo = ccrEntity.getCourseNo();

			HashSet<ClassCourseRelation> classWithCourseNo = ClassCourseRelationWrapper
					.getClassCourseRelationRowsWithCourseNo(courseNo);

			sync(classWithCourseNo, relations);
		} catch (Exception e) {
			return new Response(ResponseStatus.FAILURE);
		}

		return new Response(ResponseStatus.SUCCESS);
	}

	/**
	 * @param relations
	 *            HashSet of ClassCourseRelations that should be added with the
	 *            database.
	 * @return a Response object with a success status if all
	 *         {@link ClassCourseRelations} were added. Otherwise false.
	 */
	public static Response addRelations(ResourceSet relations) {
		boolean checker = true;

		for (Resource ccrEntity : relations) {
			if (!ClassCourseRelationWrapper
					.insertIntoClassCourseRelation((ClassCourseRelation) ccrEntity)) {
				checker = false;
			}
		}
		if (checker) {
			return new Response(ResponseStatus.SUCCESS);
		}

		return new Response(ResponseStatus.FAILURE);
	}

	/**
	 * Synchronizes the {@link ClassUserRelation} database-rows for a specific
	 * user or class with a given set of ClassUserRelations.
	 * 
	 * @author Morten Faarkrog. Changes to work with ClassCourseRelations is
	 *         made by Oliver Nielsen.
	 * @param ccrSet
	 *            HashSet of all the ClassUserRelations that from the update
	 *            methods.
	 * @param relations
	 *            The ResourceSet passed to the Domain Logic by the Presenter.
	 */
	private static void sync(HashSet<ClassCourseRelation> ccrSet,
			ResourceSet relations) {
		// Insert new relations to the DB
		for (Resource resource : relations) {
			if (!ccrSet.contains(resource)) {
				ClassCourseRelation ccrEntity = (ClassCourseRelation) resource;
				ClassCourseRelationWrapper
						.insertIntoClassCourseRelation(ccrEntity);
			}
		}
		// Remove deleted relations from the DB
		for (ClassCourseRelation ccrEntity : ccrSet) {
			if (!relations.contains(ccrEntity)) {
				ClassCourseRelationWrapper.deleteClassCourseRelationRow(
						ccrEntity.getClassNo(), ccrEntity.getCourseNo());
			}
		}
	}

}
