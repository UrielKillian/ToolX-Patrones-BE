package com.jossticeteam.toolx.domain.service;

import com.jossticeteam.toolx.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> deleteUser(Long userId);

    User updateUser(Long userId, User userRequest);

    User createUser(User user);

    User getUserByUserId(Long userId);

    Page<User> getAllUsers(Pageable pageable);
}
