package com.easyTeach.server.domainLogic;

import com.easyTeach.common.entity.Exercise;
import com.easyTeach.common.entity.Question;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.entity.Tag;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.server.databaseWrapper.ExerciseWrapper;
import com.easyTeach.server.databaseWrapper.QuestionWrapper;
import com.easyTeach.server.databaseWrapper.TagWrapper;

public class TagRules {

	/**
	 * Class used for manipulating with {@link Tag} entities. It contains, among
	 * other things, the logic for calling the CRUD procedures from the
	 * {@link TagWrapper}. The constructor is private as there should never be
	 * created an instance of the TagRules class itself.
	 * 
	 * @author Tonni Hyldgaard
	 * @version 1.0
	 * @date 11. December, 2013
	 * @see Tag
	 * @see TagWrapper
	 */

	private TagRules() {
	}
	
	/**
     * Adds a {@link Tag} to the database.
     * 
     * @param tag the tag that should be added.
     * @return a Response object with a success status if the Tag was added.
     *         Otherwise false.
     */
	public static Response addTag(Tag tag) {
		if (TagWrapper.insertIntoTag(tag)) {
            return new Response(ResponseStatus.SUCCESS);
        }
		
        return new Response(ResponseStatus.FAILURE, "Failed to insert Tag");
	}

	/**
	 * Method used for retrieving a single {@link Tag} entity with a given
	 * tagNo.
	 * 
	 * @param Tag
	 *            the tag entity containing the tagNo one is trying to retrieve
	 * @return if successful, a Response object with a success status and
	 *         specific tag from the the DB. Otherwise, a failure response.
	 * @see Tag
	 * @see TagWrapper
	 */
	public static Response getTag(Tag tag) {
		Tag newTag = TagWrapper.getTagRowWithTagNo(tag.getTagNo());

		if (newTag != null) {
			return new Response(ResponseStatus.SUCCESS, newTag);
		}

		return new Response(ResponseStatus.FAILURE);
	}
	
	/**
     * Retrieves and returns a list with all the {@link Tag}s from the
     * DB.
     * 
     * @return if successful, a Response object with a success status and a list
     *         of all tags in the DB. Otherwise, a failure response.
     * @see Response
     * @see Tag
     * @see TagWrapper
     */
    public static Response getAllTags() {
        ResourceSet newTag = new ResourceSet();

        for (Tag tag : TagWrapper.getTagRows()) {
        	newTag.add(tag);
        }

        return new Response(ResponseStatus.SUCCESS, newTag);
    }
	

	/**
	 * @param Tag
	 *            entity containing the tagNo of the tag one is finding
	 *            exercises for.
	 * @return a Response object with a success status and all the classes
	 *         associated to a specific exercises.
	 * @see Tag
	 * @see TagWrapper
	 * @see Exericse
	 * @see ExerciseWrapper
	 */
	public static Response getExerciseRowsWithTagNo(Tag tag) {
		ResourceSet exercises = new ResourceSet();

		for (Exercise exerciseEntity : ExerciseWrapper
				.getExerciseRowsWithTagNo(tag.getTagNo())) {
			exercises.add(exerciseEntity);
		}

		return new Response(ResponseStatus.SUCCESS, exercises);
	}
	
	/**
	 * @param Tag
	 *            entity containing the tagNo of the tag one is finding
	 *            questions for.
	 * @return a Response object with a success status and all the classes
	 *         associated to a specific exercises.
	 * @see Tag
	 * @see TagWrapper
	 * @see Question
	 * @see QuestionWrapper
	 */
	public static Response getQuestionRowsWithTagNo(Tag tag) {
		ResourceSet questions = new ResourceSet();

		for (Question questionEntity : QuestionWrapper
				.getQuestionRowsWithTagNo(tag.getTagNo())) {
			questions.add(questionEntity);
		}

		return new Response(ResponseStatus.SUCCESS, questions);
	}
	
	/**
     * Updates a {@link Tag} row in the database.
     * 
     * @param tagEntity the class that should be updated.
     * @return a Response object with a success status if the Tag was updated.
     *         Otherwise false.
     */
    public static Response updateTag(Tag tag) {
        if (TagWrapper.updateTagRow(tag)) {
            return new Response(ResponseStatus.SUCCESS);
        }

        return new Response(ResponseStatus.FAILURE, "Failed to update tag");
    }
    
    /**
     * Deletes a {@link Tag} row in the database.
     * 
     * @param tag the tag that should be deleted.
     * @return a Response object with a success status if the Tag was deleted.
     *         Otherwise false.
     */
    public static Response deleteTag(Tag tag) {
        if (TagWrapper.deleteTagRow(tag.getTagNo())) {
            return new Response(ResponseStatus.SUCCESS);
        }

        return new Response(ResponseStatus.FAILURE, "Failed to delete tag");
    }
}
