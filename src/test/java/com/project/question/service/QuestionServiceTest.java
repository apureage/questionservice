package com.project.question.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.project.question.cache.QuestionCacheService;
import com.project.question.model.Question;
import com.project.question.service.impl.QuestionServiceImpl;
import com.project.question.utils.QuestionUtil;


@SpringBootTest
@ContextConfiguration(classes = { QuestionServiceImpl.class })
public class QuestionServiceTest {

	@Autowired
	private QuestionService questionService;
	
	@MockBean
	private QuestionUtil questionUtil;
	
	@MockBean
	private QuestionCacheService questionCacheService;
	
	@Test
	void getQuestionTest() {
		String strNum = "3,4,4";
		String strQuest = "Here you go, solve the question: \"Please sum the numbers 3,4,4\"";
		//Mockito.when(questionUtil.getRandomNumber(Mockito.any(), Mockito.any())).thenReturn(Mockito.any());
		List<Integer> numList = new ArrayList<>();
		//Mockito.when(questionUtil.getRandomNumberList(Mockito.any())).thenReturn(Mockito.any());
		Question question = new Question();
		question.setQuestionName(strQuest);
		question.setQuestionNum(numList);
	
		Mockito.doNothing().when(questionCacheService).saveQuestionToCache(any());
		questionService.getQuestion("Hey Service, can you provide me a question with numbers to add?");
		//Mockito.verify(questionService, atLeast(1)).getQuestion(Mockito.any());
	}
	
}
