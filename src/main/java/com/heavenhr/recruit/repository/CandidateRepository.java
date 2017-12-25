package com.heavenhr.recruit.repository;

import org.springframework.data.repository.CrudRepository;

import com.heavenhr.recruit.model.Candidate;

public interface CandidateRepository extends CrudRepository<Candidate, Long> {

}
