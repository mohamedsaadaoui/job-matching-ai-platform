package com.CANDIDATE_SERVICE.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Candidate {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;

    @Column(columnDefinition = "TEXT")
    private String skills; // JSON string

    private String cvUrl; // lien vers fichier
}