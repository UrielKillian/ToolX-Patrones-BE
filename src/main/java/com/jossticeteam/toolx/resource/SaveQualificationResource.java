package com.jossticeteam.toolx.resource;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SaveQualificationResource {
    @NotNull
    private Double qualification;
}
