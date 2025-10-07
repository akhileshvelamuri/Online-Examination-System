package com.oes.daointerfaces;

import com.oes.models.Candidate;

public interface CandidateDao {
	
	int insertCandidate(Candidate candidate);
	Candidate getCandidate(String email);
}
