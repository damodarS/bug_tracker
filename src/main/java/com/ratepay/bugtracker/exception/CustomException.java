package com.ratepay.bugtracker.exception;

public class CustomException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;

	public CustomException(String msg, Throwable cause) {
		super(msg, cause);
		this.msg = msg;
	}

	public CustomException(Throwable cause) {
		super(cause);
		this.msg = cause.getMessage();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public CustomException(String msg) {
		super(msg);
		this.msg = msg;
	}

}
