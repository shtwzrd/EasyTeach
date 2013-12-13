package com.easyTeach.server.domainLogic;

import com.easyTeach.common.entity.Course;
import com.easyTeach.common.entity.Exercise;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.entity.Tag;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.server.databaseWrapper.ExerciseWrapper;

/**
 * Class used for manipulating with {@link Exercise} entities. It contains,
 * among other things, the logic for calling the CRUD procedures from the
 * {@link ExerciseWrapper}. The constructor is private as there should never be
 * created an instance of the ExerciseRules class itself.
 * 
 * @author Oliver Nielsen
 * @version 1.0
 * @date 12. December, 2013
 */

public class ExerciseRules {

	private ExerciseRules() {
		// Empty constructor
	}

	/**
	 * @param exerciseEntity
	 *            Exercise entity containing a exerciseNo for the exercise that
	 *            should be returned.
	 * @return A Response object with a success status and the exercise
	 *         associated with the exerciseNo.
	 */
	public static Response getExercise(Exercise exerciseEntity) {
		Exercise getExercise = ExerciseWrapper
				.getExerciseRowWithExerciseNo(exerciseEntity.getExerciseNo());

		if (getExercise != null) {
			return new Response(ResponseStatus.SUCCESS, getExercise);
		}
		return new Response(ResponseStatus.FAILURE);
	}

	/**
	 * @param exerciseEntity
	 *            Exercise entity containing a courseNo.
	 * @return A Response object with a success status and a {@link ResourceSet}
	 *         of all the all the exercises associated with the courseNo.
	 */
	public static Response getExercisesWithCourseNo(Course courseEntity) {
		ResourceSet exercises = new ResourceSet();

		for (Exercise exercise : ExerciseWrapper
				.getExerciseRowsWithCourseNo(courseEntity.getCourseNo())) {
			exercises.add(exercise);
		}

		return new Response(ResponseStatus.SUCCESS, exercises);
	}

	/**
	 * @param tagEntity
	 *            Tag entity containing a tag.
	 * @return A Response object with a success status and a {@link ResourceSet}
	 *         of all the all the exercises associated with the tag.
	 */
	public static Response getExercisesWithTag(Tag tagEntity) {
		ResourceSet exercises = new ResourceSet();

		for (Exercise exercise : ExerciseWrapper
				.getExerciseRowsWithTag(tagEntity.getTag())) {
			exercises.add(exercise);
		}

		return new Response(ResponseStatus.SUCCESS, exercises);
	}

	/**
	 * @return A Response object with a success status and a {@link ResourceSet}
	 *         of all the all the exercises.
	 */
	public static Response getExercises() {
		ResourceSet exercises = new ResourceSet();

		for (Exercise exercise : ExerciseWrapper.getExerciseRows()) {
			exercises.add(exercise);
		}

		return new Response(ResponseStatus.SUCCESS, exercises);
	}

	/**
	 * @param exerciseEntity
	 *            Exercise entity.
	 * @return A Response object with a success status if the Exercise was
	 *         updated. If not false.
	 */
	public static Response updateExercise(Exercise exerciseEntity) {
		if (ExerciseWrapper.updateExerciseRow(exerciseEntity)) {
			return new Response(ResponseStatus.SUCCESS);
		}
		return new Response(ResponseStatus.FAILURE);
	}

	/**
	 * @param exerciseEntity
	 *            Exercise entity containing exerciseNo.
	 * @return A Response object with a success status if the Exercise was
	 *         deleted. If not false.
	 */
	public static Response deleteExercise(Exercise exerciseEntity) {
		if (ExerciseWrapper.deleteExerciseRow(exerciseEntity.getExerciseNo())) {
			return new Response(ResponseStatus.SUCCESS);
		}
		return new Response(ResponseStatus.FAILURE);
	}

	/**
	 * @param exerciseEntity
	 *            Exercise entity.
	 * @return A Response object with a success status if the Exercise was
	 *         added. If not false.
	 */
	public static Response addExercise(Exercise exerciseEntity) {
		if (ExerciseWrapper.insertIntoExercise(exerciseEntity)) {
			return new Response(ResponseStatus.SUCCESS);
		}
		return new Response(ResponseStatus.FAILURE);
	}

}
