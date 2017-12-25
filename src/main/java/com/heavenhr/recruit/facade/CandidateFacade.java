package com.heavenhr.recruit.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.heavenhr.recruit.model.Application;
import com.heavenhr.recruit.model.Candidate;
import com.heavenhr.recruit.model.Offer;
import com.heavenhr.recruit.repository.CandidateRepository;
import com.heavenhr.recruit.repository.OfferRepository;

@Service
public class CandidateFacade {
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private OfferRepository offerRepository;
	
	public void createOrUpdateCandidate(Candidate candidate) {
		this.candidateRepository.save(candidate);
	}

	public List<Candidate> getAllCandidates() {
		return  Lists.newArrayList(this.candidateRepository.findAll());
	}

	public void applyJob(Long offerId, Long candidateId) {
		Candidate candidate = this.candidateRepository.findOne(candidateId);
		Offer offer = this.offerRepository.findOne(offerId);
		
		Application application = new Application();
		application.setCandidate(candidate);
		
		offer.applyForJob(application);
		
		this.offerRepository.save(offer);
	}
	
}
