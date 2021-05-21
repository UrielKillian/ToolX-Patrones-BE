package com.jossticeteam.toolx.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/*
user, username, email, password, subscription, description, phone, registrationDate, notification, facebook_link,
instagram, twitter, imageUrl, direction.

Registration Date, lastUpdate -> AuditModel
 */

@Entity
@Table(name = "users")
@Data
public class User extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 15)
    @NaturalId
    @Column(unique = true)
    private String username;

    @NotNull
    @NotBlank
    @Size(max = 15, min = 5)
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 25)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(max = 25)
    private String lastName;

    @NotNull
    @NotBlank
    @Size(max = 25)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 25)
    private String telephone;

}
