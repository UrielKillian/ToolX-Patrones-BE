package com.jossticeteam.toolx.controller;

import com.jossticeteam.toolx.domain.model.Job;
import com.jossticeteam.toolx.domain.service.JobService;
import com.jossticeteam.toolx.resource.JobResource;
import com.jossticeteam.toolx.resource.SaveJobResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "jobs", description = "Jobs API")
@RestController
@RequestMapping("/api")
public class JobsController {

    @Autowired
    private JobService jobService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/users/{userId}/jobs/{jobId}")
    public Page<JobResource> getAllJobsByUserId(@PathVariable Long userId, Pageable pageable) {
        Page<Job> jobPage = jobService.getAllJobsByUserId(userId, pageable);
        List<JobResource> resources = jobPage.getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/users/{userId}/jobs/")
    public JobResource createJob(@PathVariable Long userId, @Valid @RequestBody SaveJobResource resource) {
        return convertToResource(jobService.updateJob(userId, convertToEntity(resource)));
    }

    @PutMapping("/users/{userId}/jobs/{jobId}")
    public JobResource updateJob(@PathVariable Long userId, @PathVariable Long jobId,
            @Valid @RequestBody SaveJobResource resource) {
        return convertToResource(jobService.updateJob(jobId, convertToEntity(resource)));
    }

    @DeleteMapping("/users/{userId}/jobs/{jobId}")
    public ResponseEntity<?> deleteJob(@PathVariable Long jobId) {
        return jobService.deleteJob(jobId);
    }

    private Job convertToEntity(SaveJobResource resource) {
        return mapper.map(resource, Job.class);
    }

    private JobResource convertToResource(Job entity) {
        return mapper.map(entity, JobResource.class);
    }

}
