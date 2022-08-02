package com.project.question.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import com.project.question.controller.impl.QuestionControllerImpl;
import com.project.question.service.QuestionService;

@SpringBootTest
@ContextConfiguration(classes = { QuestionControllerImpl.class })
@AutoConfigureMockMvc(addFilters = false) 
public class QuestionControllerTest extends QuestionBaseControllerTest{

	@MockBean
	private QuestionControllerImpl questionControllerImpl;
	
	@MockBean
	private QuestionService questionService;
	
	@Test
	void getQuestionTest() throws Exception {
		String question = "Hey Service, can you provide me a question with numbers to add?";
		Mockito.when(questionService.getQuestion(Mockito.any())).thenReturn(question);
		
		mockMvc.perform(get("/question").accept(MediaType.APPLICATION_JSON).queryParam(
				"question", question)).andExpect(status().isOk());		
	}
}
