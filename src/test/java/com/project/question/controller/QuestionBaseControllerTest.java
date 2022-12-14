package com.project.question.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc(addFilters = false) 
public class QuestionBaseControllerTest {

	@Autowired
    protected MockMvc mockMvc;
}
