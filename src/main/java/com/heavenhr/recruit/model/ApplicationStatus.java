package com.heavenhr.recruit.model;

import com.heavenhr.recruit.model.status.AppliedStatusNotifier;

/**
 * 
 * the enum values are notifiers also so each status knows what to call for notification
 *
 */
public enum ApplicationStatus {
	
	APPLIED(new AppliedStatusNotifier()), 
	INVITED(new AppliedStatusNotifier()), 
	REJECTED(new AppliedStatusNotifier()), 
	HIRED(new AppliedStatusNotifier());
	
	private final com.heavenhr.recruit.model.status.ApplicationStatusNotifer actionInstance;
	
	ApplicationStatus(com.heavenhr.recruit.model.status.ApplicationStatusNotifer applicationStatus) {
		this.actionInstance = applicationStatus;
	}
	
	public com.heavenhr.recruit.model.status.ApplicationStatusNotifer getActionInstance() {
		return actionInstance;
	}

}
