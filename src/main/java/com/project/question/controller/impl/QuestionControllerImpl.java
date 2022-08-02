package com.project.question.controller.impl;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.question.controller.QuestionController;
import com.project.question.service.QuestionService;
import com.project.question.utils.QuestionUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionControllerImpl implements QuestionController{
	
	public static final Logger LOGGER = LoggerFactory.getLogger(QuestionControllerImpl.class);
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuestionUtil questionUtil;
	
	@Override
	@GetMapping
	public ResponseEntity<String> getQuestion(String question) {
		// TODO Auto-generated method stub
		LOGGER.info("getQuestion question : "+question);
		if(StringUtils.isNotBlank(question)){
			LOGGER.info("provide rando question");
			return new  ResponseEntity<String>(questionService.getQuestion(question),HttpStatus.OK);	
		}else{
			return new  ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	@GetMapping("/answer")
	public ResponseEntity<String> getAnswer(String input) {
		// TODO Auto-generated method stub
		LOGGER.info("getAnswer input : "+input);
		if(StringUtils.isNotBlank(input)) {
			String response = "";
			String questionStr = input.substring(input.indexOf("\"") + 1, input.lastIndexOf("\""));
			String answerStr = input.substring(input.lastIndexOf("\"") + 1, input.length());
			
			LOGGER.info("getAnswer questionStr : "+questionStr);
			LOGGER.info("getAnswer answerStr : "+answerStr);

			Integer totalValue =  questionUtil.questionSum(questionStr);
			Integer answerValue =    questionUtil.answerSum(answerStr);		
			
			questionService.getQuestionFromCache(input, answerValue);
			questionService.getAnswerFromCache(input);
			
			if(totalValue.equals(answerValue)){
				response = "That’s great!"; 
			}else {
				response = "That’s wrong. Please try again."; 
			}
			
			return new  ResponseEntity<String>(response,HttpStatus.OK);
		}else {
			return new  ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
