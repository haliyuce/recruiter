package com.heavenhr.recruit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.heavenhr.recruit.facade.CandidateFacade;
import com.heavenhr.recruit.model.Candidate;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/candidate")
public class CandidateController {
	
	@Autowired
	private CandidateFacade candidateFacade;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Create a candidate / signup", consumes="application/json")
	public void create(@RequestBody Candidate candidate) {
		this.candidateFacade.createOrUpdateCandidate(candidate);
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@ApiOperation(value = "Update a candidate information", consumes="application/json")
	public void update(@RequestBody Candidate candidate) {
		this.candidateFacade.createOrUpdateCandidate(candidate);
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@ApiOperation(value = "Get all candidates", consumes="application/json")
	public List<Candidate> getAllCandidates() {
		return this.candidateFacade.getAllCandidates();
	}
	
	@PutMapping("/{candidateId}/{offerId}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@ApiOperation(value = "Candidate application action", consumes="application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "candidateId", value = "candidate id", required = false, dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "offerId", value = "offer id", required = false, dataType = "string", paramType = "query")
      })
	public void applyOffer(@PathVariable Long candidateId, @PathVariable Long offerId) {
		this.candidateFacade.applyJob(offerId, candidateId);
	}

}
