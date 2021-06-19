package com.jossticeteam.toolx.domain.service;

import com.jossticeteam.toolx.domain.model.Qualification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface QualificationService {
    Qualification getQualificationByUserIdAndUserQualifiedId(Long userId, Long userQualifiedId);
    ResponseEntity<?>deleteQualification(Long userId, Long userQualifiedId);
    Qualification createQualification(Long userId, Long userQualifiedId, Qualification qualification);
    Page<Qualification> getAllQualificationsByUserId(Long userId, Pageable pageable);
    Page<Qualification>getAllQualificationsByUserQualifiedId(Long userQualifiedId, Pageable pageable);
    Qualification editQualification(Long userId, Long userQualifiedId, Qualification qualificationDetails);
    Page<Qualification>getAllQualifications(Pageable pageable);
}
