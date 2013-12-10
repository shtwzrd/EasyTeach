package com.easyTeach.server.domainLogic;

import java.sql.SQLException;
import java.util.HashSet;

import com.easyTeach.common.entity.Exercise;
import com.easyTeach.common.entity.ExerciseParameter;
import com.easyTeach.common.entity.ExerciseQuestionRelation;
import com.easyTeach.common.entity.Question;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.common.network.resource.ExerciseResource;
import com.easyTeach.common.network.resource.QuestionResource;
import com.easyTeach.server.databaseWrapper.ExerciseParameterWrapper;
import com.easyTeach.server.databaseWrapper.ExerciseQuestionRelationWrapper;
import com.easyTeach.server.databaseWrapper.ExerciseWrapper;
import com.easyTeach.server.databaseWrapper.QuestionWrapper;

public class ExerciseRules {

	private ExerciseRules() {

	}

//	public Response addExercise(ExerciseResource exerciseResource) {
//		Exercise exerciseTemp = new Exercise();
//		ExerciseParameter exerciseParameterTemp = new ExerciseParameter();
//		
//		if (exerciseResource.getIsTest()) {
//			exerciseTemp.setCourseNo(exerciseResource.getCourseNo());
//			exerciseTemp.setAuthor(exerciseResource.getUserNo());
//			exerciseTemp.setExerciseName(exerciseResource.getExerciseName());
//			exerciseTemp.setDateAdded(exerciseResource.getDateAdded());
//			exerciseTemp.setPassword(exerciseResource.getPassword());
//			
//			exerciseParameterTemp.setIsTest(exerciseResource.getIsTest());
//			exerciseParameterTemp.setIsLocked(exerciseResource.getIsLocked());
//			exerciseParameterTemp.setAccessBegin(exerciseResource.getAccessBegin());
//			exerciseParameterTemp.setAccessEnd(exerciseResource.getAccessEnd());
//			exerciseParameterTemp.setTimeLimit(exerciseResource.getTimeLimit());
//		}
//		else {
//			exerciseTemp.setCourseNo(exerciseResource.getCourseNo());
//			exerciseTemp.setAuthor(exerciseResource.getUserNo());
//			exerciseTemp.setExerciseName(exerciseResource.getExerciseName());
//			exerciseTemp.setDateAdded(exerciseResource.getDateAdded());
//			exerciseTemp.setPassword(exerciseResource.getPassword());
//		}
//		
//		if (!ExerciseParameterWrapper.insertIntoExerciseParameter(exerciseParameterTemp)) {
//			return new Response(ResponseStatus.FAILURE);
//		}
//		String exerciseParameterNo = YOU NEED THE EXERCISEPARAMETERNO BUT DON'T HAVE IT UNTIL CREATED AND THEN YOU NEED THE EXERCISENO
//		if (condition) {
//			
//		}
//	}

	public Response getExercise(ExerciseResource exerciseResource) {
		Exercise exerciseEntity = ExerciseWrapper.getExerciseRowWithExerciseNo(exerciseResource.getExerciseNo());
		
		if (exerciseEntity != null) {
			ExerciseResource returnExerciseResource = null;
			
			ExerciseParameter ePEntity = ExerciseParameterWrapper.getExerciseParameterRowWithExerciseParameterNo(exerciseEntity.getExerciseParameterNo());
			
			HashSet<ExerciseQuestionRelation> exerciseQuestionSet = ExerciseQuestionRelationWrapper.getExerciseQuestionRelationRowsWithExerciseNo(exerciseEntity.getExerciseNo());
			
			HashSet<QuestionResource> questions = new HashSet<QuestionResource>();
			for (ExerciseQuestionRelation exQuRe : exerciseQuestionSet) {
				try {
					Question test = QuestionWrapper.getQuestionRowWithQuestionNo(exQuRe.getQuestionNo());
					
					QuestionResource quResource = new QuestionResource(test.getQuestionNo(), test.getQuestionType(), test.getQuestion(), test.getPoints());
					questions.add(quResource);
				} catch (SQLException e) {
					System.err.println(e);
				}
			}
			if (ePEntity != null && ePEntity.getIsTest()) {
				returnExerciseResource = new ExerciseResource(exerciseEntity.getExerciseNo(), exerciseEntity.getCourseNo(), exerciseEntity.getAuthor(), exerciseEntity.getExerciseName(), exerciseEntity.getPassword(), ePEntity.getIsLocked(), ePEntity.getAccessBegin(), ePEntity.getAccessEnd(), ePEntity.getTimeLimit(), questions);
			}
			else if (ePEntity != null && !ePEntity.getIsTest()) {
				returnExerciseResource = new ExerciseResource(exerciseEntity.getExerciseNo(), exerciseEntity.getCourseNo(), exerciseEntity.getAuthor(), exerciseEntity.getExerciseName(), questions);
			}
			return new Response(ResponseStatus.SUCCESS, returnExerciseResource);
		} else {
			return new Response(ResponseStatus.FAILURE);
		}
	}

//	public Response getAllExercises(ExerciseResource exerciseResource) {
//		HashSet<Exercise> exerciseEntitySet = ExerciseWrapper.getExerciseRows();
//		
//		HashSet<ExerciseResource> exerciseResourceSet = new HashSet<ExerciseResource>();
//		for (Exercise exercise : exerciseEntitySet) {
//			ExerciseResource returnExerciseResource = null;
//			
//			HashSet<ExerciseQuestionRelation> exerciseQuestionSet = ExerciseQuestionRelationWrapper.getExerciseQuestionRelationRowsWithExerciseNo(exercise.getExerciseNo());
//			
//			HashSet<QuestionResource> questions = new HashSet<QuestionResource>();
//			for (ExerciseQuestionRelation exQuRe : exerciseQuestionSet) {
//				try {
//					Question test = QuestionWrapper.getQuestionRowWithQuestionNo(exQuRe.getQuestionNo());
//					
//					QuestionResource quResource = new QuestionResource(test.getQuestionNo(), test.getQuestionType(), test.getQuestion(), test.getPoints());
//					questions.add(quResource);
//				} catch (SQLException e) {
//					System.err.println(e);
//				}
//			}
//			
//			
//			
//			ExerciseResource returnExerciseResource = new E
//	}

	public Response updateExercise(ExerciseResource exerciseResource) {
		return null;
	}

	public Response removeExercise(ExerciseResource exerciseResource) {
		if (ExerciseWrapper.deleteExerciseRow(exerciseResource.getExerciseNo())) {
			return new Response(ResponseStatus.SUCCESS);
		}
		return new Response(ResponseStatus.FAILURE);
	}

	public Response getExercisesByTag() {
		return null;
	}

}
