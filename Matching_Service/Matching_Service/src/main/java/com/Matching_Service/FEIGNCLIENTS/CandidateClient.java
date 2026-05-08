package com.Matching_Service.FEIGNCLIENTS;

import com.Matching_Service.dtos.CandidateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "candidate-service", url = "http://localhost:8082")
public interface CandidateClient {

    @GetMapping("/candidates/{id}")
    CandidateDTO getCandidate(@PathVariable Long id);
}
