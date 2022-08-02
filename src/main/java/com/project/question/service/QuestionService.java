package com.project.question.service;

/**
 * Service to handle the services of controller
 * @author apurba
 *
 */

public interface QuestionService {

	String getQuestion(String questinStr);
	
	void getQuestionFromCache(String strSearch, Integer expectedValue);
	
	void getAnswerFromCache(String answer);
}
