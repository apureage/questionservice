package com.project.question.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Question implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String type;
	
	private String questionName;
	
	private List<Integer> questionNum;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public List<Integer> getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(List<Integer> questionNum) {
		this.questionNum = questionNum;
	}
	
}
