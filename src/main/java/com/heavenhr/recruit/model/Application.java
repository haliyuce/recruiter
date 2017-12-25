package com.heavenhr.recruit.model;

import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Application {
	
    @Id
    @GeneratedValue
    private Long id;
    
    @OneToOne(cascade = CascadeType.MERGE)
    private Candidate candidate;
    
    @Column
    private ApplicationStatus status;
    
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    protected ZonedDateTime applicationDate = ZonedDateTime.now();

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

}