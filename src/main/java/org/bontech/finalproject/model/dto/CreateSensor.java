package org.bontech.finalproject.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;


@Data
public class CreateSensor {

    @NotNull(message = "sensor name should not be null")
    private String sensorName;
    @NotNull(message = "silo id name should not be null")
    private Long siloId;
    @NotNull(message = "sensePeriod id name should not be null")
    private LocalTime sensePeriod;

}
