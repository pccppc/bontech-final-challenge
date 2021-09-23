package org.bontech.finalproject.service.impl;

import org.bontech.finalproject.model.Silo;
import org.bontech.finalproject.model.Storage;
import org.bontech.finalproject.model.dto.CreateStorage;
import org.bontech.finalproject.model.dto.DeleteStorage;
import org.bontech.finalproject.model.dto.UpdateStorage;
import org.bontech.finalproject.repositories.SiloRepository;
import org.bontech.finalproject.repositories.StorageRepository;
import org.bontech.finalproject.service.base.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {

    private final StorageRepository storageRepository;
    private final SiloRepository siloRepository;

    @Autowired
    public StorageServiceImpl(StorageRepository storageRepository, SiloRepository siloRepository) {
        this.storageRepository = storageRepository;
        this.siloRepository = siloRepository;
    }

    @Override
    public Storage creteStorage(CreateStorage createStorage) {
        return storageRepository.save(Storage.builder().name(createStorage.getName()).build());
    }

    @Override
    public void deleteStorage(DeleteStorage deleteStorage) {
        if (storageRepository.existsById(deleteStorage.getId())){
            storageRepository.deleteById(deleteStorage.getId());
        }else throw new RuntimeException("storage not found");
    }

    @Override
    public void updateStorage(UpdateStorage updateStorage) {
        if (storageRepository.existsById(updateStorage.getId())){
            storageRepository.updateStorage(updateStorage.getName(),updateStorage.getId());
        }else throw new RuntimeException("storage not found");
    }

    @Override
    public Storage findStorage(Long storageId) {
        Optional<Storage> byId = storageRepository.findById(storageId);
        if (byId.isPresent()){
            return byId.get();
        }else throw new RuntimeException("storage not found");
    }

    @Override
    public Long getCurrentStored(Long storageId) {
        if (storageRepository.existsById(storageId))
            return siloRepository.sumOfAllCurrentWeightWithStorageId(storageId);
        else throw new RuntimeException("storage not found");
    }

    @Override
    public List<Silo> getAllSilo(Long storageId) {
        if (storageRepository.existsById(storageId))
            return siloRepository.findAllByStorageId(storageId);
        else throw new RuntimeException("storage not found");
    }
}
