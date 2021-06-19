package com.jossticeteam.toolx.domain.repository;

import com.jossticeteam.toolx.domain.model.Qualification;
import com.jossticeteam.toolx.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Long> {
    Page<Qualification> findByUserId(Long userId, Pageable pageable);

    Optional<Qualification> findByUserIdAndUserQualifiedId(Long userId , Long userDestinedId);
    Page<Qualification> findByUserQualified(Long userQualified, Pageable pageable);
}
