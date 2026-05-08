package com.Matching_Service.dtos;

import lombok.Data;

@Data
public class CandidateDTO {

    private Long id;

    private String name;

    private String email;

    private String skills;
}