package com.monty.exam.core.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable {

	private static final long serialVersionUID = 3724565264140724974L;

	
	//(id, name, email, password, age, active, created_at)

	private String id;
	private String name;
	private String email;
	private String password;
	private Integer age;
	private Boolean active;
	private LocalDateTime createdAt;
	
	public User() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	
	
	
	
}
