package com.project.question.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class QuestionUtil {

	public static final Logger LOGGER = LoggerFactory.getLogger(QuestionUtil.class);
	
	public static int getRandomNumber(int first, int second) {

		Random random = new Random();
		
		return random.ints(first, (second + 1)).findAny().getAsInt();
	}
	
	public static List<Integer> getRandomNumberList(int number){
		List<Integer> randomList = new ArrayList<Integer>(); 
		
		for(int i=0;i<number;i++){
			randomList.add(getRandomNumber(number,  number + 1));
		}
		LOGGER.info("randomList :"+randomList);
		return randomList;		
	}
	
	public static Integer questionSum(String input){
		LOGGER.info("questionSum input :"+input);
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(input);
        int questionSum = 0;
        while( m.find() ) {
        	questionSum += Integer.valueOf(m.group());
        }
        LOGGER.info("questionSum value : "+questionSum);
        return questionSum;
	}
	
	public static Integer answerSum(String input){
        
		LOGGER.info("answerSum input :"+input);
	    Pattern p = Pattern.compile("\\d+");
	    Matcher m = p.matcher(input);
	    int answerSum = 0;
	    if(m.find()) {          
	    	answerSum = Integer.valueOf(m.group().toString());
	    }
	    LOGGER.info("answerSum value : "+answerSum);
	    return answerSum;
	}
}
