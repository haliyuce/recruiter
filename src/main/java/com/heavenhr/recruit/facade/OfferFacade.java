package com.heavenhr.recruit.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.heavenhr.recruit.model.Offer;
import com.heavenhr.recruit.repository.OfferRepository;

@Service
public class OfferFacade {
	
	@Autowired
	private OfferRepository offerRepository;

	public List<Offer> getOffers() {
		return Lists.newArrayList(this.offerRepository.findAll());
	}

	public void createOrUpdate(Offer offer) {
		this.offerRepository.save(offer);
	}

	public Offer getOffer(long offerId) {
		return this.offerRepository.findOne(offerId);
	}

}
