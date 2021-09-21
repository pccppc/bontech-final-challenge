package org.bontech.finalproject.service.base;

public interface SensorService {

    /**
     * get info from sensor. estimate current Weight and store this info into SenseHistory table
     * @param sensorId for find a selected sensor
     * @return Long estimated amount
     */
    Long estimateCurrentWeight(Long sensorId);

}
