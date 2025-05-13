package com.onlinefoodordering.response;

import com.onlinefoodordering.model.USER_ROLE;

import lombok.Data;

@Data
public class AuthResponse {

	private String jwt;
	private String message;
	private USER_ROLE role;
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public USER_ROLE getRole() {
		return role;
	}
	public void setRole(USER_ROLE user_ROLE) {
		this.role = user_ROLE;
	}
	
	
}
