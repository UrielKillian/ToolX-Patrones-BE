package com.jossticeteam.toolx.controller;

import com.jossticeteam.toolx.domain.model.Qualification;
import com.jossticeteam.toolx.domain.service.QualificationService;
import com.jossticeteam.toolx.resource.QualificationResource;
import com.jossticeteam.toolx.resource.SaveQualificationResource;
import io.swagger.v3.oas.annotations.Operation;
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

@Tag(name = "qualifications", description = "Qualifications API")
@RestController
@RequestMapping("/api")
public class QualificationController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private QualificationService qualificationService;

    @GetMapping("/users/{userId}/qualifications")
    public Page<QualificationResource> getAllQualificationByUserId(
            @PathVariable(name = "userId") Long userId, Pageable pageable){
        List<QualificationResource> qualifications=qualificationService.getAllQualificationsByUserId(userId,pageable).
                getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int qualification_count=qualifications.size();
        return  new PageImpl<>(qualifications,pageable,qualification_count);
    }

    @GetMapping("/users/{userQualifiedId}/qualifications")
    public Page<QualificationResource> getAllQualificationByUserQualifiedId(
            @PathVariable(name = "userQualifiedId") Long userQualifiedId, Pageable pageable){
        List<QualificationResource> qualifications=qualificationService.getAllQualificationsByUserQualifiedId(userQualifiedId,pageable).
                getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int qualification_count=qualifications.size();
        return  new PageImpl<>(qualifications,pageable,qualification_count);
    }

    @PostMapping("/users/{userId}/users/{userQualifiedId}/qualifications")
    public QualificationResource createQualification(@PathVariable(name = "userId")Long userId,
                                                     @PathVariable(name = "userQualifiedId") Long postId,
                                                     @Valid @RequestBody SaveQualificationResource resource){
        return convertToResource(qualificationService.createQualification(userId,postId,convertToEntity(resource)));
    }

    @PutMapping("/users/{userId}/users/{userQualifiedId}/qualifications")
    public QualificationResource editQualification(@PathVariable(name = "userId")Long userId,
                                                   @PathVariable(name = "userQualifiedId") Long userQualifiedId,
                                                   @Valid @RequestBody SaveQualificationResource resource){
        return convertToResource(qualificationService.editQualification(userId,userQualifiedId,convertToEntity(resource)));
    }

    @GetMapping("/users/{userId}/users/{userQualifiedId}/qualifications")
    public QualificationResource getQualificationByUserIdAndPostId(@PathVariable(name = "userId") Long userId,
                                                                   @PathVariable(name= "userQualifiedId") Long userQualifiedId){
        return convertToResource(qualificationService.getQualificationByUserIdAndUserQualifiedId(userId, userQualifiedId));
    }

    @DeleteMapping("/users/{userId}/users/{userQualifiedId}/qualifications")
    public ResponseEntity<?> deleteQualification(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name= "userQualifiedId") Long userQualifiedId) {
        return qualificationService.deleteQualification(userId, userQualifiedId);
    }
    private QualificationResource convertToResource(Qualification entity){
        return mapper.map(entity, QualificationResource.class);
    }
    private Qualification convertToEntity(SaveQualificationResource resource) {
        return mapper.map(resource, Qualification.class);
    }
}
