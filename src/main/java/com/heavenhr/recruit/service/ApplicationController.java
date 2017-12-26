package com.heavenhr.recruit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.heavenhr.recruit.exception.EntityNotFoundException;
import com.heavenhr.recruit.facade.ApplicationFacade;
import com.heavenhr.recruit.model.Application;
import com.heavenhr.recruit.model.ApplicationStatus;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/application")
public class ApplicationController {
	
	@Autowired
	private ApplicationFacade applicationFacade;
	
	@PutMapping("/{applicationId}/{newstatus}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@ApiOperation(value = "Update a application status", consumes="application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "applicationId", value = "application id", required = false, dataType = "long", paramType = "path"),
        @ApiImplicitParam(name = "newstatus", value = "new status value", required = false, paramType = "path")
      })
	public void updateApplicationStatus(@PathVariable long applicationId, @PathVariable ApplicationStatus newstatus) throws EntityNotFoundException {
		Application application = this.applicationFacade.getApplication(applicationId);
		
		if(application == null) {
			throw new EntityNotFoundException("Application id is invalid");
		}
		
		if(application.getStatus().name().equals(newstatus)) {
			return; // no op
		}
		
		application.setStatus(newstatus);
		this.applicationFacade.update(application);
		
	}

}
