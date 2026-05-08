package com.CANDIDATE_SERVICE.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@FeignClient(name = "ai-service", url = "http://localhost:8000")
public interface AiClient {

    @PostMapping(value = "/parse-cv", consumes = "multipart/form-data")
    Map<String, Object> parseCV(@RequestPart("file") MultipartFile file);
}