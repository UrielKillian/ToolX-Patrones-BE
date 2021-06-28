package com.jossticeteam.toolx.service;

import com.jossticeteam.toolx.domain.model.Job;
import com.jossticeteam.toolx.domain.repository.JobRepository;
import com.jossticeteam.toolx.domain.service.JobService;
import com.jossticeteam.toolx.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {
     @Autowired
     private JobRepository jobRepository;



     @Override
    public Page<Job> getAllJobsByUserId(Long userId, Pageable pageable){ return jobRepository.findByUserId(userId, pageable ); }

    @Override
    public Job createJob(Job job){
         return jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long jobId){

         return jobRepository.findById(jobId)
                 .orElseThrow(()-> new ResourceNotFoundException("Job", "Id", jobId));
    }

    @Override
    public Job updateJob(Long jobId, Job jobRequest){
         Job job = jobRepository.findById(jobId)
                 .orElseThrow(()-> new ResourceNotFoundException("Job", "Id", jobId));

         return jobRepository.save(
                 job.setTitle(jobRequest.getTitle())
                 .setDescription(jobRequest.getDescription())
                 .setPayment(jobRequest.getPayment())
                 .setUser(jobRequest.getUser())
         );

    }
    public ResponseEntity<?> deleteJob(Long jobId){
        Job job = jobRepository.findById(jobId)
                .orElseThrow(()-> new ResourceNotFoundException("Job", "Id", jobId));
        jobRepository.delete(job);
        return ResponseEntity.ok().build();


    }



}
