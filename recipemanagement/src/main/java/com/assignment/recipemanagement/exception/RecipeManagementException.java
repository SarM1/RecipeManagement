package com.assignment.recipemanagement.exception;

import org.springframework.http.HttpStatus;

public class RecipeManagementException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private HttpStatus httpStatus;

	public RecipeManagementException(String errorMessage, HttpStatus status) {
		super();

		this.errorMessage = errorMessage;
		this.httpStatus = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
