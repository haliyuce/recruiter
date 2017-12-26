package com.heavenhr.recruit.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heavenhr.recruit.model.Application;
import com.heavenhr.recruit.repository.ApplicationRepository;

@Service
public class ApplicationFacade {
	
	@Autowired
	private ApplicationRepository applicationRepository;

	public Application getApplication(long applicationId) {
		return this.applicationRepository.findOne(applicationId);
	}

	public void update(Application application) {
		this.applicationRepository.save(application);
	}

}
