package com.project.question.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.question.model.Question;

@Component
public class QuestionsssUtil {

	public static final Logger LOGGER = LoggerFactory.getLogger(QuestionsssUtil.class);
	
	@Autowired
	QuestionUtil generateRandomNymber;
	
	public String getQuestion() {
		StringBuffer sb = new StringBuffer();
		List<Integer> numbers = generateRandomNymber.getRandomNumberList(
				generateRandomNymber.getRandomNumber(2, 3));
		String strNum = numbers.stream().filter(Objects::nonNull).map(Object::toString).collect(Collectors.joining(","));
		String strQuest = sb.append("\n").append("Please sum the numbers ").append(strNum).append("\n").toString();
		LOGGER.info("strQuest " +strQuest);
		Question question = new Question();
		question.setQuestionName(strQuest);
		
		return strQuest;
	}
}
