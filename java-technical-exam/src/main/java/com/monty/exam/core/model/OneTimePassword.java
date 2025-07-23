package com.monty.exam.core.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OneTimePassword implements Serializable{

	private static final long serialVersionUID = -286529939451921640L;

	private Long id;
	private Long usrId;
	private String code;
	private Boolean verified;
	private LocalDateTime expirationTime;

	public OneTimePassword() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUsrId() {
		return usrId;
	}

	public void setUsrId(Long usrId) {
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
