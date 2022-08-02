package com.project.question.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.project.question.cache.QuestionCacheService;
import com.project.question.model.Question;
import com.project.question.service.QuestionService;
import com.project.question.utils.QuestionUtil;

@Service
public class QuestionServiceImpl implements QuestionService{

	public static final Logger LOGGER = LoggerFactory.getLogger(QuestionServiceImpl.class);
	
	@Autowired
	private QuestionUtil questionUtil;
	
	@Autowired
	private QuestionCacheService questionCacheService;
	
	/**
	 * Generate random question and save it to cache
	 */
	@Override
	public String getQuestion(String questinStr) {
		
		LOGGER.info("inside getQuestion");
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		List<Integer> numList = questionUtil.getRandomNumberList(
				questionUtil.getRandomNumber(2, 3));
		String strNum = numList.stream().filter(Objects::nonNull).map(Object::toString).collect(Collectors.joining(","));
		String strQuest = sb.append("Here you go, solve the question: \"Please sum the numbers ").
				append(strNum).append("\"").toString();
		LOGGER.info("strQuest " +strQuest);
		Question question = new Question();
		question.setQuestionName(strQuest);
		question.setQuestionNum(numList);
		
		questionCacheService.saveQuestionToCache(question);
		LOGGER.info("return strQuest " +strQuest);
		return strQuest;
	}

	/**
	 * find all questions from cache
	 */
	@Override
	public void getQuestionFromCache(String strSearch, Integer expectedValue) {
		// TODO Auto-generated method stub
		List<Question> questionsList =  questionCacheService.getQuestionsFromCache(); 
		LOGGER.info("questionsList from cache :  "+questionsList);
		Optional<Question> optional = questionsList.stream().filter(a->
			a.getQuestionName().equalsIgnoreCase(strSearch)).findAny();
		
		if(optional.isPresent()){
			int sumValue = optional.get().getQuestionNum().stream().collect(
					Collectors.summingInt(Integer::intValue));
			//int sumValue = question.getQuestionNum().stream().collect(Collectors.summingInt(Integer::intValue));
			LOGGER.info("sumValue from Cache :  "+sumValue + " expectedValue : " +expectedValue.intValue());
			
		}
	}

	/**
	 * find all answers from cache and store if none or not matching
	 */
	@Override
	public void getAnswerFromCache(String answer) {
		// TODO Auto-generated method stub
		List<String> answerList =  questionCacheService.getAnswerFromCache(); 
		LOGGER.info("answerList from cache :  "+answerList);
		if(CollectionUtils.isEmpty(answerList)){
			LOGGER.info("List is empty");
			questionCacheService.saveAnswerToCache(answer);
		}else if(CollectionUtils.contains(answerList.iterator(), answer)){
			LOGGER.info("answer already exist in list");
		}else
			questionCacheService.saveAnswerToCache(answer);;
	}
	
}
