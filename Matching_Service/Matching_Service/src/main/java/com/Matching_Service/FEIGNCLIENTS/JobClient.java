package com.Matching_Service.FEIGNCLIENTS;

import com.Matching_Service.dtos.JobDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "job-service", url = "http://localhost:8083")
public interface JobClient {

    @GetMapping("/jobs/{id}")
    JobDTO getJob(@PathVariable Long id);
}
