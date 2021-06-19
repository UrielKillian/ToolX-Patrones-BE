package com.jossticeteam.toolx.resource;

import com.jossticeteam.toolx.domain.model.AuditModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SaveCommentResource extends AuditModel {
    @NotNull
    @NotBlank
    private String content;
}
