package org.bontech.finalproject.service.impl;

import org.bontech.finalproject.model.ChangesOfSilo;
import org.bontech.finalproject.model.SenseHistory;
import org.bontech.finalproject.model.Sensor;
import org.bontech.finalproject.model.Silo;
import org.bontech.finalproject.model.dto.CreateSensor;
import org.bontech.finalproject.model.dto.DeleteSensor;
import org.bontech.finalproject.model.dto.UpdateSensor;
import org.bontech.finalproject.repositories.ChangeOfSiloRepository;
import org.bontech.finalproject.repositories.SenseHistoryRepository;
import org.bontech.finalproject.repositories.SensorRepository;
import org.bontech.finalproject.repositories.SiloRepository;
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

    @Autowired
    public SensorServiceImpl(SensorRepository sensorRepository, SenseHistoryRepository senseHistoryRepository, SiloRepository siloRepository, ChangeOfSiloRepository changeOfSiloRepository) {
        this.sensorRepository = sensorRepository;
        this.senseHistoryRepository = senseHistoryRepository;
        this.siloRepository = siloRepository;
        this.changeOfSiloRepository = changeOfSiloRepository;
    }

    @Override
    @Transactional
    public Long estimateCurrentWeight(Long siloId) {
        Optional<Silo> byId = siloRepository.findById(siloId);
        if (byId.isPresent()){
            Silo silo = byId.get();
            Sensor sensor = silo.getSensor();
            Long result = getSensorResult(sensor);
            SenseHistory senseHistory = SenseHistory.builder().sensor(sensor).amount(result).build();
            senseHistoryRepository.save(senseHistory);
            if (!result.equals(silo.getCurrentWeight())) {
                ChangesOfSilo changesOfSilo = ChangesOfSilo.builder().silo(silo).amount(result - silo.getCurrentWeight()).build();
                silo.setCurrentWeight(result);
                siloRepository.save(silo);
                changeOfSiloRepository.save(changesOfSilo);
            }
            return result;
        }throw new RuntimeException("silo not found");
    }

    public Long getSensorResult(Sensor sensor){
        //using http request or socket for connect to sensor with{id} and get info
        //for now I use Random Long
        sensor.setAmount(new Random().nextLong());
        return sensorRepository.save(sensor).getAmount();
    }

    @Override
    public Sensor createSensor(CreateSensor createSensor) {
        Optional<Silo> byId = siloRepository.findById(createSensor.getSiloId());
        if (byId.isPresent()){
            Silo silo = byId.get();
            Sensor sensor = Sensor.builder().name(createSensor.getSensorName()).silo(silo).build();
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
