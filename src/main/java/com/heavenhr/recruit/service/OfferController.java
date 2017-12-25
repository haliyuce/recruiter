package com.heavenhr.recruit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.heavenhr.recruit.facade.OfferFacade;
import com.heavenhr.recruit.model.Offer;

@RestController
@RequestMapping("api/v1/offer")
public class OfferController {
	
	@Autowired
	private OfferFacade offerFacade;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Offer> getOffers() {
		return this.offerFacade.getOffers();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createOffer(@RequestBody Offer offer) {
		this.offerFacade.createOrUpdate(offer);
	}

}
