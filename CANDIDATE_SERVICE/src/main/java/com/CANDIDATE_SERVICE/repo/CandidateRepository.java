package com.CANDIDATE_SERVICE.repo;

import com.CANDIDATE_SERVICE.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
