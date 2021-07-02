package com.jossticeteam.toolx.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "jobs")
@Data
public class Job extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Double payment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public Job() {
    }

    public Job(String title, String description, Double payment, User user) {
        this.title = title;
        this.description = description;
        this.payment = payment;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Job setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Job setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Job setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPayment() {
        return payment;
    }

    public Job setPayment(Double payment) {
        this.payment = payment;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Job setUser(User user) {
        this.user = user;
        return this;
    }
}
