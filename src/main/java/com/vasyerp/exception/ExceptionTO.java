package com.vasyerp.exception;

public class ExceptionTO {

	private Long errorCode;
	private String errorMsg;
	private String humanizedMsg;

	public ExceptionTO(Long errorCode, String errorMsg, String humanizedMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.humanizedMsg = humanizedMsg;
	}

	public Long getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Long errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getHumanizedMsg() {
		return humanizedMsg;
	}

	public void setHumanizedMsg(String humanizedMsg) {
		this.humanizedMsg = humanizedMsg;
	}

}
