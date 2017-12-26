package com.heavenhr.recruit.model.status;

import org.apache.log4j.Logger;

import com.heavenhr.recruit.model.Candidate;

public abstract class ApplicationStatusNotifer {
	
	protected static final Logger LOGGER = Logger.getLogger(ApplicationStatusNotifer.class);

	public abstract void notify(Candidate candidate);

}
