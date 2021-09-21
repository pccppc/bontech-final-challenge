package org.bontech.finalproject.service.base;

import org.bontech.finalproject.model.Sensor;
import org.bontech.finalproject.model.dto.CreateSensor;
import org.bontech.finalproject.model.dto.DeleteSensor;
import org.bontech.finalproject.model.dto.UpdateSensor;

public interface SensorService {

    /**
     * get info from sensor. estimate current Weight and store this info into SenseHistory table
     * @param sensorId for find a selected sensor
     * @return Long estimated amount
     */
    Long estimateCurrentWeight(Long sensorId);

    Sensor createSensor(CreateSensor createSensor);

    void deleteSensor(DeleteSensor deleteSensor);

    Sensor updateSensor(UpdateSensor updateSensor);

}
