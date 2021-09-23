package org.bontech.finalproject.service.impl;

import java.util.List;
import java.util.Optional;

import org.bontech.finalproject.service.base.SiloService;
import org.bontech.finalproject.service.base.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bontech.finalproject.repositories.*;
import org.bontech.finalproject.model.*;
import org.bontech.finalproject.model.dto.*;

@Service
public class SiloServiceImpl implements SiloService{
	
	private final SiloRepository siloRepository;
	private final SensorService sensorService;
	private final ChangeOfSiloRepository changeOfSiloRepository;

	@Autowired
	public SiloServiceImpl(SiloRepository siloRepository, SensorService sensorService, ChangeOfSiloRepository changeOfSiloRepository){
		this.siloRepository = siloRepository;
		this.sensorService = sensorService;
		this.changeOfSiloRepository = changeOfSiloRepository;
	}

	@Override
	public Silo createSilo(CreateSilo createSilo){
		return siloRepository.save(Silo.builder().name(createSilo.getName())
				.maximumCapacity(createSilo.getMaximumCapacity()).build());
	}

	@Override
	public void updateSilo(UpdateSilo updateSilo){
		if (siloRepository.existsById(updateSilo.getId())){
			siloRepository.updateSilo(updateSilo.getMaximumCapacity(),updateSilo.getName(),updateSilo.getId());
		}else throw new RuntimeException("silo not found");
	}

	@Override
	public void deleteSilo(DeleteSilo deleteSilo){
		if (siloRepository.existsById(deleteSilo.getId())){
			siloRepository.deleteById(deleteSilo.getId());
		}else throw new RuntimeException("silo not found");
	}

	@Override 
	public Silo findSilo(Long siloId){
		Optional<Silo> byId = siloRepository.findById(siloId);
		if (byId.isPresent()){
			return byId.get();
		}else throw new RuntimeException("silo not found");
	}
	
	@Override
	public List<ChangesOfSilo> getHistory(Long siloId,Integer n){
		return changeOfSiloRepository.getHistory(siloId,n);
	}

	@Override 
	public Silo addOrHarvest(Long siloId){
		Optional<Silo> byId = siloRepository.findById(siloId);
		if(byId.isPresent()){
			Silo silo = byId.get();
			Long aLong = sensorService.estimateCurrentWeight(siloId); // if sense value different with silo.getCurrentWeight it save into changeOfSilo and senseHistory tables and if they have not difference just save into senseHistory table
			if (aLong < 0){ //harvest
				if (silo.getCurrentWeight() + aLong > 0){
					return silo;
				}else throw new RuntimeException("silo does not have enough balance");
			}else{ //add
				if (silo.getCurrentWeight() + aLong < silo.getMaximumCapacity()){
					return silo;
				}else throw new RuntimeException("silo does not have enough space");
			}
		}else throw new RuntimeException("silo not found");
	}

}
