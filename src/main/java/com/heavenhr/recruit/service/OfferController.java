package com.heavenhr.recruit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.heavenhr.recruit.exception.EntityNotFoundException;
import com.heavenhr.recruit.facade.OfferFacade;
import com.heavenhr.recruit.model.Application;
import com.heavenhr.recruit.model.Offer;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/offer")
public class OfferController {
	
	@Autowired
	private OfferFacade offerFacade;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "Get all offers", consumes="application/json")
	public List<Offer> getOffers() {
		return this.offerFacade.getOffers();
	}
	
	@GetMapping("/{offerId}")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "Get one specific offer by id", consumes="application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "offerId", value = "offer id", required = false, dataType = "long", paramType = "path")
      })
	public Offer getOffer(@PathVariable long offerId ) {
		return this.offerFacade.getOffer(offerId);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Create/Update offer", consumes="application/json")
	public void createOffer(@RequestBody Offer offer) {
		this.offerFacade.createOrUpdate(offer);
	}
	
	@GetMapping("/{offerId}/application/{applicationId}")
	@ApiOperation(value = "Get one specific application belongs to the offer", consumes="application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "offerId", value = "offer id", required = false, dataType = "long", paramType = "path"),
        @ApiImplicitParam(name = "applicationId", value = "application id", required = false, dataType = "long", paramType = "path")
      })
	public Application getOfferApplication(@PathVariable long offerId, @PathVariable long applicationId) throws EntityNotFoundException {
		Offer offer = this.offerFacade.getOffer(offerId);
		
		if(offer == null) {
			throw new EntityNotFoundException("Offer id is invalid");
		}
		
		List<Application> response = offer.getApplications().stream().filter(p -> p.getId().equals(applicationId)).collect(Collectors.toList());
		
		if(response == null || response.isEmpty()) {
			throw new EntityNotFoundException("this application id is not present or not under this offer");
		}
			
		return response.get(0);
	}
	
	@GetMapping("/{offerId}/application")
	@ApiOperation(value = "Get all applications of a specific offer", consumes="application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "offerId", value = "offer id", required = false, dataType = "long", paramType = "path")
      })
	public List<Application> getOfferApplications(@PathVariable long offerId) throws EntityNotFoundException {
		Offer offer = this.offerFacade.getOffer(offerId);
		
		if(offer == null) {
			throw new EntityNotFoundException("Offer id is invalid");
		}
		
		return offer.getApplications();
	}

}
