package com.jossticeteam.toolx.domain.service;

import com.jossticeteam.toolx.domain.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface JobService {
    Page<Job> getAllJobsByUserId(Long userId, Pageable pageable);
    Job createJob(Job job);
    Job getJobById(Long jobId);
    Job updateJob(Long jobId, Job job);
    ResponseEntity<?> deleteJob(Long jobId);

}
