package com.Matching_Service.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MatchResponse {

    private double score;

    private List<String> matchedSkills;

    private List<String> missingSkills;
}