package org.bontech.finalproject.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class CreateSensor {

    @NotNull(message = "sensor name should not be null")
    private String sensorName;
    @NotNull(message = "silo id name should not be null")
    private Long siloId;

}
