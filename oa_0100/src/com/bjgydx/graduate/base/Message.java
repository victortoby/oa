package com.bjgydx.graduate.base;

/**
 * 返回消息类
 * @author KAI
 *
 */


public class Message {

	private Boolean success;

	private String message;
	
	private String otherInfo;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

}
