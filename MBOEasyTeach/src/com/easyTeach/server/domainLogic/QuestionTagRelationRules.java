package com.easyTeach.server.domainLogic;

import java.util.HashSet;

import com.easyTeach.common.entity.QuestionTagRelation;
import com.easyTeach.common.entity.Resource;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.server.databaseWrapper.QuestionTagRelationWrapper;

public class QuestionTagRelationRules {

	/**
	 * Class used for manipulating with {@link QuestionTagRelation} entities. It
	 * contains, among other things, the logic for calling the CRUD procedures
	 * from the {@link QuestionTagRelationWrapper}. The constructor is private
	 * as there should never be created an instance of the
	 * QuestionTagRelationRules class itself.
	 * 
	 * @author Tonni Hyldgaard
	 * @version 1.0
	 * @date 11. December, 2013
	 * @see QuestionTagRelation
	 * @see QuestionTagRelationWrapper
	 */

	private QuestionTagRelationRules() {
	}

	/**
	 * Adds a {@link QuestionTagRelation} to the database.
	 * 
	 * @param qtr
	 *            the QuestionTagRelation that should be added.
	 * @return a Response object with a success status if the
	 *         QuestionTagRelation was added. Otherwise false.
	 */
	public static Response addQuestionTagRelation(QuestionTagRelation qtr) {
		if (QuestionTagRelationWrapper.insertIntoQuestionTagRelation(qtr)) {
			return new Response(ResponseStatus.SUCCESS);
		}

		return new Response(ResponseStatus.FAILURE,
				"Failed to insert QuestionTagRelation");
	}

	/**
	 * Deletes a {@link QuestionTagRelation} row in the database.
	 * 
	 * @param qtr
	 *            the QuestionTagRelation that should be deleted.
	 * @return a Response object with a success status if the
	 *         QuestionTagRelation was deleted. Otherwise false.
	 */
	public static Response deleteQuestionTagRelation(QuestionTagRelation qtr) {
		if (QuestionTagRelationWrapper.deleteQuestionTagRelationRow(
				qtr.getQuestionNo(), qtr.getTagNo())) {
			return new Response(ResponseStatus.SUCCESS);
		}

		return new Response(ResponseStatus.FAILURE,
				"Failed to delete QuestionTagRelation");
	}

    /**
     * Updates {@link QuestionTagRelation} rows in the database by a specific
     * question.
     * 
     * @param relations
     *            HashSet of QuestionTagRelation that should be synchronized with
     *            the database.
     * @return a Response object with a success status if the QuestionTagRelation
     *         was updated.
     */
    public static Response updateQuestionTagRelationByQuestionNo(ResourceSet relations) {
        // Finds out what userNo we are working with
        QuestionTagRelation entity = (QuestionTagRelation) relations.toArray()[0];
        String questionNo = entity.getQuestionNo();

        // Gets all the ClassUserRelations with the specific classNo
        HashSet<QuestionTagRelation> databaseSet = QuestionTagRelationWrapper
                .getQuestionTagRelationRowsWithQuestionNo(questionNo);

        sync(databaseSet, relations);

        return new Response(ResponseStatus.SUCCESS);
    }
	
	/**
	 * Synchronizes the {@link QuestionTagRelation} database-rows for a specific
	 * question or tag with a given set of QuestionTagRelation.
	 * 
	 * @param databaseSet
	 *            HashSet of all the QuestionTagRelation with a specific
	 *            questionNo or TagNo
	 * @param relations
	 *            The ResourceSet passed to the Domain Logic by the Presenter
	 */
	private static void sync(HashSet<QuestionTagRelation> databaseSet,
			ResourceSet relations) {
		// Insert new relations to the DB
		for (Resource resource : relations) {
			if (!databaseSet.contains(resource)) {
				QuestionTagRelation qtr = (QuestionTagRelation) resource;
				QuestionTagRelationWrapper.insertIntoQuestionTagRelation(qtr);
			}
		}

		// Remove deleted relations from the DB
		for (QuestionTagRelation qtr : databaseSet) {
			if (!relations.contains(qtr)) {
				QuestionTagRelationWrapper.deleteQuestionTagRelationRow(
						qtr.getQuestionNo(), qtr.getTagNo());
			}
		}
	}

}
