package com.project.question.exception;

public class UnauthenticatedException extends QuestionBaseException  {

	private static final long serialVersionUID = 7911791077699304531L;
	
	public UnauthenticatedException(String code) {
		super(code);
	}

	public UnauthenticatedException(String code, Object... args) {
		super(code, args);
	}
}
