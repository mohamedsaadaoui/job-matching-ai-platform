package com.CANDIDATE_SERVICE.controller;

import com.CANDIDATE_SERVICE.FeignClient.AiClient;
import com.CANDIDATE_SERVICE.entity.Candidate;
import com.CANDIDATE_SERVICE.repo.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateRepository repository;
    private final AiClient aiClient;

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public Candidate uploadCV(
            @RequestParam("file") MultipartFile file,
            @RequestParam String name,
            @RequestParam String email
    ) throws IOException {

        // 🤖 FIRST → call AI service
        Map<String, Object> response = aiClient.parseCV(file);

        // 📁 upload folder
        String uploadDir = "C:/uploads/";

        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 📄 save file
        String fileName = file.getOriginalFilename();

        if (fileName == null) {
            throw new RuntimeException("Invalid file");
        }

        String filePath = uploadDir + fileName;

        // 💾 THEN save file
        file.transferTo(new File(filePath));

        // 👤 create candidate
        Candidate candidate = new Candidate();
        candidate.setName(name);
        candidate.setEmail(email);
        candidate.setCvUrl(filePath);

        // 🧠 save skills
        candidate.setSkills(response.get("skills").toString());

        return repository.save(candidate);
    }

    @GetMapping("/{id}")
    public Candidate getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
    }
}

