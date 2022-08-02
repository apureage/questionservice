package com.project.question.constants;

public enum QuestionErrorCodes {

	SERVER_ERROR("internal.server.error"),
	NOT_FOUND("not.found"),
	UNAUTHENTICATED("unauthenticated"),
	INVALID_TOKEN("invalid.token");

	private final String message;
	
	QuestionErrorCodes(final String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
