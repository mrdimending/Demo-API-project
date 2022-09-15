package com.jumpee.commerce.model;

import javax.validation.constraints.Size;

public class Password 
{
	private String oldPassword;
	@Size(min = 8, message = "Password must be at least 8 characters")
	private String newPassword;
	private String confirmPassword;
	
	public Password() {};
	public Password(String oldPassword, String newPassword, String confirmPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
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
	
	
}
