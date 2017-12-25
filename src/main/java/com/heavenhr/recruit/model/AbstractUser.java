package com.heavenhr.recruit.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public abstract class AbstractUser {
	
    @Id
    @GeneratedValue
    protected Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    protected ZonedDateTime dateCreated = ZonedDateTime.now();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
