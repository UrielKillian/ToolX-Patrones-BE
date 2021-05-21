package com.jossticeteam.toolx.resource;

import com.jossticeteam.toolx.domain.model.AuditModel;
import lombok.Data;

@Data
public class SaveUserResource extends AuditModel {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
}
