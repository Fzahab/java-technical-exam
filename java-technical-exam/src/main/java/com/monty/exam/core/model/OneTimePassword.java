package com.monty.exam.core.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OneTimePassword implements Serializable{

	private static final long serialVersionUID = -286529939451921640L;


	private String id;
	private String usrId;
	private String code;
	private Boolean verified;
	private LocalDateTime expirationTime;

	public OneTimePassword() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public LocalDateTime getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(LocalDateTime expirationTime) {
		this.expirationTime = expirationTime;
	}



}
