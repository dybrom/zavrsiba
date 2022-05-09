package com.example.fixba.exception;

public class NotFoundException  extends  RuntimeException {

	private String message;

	public NotFoundException(String message) {
		super(message);
		this.message = message;
	}

	public NotFoundException() {
		super();
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
