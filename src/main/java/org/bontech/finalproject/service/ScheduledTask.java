package org.bontech.finalproject.service;

import lombok.extern.slf4j.Slf4j;
import org.bontech.finalproject.service.base.SensorService;

@Slf4j
public class ScheduledTask implements Runnable{

    private SensorService service;
    private Long sensorId;

    public ScheduledTask(SensorService service,Long sensorId) {
        this.service = service;
        this.sensorId = sensorId;
    }

    @Override
    public void run() {
        Long amount = service.estimateCurrentWeight(this.sensorId);
        log.info("scheduled sense was done for sensor with id: {} , current estimated weight: {}" , sensorId,amount);
    }
 }
