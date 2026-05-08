package com.JOB_SERVICE.controller;

import com.JOB_SERVICE.entity.Job;
import com.JOB_SERVICE.repo.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobRepository repository;

    @PostMapping
    public Job create(@RequestBody Job job) {
        return repository.save(job);
    }

    @GetMapping
    public List<Job> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Job getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }
}
