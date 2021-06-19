package com.jossticeteam.toolx.domain.service;

import com.jossticeteam.toolx.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    Page<Comment> getAllCommentsByUserId(Long userId, Pageable pageable);

    Comment getCommentById(Long commentId);

    Comment createComment(Long userId,Long userCommentedId ,Comment comment);

    Comment updateComment(Long commentId, Comment commentDetails);

    ResponseEntity<?>deleteComment(Long commentId);

}
