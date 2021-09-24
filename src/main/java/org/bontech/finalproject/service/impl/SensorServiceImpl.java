package org.bontech.finalproject.service.impl;

import org.bontech.finalproject.model.*;
import org.bontech.finalproject.model.dto.CreateSensor;
import org.bontech.finalproject.model.dto.DeleteSensor;
import org.bontech.finalproject.model.dto.UpdateSensor;
import org.bontech.finalproject.repositories.*;
import org.bontech.finalproject.service.base.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.Random;

@Service
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;
    private final SenseHistoryRepository senseHistoryRepository;
    private final SiloRepository siloRepository;
    private final ChangeOfSiloRepository changeOfSiloRepository;
    private final ScheduledSenseRepository scheduledSenseRepository;

    @Autowired
    public SensorServiceImpl(SensorRepository sensorRepository, SenseHistoryRepository senseHistoryRepository, SiloRepository siloRepository, ChangeOfSiloRepository changeOfSiloRepository, ScheduledSenseRepository scheduledSenseRepository) {
        this.sensorRepository = sensorRepository;
        this.senseHistoryRepository = senseHistoryRepository;
        this.siloRepository = siloRepository;
        this.changeOfSiloRepository = changeOfSiloRepository;
        this.scheduledSenseRepository = scheduledSenseRepository;
    }

    @Override
    @Transactional
    public Long estimateCurrentWeight(Long sensorId) {
        Optional<Sensor> byId = sensorRepository.findById(sensorId);
        if (byId.isPresent()){
            Sensor sensor = byId.get();
            Silo silo = sensor.getSilo();
            return getSensorResult(sensor,silo);
        }throw new RuntimeException("sensor not found");
    }

    @Override
    @Transactional
    public Long estimateCurrentWeight(Long sensorId,Silo silo) {
        Optional<Sensor> byId = sensorRepository.findById(sensorId);
        if (byId.isPresent()){
            Sensor sensor = byId.get();
            return getSensorResult(sensor,silo);
        }throw new RuntimeException("sensor not found");
    }

    public Long getSensorResult(Sensor sensor,Silo silo){
        //using http request or socket for connect to sensor with{id} and get info
        //for now I use Random Long
        sensor.setAmount(new Random().nextLong());
        Long result = sensorRepository.save(sensor).getAmount();
        SenseHistory senseHistory = SenseHistory.builder().sensor(sensor).amount(result).build();
        senseHistoryRepository.save(senseHistory);
        if (!result.equals(silo.getCurrentWeight())) {
            ChangesOfSilo changesOfSilo = ChangesOfSilo.builder().silo(silo).amount(result - silo.getCurrentWeight()).build();
            silo.setCurrentWeight(result);
            siloRepository.save(silo);
            changeOfSiloRepository.save(changesOfSilo);
        }
        return result;
    }

    @Override
    @Transactional
    public Sensor createSensor(CreateSensor createSensor) {
        Optional<Silo> byId = siloRepository.findById(createSensor.getSiloId());
        if (byId.isPresent()){
            Silo silo = byId.get();
            Sensor sensor = Sensor.builder().name(createSensor.getSensorName()).silo(silo).build();
            scheduledSenseRepository.save(ScheduledSense.builder().sensor(sensor).period(createSensor.getSensePeriod())
                    .build());
            return sensorRepository.save(sensor);
        }else throw new RuntimeException("silo not found");
    }

    @Override
    public void deleteSensor(DeleteSensor deleteSensor) {
        if (sensorRepository.existsById(deleteSensor.getSensorId())){
            sensorRepository.deleteById(deleteSensor.getSensorId());
        }else throw new RuntimeException("sensor not found");
    }

    @Override
    public Sensor updateSensor(UpdateSensor updateSensor) {
        Optional<Sensor> byId = sensorRepository.findById(updateSensor.getSensorId());
        if (byId.isPresent()){
            Sensor sensor = byId.get();
            sensor.setName(updateSensor.getName());
            return sensorRepository.save(sensor);
        }else throw new RuntimeException("sensor not found");
    }




}
