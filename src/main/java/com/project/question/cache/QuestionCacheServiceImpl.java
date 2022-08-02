package com.project.question.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.project.question.model.Question;

@Component
public class QuestionCacheServiceImpl implements QuestionCacheService{
	
	public static final Logger LOGGER = LoggerFactory.getLogger(QuestionCacheServiceImpl.class);

	private List<Question> quesList =new ArrayList<Question>();
	private List<String> ansList =new ArrayList<String>();

	@Override
	public void saveQuestionToCache(Question question) {
		// TODO Auto-generated method stub
		LOGGER.info("save Question in Cache");
		Optional<Question> questionInfo = quesList.stream().filter(a->a.getQuestionName().equalsIgnoreCase(question.getQuestionName())).findAny();
		if(!questionInfo.isPresent()){
			LOGGER.info("adding to quesList");
			quesList.add(question);
		}
		//return quesList;
	}

	@Override
	public List<Question> getQuestionsFromCache() {
		// TODO Auto-generated method stub
		LOGGER.info("return all Question from Cache");
		return quesList;
	}

	@Override
	public void saveAnswerToCache(String answer) {
		// TODO Auto-generated method stub
		LOGGER.info("save answer in Cache");
		Optional<String> optional = ansList.stream().filter(a->a.equalsIgnoreCase(answer)).findAny();
		if(optional.isPresent()){
		   LOGGER.info("answer exists in Cache");
		}else{
			ansList.add(answer);
		}
		//return ansList;
	}

	@Override
	public List<String> getAnswerFromCache() {
		// TODO Auto-generated method stub
		LOGGER.info("return all answer from Cache");
		return ansList;
	}

}
