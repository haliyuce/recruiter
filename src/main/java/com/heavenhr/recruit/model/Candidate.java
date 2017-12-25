package com.heavenhr.recruit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "candidate", uniqueConstraints = @UniqueConstraint(name = "uc_email", columnNames = { "email" }))
public class Candidate extends AbstractUser {
	
	@Column
	private String resumeText;
	
	@Column
	private String email;
	
	public String getResumeText() {
		return resumeText;
	}
	
	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

}
