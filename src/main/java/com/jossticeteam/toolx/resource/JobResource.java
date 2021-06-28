package com.jossticeteam.toolx.resource;

import com.jossticeteam.toolx.domain.model.AuditModel;
import com.sun.istack.NotNull;

public class JobResource extends AuditModel {


    private Long id;
    private String title;
    private String description;
    private Double payment;


    public Long getId() {
        return id;
    }

    public JobResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public JobResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public JobResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPayment() {
        return payment;
    }

    public JobResource setPayment(Double payment) {
        this.payment = payment;
        return this;
    }
}
