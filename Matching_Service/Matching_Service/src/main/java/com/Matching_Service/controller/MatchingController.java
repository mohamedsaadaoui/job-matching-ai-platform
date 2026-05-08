package com.Matching_Service.controller;

import com.Matching_Service.FEIGNCLIENTS.CandidateClient;
import com.Matching_Service.FEIGNCLIENTS.JobClient;
import com.Matching_Service.dtos.CandidateDTO;
import com.Matching_Service.dtos.JobDTO;
import com.Matching_Service.response.MatchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/matching")
@RequiredArgsConstructor
public class MatchingController {

    private final CandidateClient candidateClient;
    private final JobClient jobClient;

    @GetMapping("/{candidateId}/{jobId}")
    public MatchResponse match(
            @PathVariable Long candidateId,
            @PathVariable Long jobId
    ) {

        CandidateDTO candidate =
                candidateClient.getCandidate(candidateId);

        JobDTO job =
                jobClient.getJob(jobId);

        // convert skills
        List<String> candidateSkills =
                Arrays.stream(candidate.getSkills()
                                .replace("[", "")
                                .replace("]", "")
                                .split(","))
                        .map(String::trim)
                        .toList();

        List<String> requiredSkills =
                Arrays.stream(job.getRequiredSkills().split(","))
                        .map(String::trim)
                        .toList();

        // matching
        List<String> matched = new ArrayList<>();
        List<String> missing = new ArrayList<>();

        for (String skill : requiredSkills) {

            if (candidateSkills.stream()
                    .anyMatch(s -> s.equalsIgnoreCase(skill))) {

                matched.add(skill);

            } else {
                missing.add(skill);
            }
        }

        // calculate score
        double score =
                ((double) matched.size()
                        / requiredSkills.size()) * 100;

        return new MatchResponse(
                score,
                matched,
                missing
        );
    }
}