package com.heavenhr.recruit.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "offer", uniqueConstraints = @UniqueConstraint(name = "uc_job_title", columnNames = { "jobTitle" }))
public class Offer {

    @Id
    @GeneratedValue
    protected Long id;
	
	@Column
	private String jobTitle;
    
	@Column(nullable = false)
    protected LocalDate startDate;
	
	@OneToMany(orphanRemoval = true, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Application> applications;
	
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    protected ZonedDateTime dateCreated = ZonedDateTime.now();
    
    @Column
    private Boolean active;
	
	public Offer() {
		this.applications = new ArrayList<>();
	}
	
	@JsonProperty(access = Access.READ_ONLY)
	public int getNumberOfApplications() {
		return this.applications.size();
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	
	public void applyForJob(Application application) {
		this.applications.add(application);
	}
	
	public Long getId() {
		return id;
	}
	
	public Boolean getActive() {
		return active;
	}
	
}
