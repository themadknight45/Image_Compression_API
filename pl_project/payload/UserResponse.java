package com.pl_project.pl_project.payload;

public class UserResponse {
	public UserResponse(String loginStatus, String loginMessage, int token) {
		super();
		this.loginStatus = loginStatus;
		this.loginMessage = loginMessage;
		this.token = token;
	}
	public UserResponse() {
		super();
		this.loginMessage="invald User";
		this.loginStatus="login failed";
		this.token=-1;
	}
	private String loginStatus;
	private String loginMessage;
	private int token;
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getLoginMessage() {
		return loginMessage;
	}
	public void setLoginMessage(String loginMessage) {
		this.loginMessage = loginMessage;
	}
	public int getToken() {
		return token;
	}
	public void setToken(int token) {
		this.token = token;
	}
}
