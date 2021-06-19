package com.jossticeteam.toolx.service;

import com.jossticeteam.toolx.domain.model.Comment;
import com.jossticeteam.toolx.domain.model.User;
import com.jossticeteam.toolx.domain.repository.CommentRepository;
import com.jossticeteam.toolx.domain.repository.UserRepository;
import com.jossticeteam.toolx.domain.service.CommentService;
import com.jossticeteam.toolx.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Comment> getAllCommentsByUserId(Long userId, Pageable pageable) {
        return commentRepository.findByUserId(userId, pageable);
    }

    @Override
    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment not found with Id " + commentId));
    }

    @Override
    public Comment createComment(Long userId, Long userCommentedId, Comment comment) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with Id: "+ userId));
        User userDestined = userRepository.findById(userCommentedId).orElseThrow(()-> new ResourceNotFoundException("User not found with Id: "+ userCommentedId));
        comment.setUser(user);
        comment.setUserCommented(userDestined);
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long commentId, Comment commentDetails) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment not found with Id: "+ commentId));
        comment.setContent(commentDetails.getContent());
        return commentRepository.save(comment);
    }

    @Override
    public ResponseEntity<?> deleteComment(Long commentId) {
        return commentRepository.findById(commentId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Comment not found with Id: "+ commentId));
    }
}
