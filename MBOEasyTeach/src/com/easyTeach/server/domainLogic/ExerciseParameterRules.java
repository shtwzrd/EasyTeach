package com.easyTeach.server.domainLogic;

import com.easyTeach.common.entity.ExerciseParameter;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.server.databaseWrapper.ExerciseParameterWrapper;

/**
 * Class used for manipulating with {@link ExerciseParameter} entities. It
 * contains, among other things, the logic for calling the CRUD procedures from
 * the {@link ExerciseParameterWrapper}. The constructor is private as there
 * should never be created an instance of the ExerciseParameterRules class
 * itself.
 * 
 * @author Oliver Nielsen
 * @version 0.1
 * @date 12. December, 2013
 */

public class ExerciseParameterRules {

	private ExerciseParameterRules() {
		// Empty constructor
	}

	/**
	 * @param epEntity
	 *            ExerciseParameter entity containing the exerciseParameterNo
	 *            for the exercise parameter that should be returned.
	 * @return A Response object with a success status and the exercise
	 *         parameter associated with the exerciseParameterNo.
	 */
	public static Response getExerciseParameter(ExerciseParameter epEntity) {
		ExerciseParameter getEP = ExerciseParameterWrapper
				.getExerciseParameterRowWithExerciseParameterNo(epEntity
						.getExerciseParameterNo());

		if (getEP != null) {
			return new Response(ResponseStatus.SUCCESS, getEP);
		}
		return new Response(ResponseStatus.FAILURE);
	}

	/**
	 * @return A Response object with a success status and a {@link ResourceSet}
	 *         of all the all the exercise parameters.
	 */
	public static Response getExerciseParameters() {
		ResourceSet exerciseParameters = new ResourceSet();

		for (ExerciseParameter ep : ExerciseParameterWrapper
				.getExerciseParameterRows()) {
			exerciseParameters.add(ep);
		}

		return new Response(ResponseStatus.SUCCESS, exerciseParameters);
	}

	/**
	 * @param epEntity
	 *            ExerciseParameter entity.
	 * @return A Response object with a success status if the ExerciseParameter
	 *         was updated. If not false.
	 */
	public static Response updateExerciseParameter(ExerciseParameter epEntity) {
		if (ExerciseParameterWrapper.updateExerciseParameterRow(epEntity)) {
			return new Response(ResponseStatus.SUCCESS);
		}
		return new Response(ResponseStatus.FAILURE);
	}

	/**
	 * @param epEntity
	 *            ExerciseParameter entity containing the exerciseParameterNo.
	 * @return A Response object with a success status if the ExerciseParameter
	 *         was deleted. If not false.
	 */
	public static Response deleteExerciseParameter(ExerciseParameter epEntity) {
		if (ExerciseParameterWrapper.deleteExerciseParameterRow(epEntity
				.getExerciseParameterNo())) {
			return new Response(ResponseStatus.SUCCESS);
		}
		return new Response(ResponseStatus.FAILURE);
	}

	/**
	 * @param epEntity
	 *            ExerciseParameter entity.
	 * @return A Response object with a success status if the ExerciseParameter
	 *         was added. If not false.
	 */
	public static Response addExerciseParameter(ExerciseParameter epEntity) {
		if (ExerciseParameterWrapper.insertIntoExerciseParameter(epEntity)) {
			return new Response(ResponseStatus.SUCCESS);
		}
		return new Response(ResponseStatus.FAILURE);
	}

}
