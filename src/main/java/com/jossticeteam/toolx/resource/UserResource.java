package com.jossticeteam.toolx.resource;

import com.jossticeteam.toolx.domain.model.AuditModel;
import lombok.Data;

@Data
public class UserResource extends AuditModel {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
}
