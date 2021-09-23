package org.bontech.finalproject.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateSensor {


    @NotNull(message = "sensor id should not be null")
    private Long sensorId;
    @NotNull(message = "name should not be null")
    private String name;

}
