package com.monty.exam.dto.model;

import java.time.LocalDateTime;

public class TokenRespond {

	private String email;
	private String token;
	private LocalDateTime date;
	
	public TokenRespond() {
		super();
	}
	public TokenRespond(String email, String token, LocalDateTime date) {
		super();
		this.email = email;
		this.token = token;
		this.date = date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	
}
