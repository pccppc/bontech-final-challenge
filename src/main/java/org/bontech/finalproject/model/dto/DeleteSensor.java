package org.bontech.finalproject.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeleteSensor {

    @NotNull(message = "sensor Id should not be null")
    private Long sensorId;

}
