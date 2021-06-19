package com.jossticeteam.toolx.controller;

import com.jossticeteam.toolx.domain.model.Comment;
import com.jossticeteam.toolx.domain.service.CommentService;
import com.jossticeteam.toolx.resource.CommentResource;
import com.jossticeteam.toolx.resource.SaveCommentResource;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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

@Tag(name = "comments", description = "Comments desc")
@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CommentService commentService;

    @PostMapping("/users/{userId}/userCommented/{userCommentedId}/comments")
    public CommentResource createComment(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "userCommentedId") Long postId,
            @Valid @RequestBody SaveCommentResource resource) {
        return convertToResource(commentService.createComment(userId, postId, convertToEntity(resource)));
    }

    @GetMapping("/users/{userId}/comments")
    public Page<CommentResource> getAllCommentsByUserId(
            @PathVariable(name = "userId") Long userId,
            Pageable pageable) {
        Page<Comment> commentPage = commentService.getAllCommentsByUserId(userId, pageable);
        List<CommentResource> resources = commentPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/comments/{commentId}")
    public CommentResource getCommentById(
            @PathVariable(name = "commentId") Long commentId) {
        return convertToResource(commentService.getCommentById(commentId));
    }

    @PutMapping("/comments/{commentId}")
    public CommentResource updateComment(
            @PathVariable(name = "commentId") Long commentId,
            @Valid @RequestBody SaveCommentResource resource) {
        return convertToResource(commentService.updateComment(commentId, convertToEntity(resource)));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable(name = "commentId") Long commentId) {
        return commentService.deleteComment(commentId);
    }

    private Comment convertToEntity(SaveCommentResource resource) {
        return mapper.map(resource, Comment.class);
    }

    private CommentResource convertToResource(Comment entity) {
        return mapper.map(entity, CommentResource.class);
    }

}
