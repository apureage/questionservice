package com.project.question.exception;

/**
 * Base exception for a Question Service
 *
 */
public abstract class QuestionBaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String code;
	private Object[] args;
	private Throwable throwable;

	/**
	 * 
	 * @param code - Error code in property bundle
	 */
	public QuestionBaseException(final String code) {
		super(code);
		this.code = code;
	}

	/**
	 * 
	 * @param code - Error code in property bundle
	 * @param args - Arguments as object array
	 */
	public QuestionBaseException(final String code, final Object[] args) {
		super(code);
		this.code = code;
		this.args = args;
	}

	public String getcode() {
		return code;
	}

	public Object[] getArgs() {
		return args;
	}

	public Throwable getThrowable() {
		return throwable;
	}

}