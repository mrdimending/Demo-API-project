package com.jumpee.commerce.model;

import javax.validation.constraints.Size;

public class Password 
{
	private String oldPassword;
	@Size(min = 8, message = "Password must be at least 8 characters")
	private String newPassword;
	private String confirmPassword;
	private String token;
	
	
	public Password() {};
	public Password(String oldPassword, String newPassword, String confirmPassword, String token) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
		this.token = token;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
