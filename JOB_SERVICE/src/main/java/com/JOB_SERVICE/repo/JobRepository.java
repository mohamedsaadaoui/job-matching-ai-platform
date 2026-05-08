package com.JOB_SERVICE.repo;

import com.JOB_SERVICE.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}