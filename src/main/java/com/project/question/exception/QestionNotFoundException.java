package com.project.question.exception;

public class QestionNotFoundException  extends QuestionBaseException {

	private static final long serialVersionUID = 7911791077699304530L;

	public QestionNotFoundException(String code) {
		super(code);
	}

	public QestionNotFoundException(String code, Object... args) {
		super(code, args);
	}
}

