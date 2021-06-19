package com.jossticeteam.toolx.resource;

import com.jossticeteam.toolx.domain.model.AuditModel;
import lombok.Data;

@Data
public class CommentResource extends AuditModel {
    private Long id;
    private Long userId;
    private Long userCommentedId;
    private String content;
}
