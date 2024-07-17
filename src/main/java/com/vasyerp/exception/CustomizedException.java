package com.vasyerp.exception;

public class CustomizedException extends RuntimeException {

	private Long error;
	private String msg;

	public CustomizedException(Long error, String message) {
		this.error = error;
		this.msg = message;

	}

	public Long getError() {
		return error;
	}

	public void setError(Long error) {
		this.error = error;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
