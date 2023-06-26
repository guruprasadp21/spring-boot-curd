package com.integra.test.SpringBootDemo.exception;

public class AppError {
	
	private Integer errorCode;
	private String errorMsg;
	
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public AppError(Integer errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	 @Override 
	 	public String toString() { return "AppError [errorCode=" +
	 errorCode + ", errorMsg=" + errorMsg + "]"; }
	 
}     

    
