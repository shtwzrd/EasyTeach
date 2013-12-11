package com.easyTeach.server.domainLogic;

import java.util.HashSet;

import com.easyTeach.common.entity.ExerciseQuestionRelation;
import com.easyTeach.common.entity.Resource;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.server.databaseWrapper.ExerciseQuestionRelationWrapper;

public class ExerciseQuestionRelationRules {

	/**
	 * Class used for manipulating with {@link ExerciseQuestionRelation}
	 * entities. It contains, among other things, the logic for calling the CRUD
	 * procedures from the {@link ExerciseQuestionRelationWrapper}. The
	 * constructor is private as there should never be created an instance of
	 * the ExerciseQuestionRelationRules class itself.
	 * 
	 * @author Tonni Hyldgaard
	 * @version 1.0
	 * @date 11. December, 2013
	 * @see ExerciseQuestionRelation
	 * @see ExerciseQuestionRelationWrapper
	 */

	private ExerciseQuestionRelationRules() {
	}

	/**
	 * Adds a {@link ExerciseQuestionRelation} to the database.
	 * 
	 * @param eqr
	 *            the ExerciseQuestionRelation that should be added.
	 * @return a Response object with a success status if the
	 *         ExerciseQuestionRelation was added. Otherwise false.
	 */
	public static Response addExerciseQuestionRelation(
			ExerciseQuestionRelation eqr) {
		if (ExerciseQuestionRelationWrapper
				.insertIntoExerciseQuestionRelation(eqr)) {
			return new Response(ResponseStatus.SUCCESS);
		}

		return new Response(ResponseStatus.FAILURE,
				"Failed to insert ExerciseQuestionRelation");
	}

	/**
	 * Deletes a {@link ExerciseQuestionRelation} row in the database.
	 * 
	 * @param eqr
	 *            the ExerciseQuestionRelation that should be deleted.
	 * @return a Response object with a success status if the
	 *         ExerciseQuestionRelation was deleted. Otherwise false.
	 */
	public static Response deleteExerciseQuestionRelation(ExerciseQuestionRelation eqr) {
		if (ExerciseQuestionRelationWrapper.deleteExerciseQuestionRelationRow(
				eqr.getExerciseNo(), eqr.getQuestionNo())) {
			return new Response(ResponseStatus.SUCCESS);
		}

		return new Response(ResponseStatus.FAILURE,
				"Failed to delete ExerciseQuestionRelation");
	}
	
	/**
	 * Synchronizes the {@link ExerciseQuestionRelation} database-rows for a specific
	 * exercise or question with a given set of ExerciseQuestionRelation.
	 * 
	 * @param databaseSet
	 *            HashSet of all the ExerciseQuestionRelation with a specific
	 *            exerciseNo or questionNo
	 * @param relations
	 *            The ResourceSet passed to the Domain Logic by the Presenter
	 */
	private static void sync(HashSet<ExerciseQuestionRelation> databaseSet,
			ResourceSet relations) {
		// Insert new relations to the DB
		for (Resource resource : relations) {
			if (!databaseSet.contains(resource)) {
				ExerciseQuestionRelation eqr = (ExerciseQuestionRelation) resource;
				ExerciseQuestionRelationWrapper.insertIntoExerciseQuestionRelation(eqr);
			}
		}

		// Remove deleted relations from the DB
		for (ExerciseQuestionRelation eqr : databaseSet) {
			if (!relations.contains(eqr)) {
				ExerciseQuestionRelationWrapper.deleteExerciseQuestionRelationRow(
						eqr.getExerciseNo(), eqr.getQuestionNo());
			}
		}
	}

}
