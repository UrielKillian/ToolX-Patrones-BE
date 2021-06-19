package com.jossticeteam.toolx.service;

import com.jossticeteam.toolx.domain.model.Qualification;
import com.jossticeteam.toolx.domain.model.User;
import com.jossticeteam.toolx.domain.repository.QualificationRepository;
import com.jossticeteam.toolx.domain.repository.UserRepository;
import com.jossticeteam.toolx.domain.service.QualificationService;
import com.jossticeteam.toolx.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QualificationServiceImpl implements QualificationService {
    @Autowired
    private QualificationRepository qualificationRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Qualification getQualificationByUserIdAndUserQualifiedId(Long userId, Long postId) {
        return qualificationRepository.findByUserIdAndUserQualifiedId(userId, postId)
                .orElseThrow(()->new ResourceNotFoundException("Qualification not found with Id" + postId +  "and UserId" + userId));
    }

    @Override
    public Qualification createQualification(Long userId, Long userQualifiedId, Qualification qualification) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        User userQualified = userRepository.findById(userQualifiedId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userQualifiedId));
        Optional<Qualification> existingQualification = qualificationRepository.findByUserIdAndUserQualifiedId(userId, userQualifiedId);
        if (!existingQualification.isEmpty()){
            throw new IllegalArgumentException("El usuario ya tiene un qualification con este usuario destino");
        }else {
            qualification.setUser(user);
            qualification.setUserQualified(userQualified);
            return qualificationRepository.save(qualification);
        }

    }

    @Override
    public Page<Qualification> getAllQualificationsByUserId(Long userId, Pageable pageable) {
        return qualificationRepository.findByUserId(userId,pageable);
    }

    @Override
    public Page<Qualification>getAllQualificationsByUserQualifiedId(Long userQualifiedId, Pageable pageable) {
        return qualificationRepository.findByUserQualified(userQualifiedId,pageable);
    }

    @Override
    public Qualification editQualification(Long userId, Long userQualifiedId, Qualification qualificationDetails) {
        Qualification qualification = qualificationRepository.findByUserIdAndUserQualifiedId(userId,userQualifiedId).orElseThrow(()->
                new ResourceNotFoundException("Qualification not found with Id" + userQualifiedId +  "and UserId" + userId));
        qualification.setQualification(qualificationDetails.getQualification());
        return qualificationRepository.save(qualification);
    }

    @Override
    public ResponseEntity<?> deleteQualification(Long userId, Long userQualifiedId) {
        Qualification qualification = qualificationRepository.findByUserIdAndUserQualifiedId(userId,userQualifiedId).orElseThrow(()->
                new ResourceNotFoundException("Qualification not found with Id" + userId +  "and UserId" + userQualifiedId));
        qualificationRepository.delete(qualification);
        return ResponseEntity.ok().build();

    }

    @Override
    public Page<Qualification> getAllQualifications(Pageable pageable) {
        return qualificationRepository.findAll(pageable);
    }
}
