package com.jossticeteam.toolx.resource;

import com.sun.istack.NotNull;

public class SaveJobResource {

    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private Double payment;

    public String getTitle() {
        return title;
    }

    public SaveJobResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveJobResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPayment() {
        return payment;
    }

    public SaveJobResource setPayment(Double payment) {
        this.payment = payment;
        return this;
    }
}
