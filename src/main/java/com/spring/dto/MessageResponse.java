package com.spring.dto;

public class MessageResponse<T> {

	private String status = ManaGlobal.SUCCESS;    //状态:success  or  fail
	private String message;   //信息：成功 or 错误
	private T object;         //对象
	private String[] usernames;

	public String[] getUsernames() {
		return usernames;
	}

	public void setUsernames(String[] usernames) {
		this.usernames = usernames;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getObject() {
		return object;
	}
	public void setObject(T object) {
		this.object = object;
	}
	
}
