package com.easyTeach.server.domainLogic;

import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.common.network.resource.QuestionResource;
import com.easyTeach.server.databaseWrapper.QuestionWrapper;

public class QuestionRules {
	
	private QuestionRules() {
		
	}
	
	public Response addQuestion(QuestionResource questionResource) {
		return null;
	}
	
	public Response getQuestion(QuestionResource questionResource) {
		return null;
	}
	
	public Response getQuestions(QuestionResource questionResource) {
		return null;
	}
	
	public Response getQuestionsByTag(QuestionResource questionResource) {
		return null;
	}
	
	public Response updateQuestion(QuestionResource questionResource) {
		return null;
	}
	
	public Response removeQuestion(QuestionResource questionResource) {
		if (QuestionWrapper.deleteQuestionRow(questionResource.getQuestionNo())) {
			return new Response(ResponseStatus.SUCCESS);
		}
		return new Response(ResponseStatus.FAILURE);
	}
	
	public Response addTag(QuestionResource questionResource) {
		return null;
	}
	
	public Response removeTag(QuestionResource questionResource) {
		return null;
	}

}
