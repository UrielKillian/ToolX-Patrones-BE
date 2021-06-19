package com.jossticeteam.toolx.resource;

import com.jossticeteam.toolx.domain.model.AuditModel;
import lombok.Data;

@Data
public class QualificationResource extends AuditModel {
    private Long id;
    private Long userId;
    private Long userQualifiedId;
    private Double qualification;
}
