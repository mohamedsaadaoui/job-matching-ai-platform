package com.CANDIDATE_SERVICE.controller;

import com.CANDIDATE_SERVICE.entity.Candidate;
import com.CANDIDATE_SERVICE.repo.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateRepository repository;

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public String upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam String name,
            @RequestParam String email
    ) throws IOException {

        String uploadDir = "C:/uploads/";

        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            throw new RuntimeException("Invalid file");
        }

        String filePath = uploadDir + fileName;
        file.transferTo(new File(filePath));

        return "Uploaded successfully: " + fileName;
    }}