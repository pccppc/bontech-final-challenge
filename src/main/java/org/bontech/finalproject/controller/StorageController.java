package org.bontech.finalproject.controller;

import org.bontech.finalproject.model.Silo;
import org.bontech.finalproject.model.Storage;
import org.bontech.finalproject.model.dto.CreateStorage;
import org.bontech.finalproject.model.dto.DeleteStorage;
import org.bontech.finalproject.model.dto.UpdateStorage;
import org.bontech.finalproject.model.response.FailureBody;
import org.bontech.finalproject.model.response.SuccessBody;
import org.bontech.finalproject.service.base.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/storage")
public class StorageController {

    private final StorageService service;

    @Autowired
    public StorageController(StorageService service) {
        this.service = service;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Object> createStorage(@RequestBody CreateStorage s){
        Storage storage = service.creteStorage(s);
        return ResponseEntity.ok(new SuccessBody<>(Map.of("message","storage was created successfully",
                "data",storage)));
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Object> deleteStorage(@RequestBody DeleteStorage s){
        try{
            service.deleteStorage(s);
            return ResponseEntity.ok(new SuccessBody<>(Map.of("message", "storage was removed successfully")));
        }catch (RuntimeException e){ //it's better if we use custom exceptions
            return ResponseEntity.status(404).body(new FailureBody<>(Map.of("message",e.getMessage())));
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Object> updateStorage(@RequestBody UpdateStorage s){
        try{
            service.updateStorage(s);
            return ResponseEntity.ok(new SuccessBody<>(Map.of("message", "storage was updated successfully")));
        }catch (RuntimeException e){ //it's better if we use custom exceptions
            return ResponseEntity.status(404).body(new FailureBody<>(Map.of("message",e.getMessage())));
        }
    }

    @GetMapping(path = "/get")
    public ResponseEntity<Object> getStorage(@RequestParam Long storageId){
        try{
            Storage storage = service.findStorage(storageId);
            return ResponseEntity.ok(new SuccessBody<>(Map.of("data",storage)));
        }catch (RuntimeException e){ //it's better if we use custom exceptions
            return ResponseEntity.status(404).body(new FailureBody<>(Map.of("message",e.getMessage())));
        }
    }

    @GetMapping(path = "/currentWeightStored")
    public ResponseEntity<Object> getCurrentWrightStored(@RequestParam Long storageId){
        try{
            Long currentStored = service.getCurrentStored(storageId);
            return ResponseEntity.ok(new SuccessBody<>(Map.of("data",currentStored)));
        }catch (RuntimeException e){ //it's better if we use custom exceptions
            return ResponseEntity.status(404).body(new FailureBody<>(Map.of("message",e.getMessage())));
        }
    }

    @GetMapping(path = "/silos")
    public ResponseEntity<Object> getAllSilo(@RequestParam Long storageId){
        try{
            List<Silo> allSilo = service.getAllSilo(storageId);
            return ResponseEntity.ok(new SuccessBody<>(Map.of("data",allSilo)));
        }catch (RuntimeException e){ //it's better if we use custom exceptions
            return ResponseEntity.status(404).body(new FailureBody<>(Map.of("message",e.getMessage())));
        }
    }

}
