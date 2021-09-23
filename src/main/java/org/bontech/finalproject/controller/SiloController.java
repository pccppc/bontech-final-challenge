package org.bontech.finalproject.controller;

import org.bontech.finalproject.model.ChangesOfSilo;
import org.bontech.finalproject.model.Silo;
import org.bontech.finalproject.model.dto.CreateSilo;
import org.bontech.finalproject.model.dto.DeleteSilo;
import org.bontech.finalproject.model.dto.UpdateSilo;
import org.bontech.finalproject.model.response.FailureBody;
import org.bontech.finalproject.model.response.SuccessBody;
import org.bontech.finalproject.service.base.SiloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/silo")
public class SiloController {

    private final SiloService service;

    @Autowired
    public SiloController(SiloService siloService) {
        this.service = siloService;
    }


    @PostMapping(path = "/create")
    public ResponseEntity<Object> createSilo(@RequestBody CreateSilo s){
        Silo silo = service.createSilo(s);
        return ResponseEntity.ok(new SuccessBody<>(Map.of("message","silo was created successfully",
                "data",silo)));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Object> updateSilo(@RequestBody UpdateSilo s){
        try {
            service.updateSilo(s);
            return ResponseEntity.ok(new SuccessBody<>(Map.of("message", "silo was updated successfully")));
        }catch (RuntimeException e){ //it's better if we use custom exceptions
            return ResponseEntity.status(404).body(new FailureBody<>(Map.of("message",e.getMessage())));
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Object> deleteSilo(@RequestBody DeleteSilo s){
        try {
            service.deleteSilo(s);
            return ResponseEntity.ok(new SuccessBody<>(Map.of("message", "silo was removed successfully")));
        }catch (RuntimeException e){ //it's better if we use custom exceptions
            return ResponseEntity.status(404).body(new FailureBody<>(Map.of("message",e.getMessage())));
        }
    }

    @GetMapping(path = "/get")
    public ResponseEntity<Object> findSilo(@RequestParam Long siloId){
        try {
            Silo silo = service.findSilo(siloId);
            return ResponseEntity.ok(new SuccessBody<>(Map.of("data",silo)));
        }catch (RuntimeException e){ //it's better if we use custom exceptions
            return ResponseEntity.status(404).body(new FailureBody<>(Map.of("message",e.getMessage())));
        }
    }

    @GetMapping(path = "/history")
    public ResponseEntity<Object> getHistory(@RequestParam Long siloId,@RequestParam Integer n){
        try {
            List<ChangesOfSilo> history = service.getHistory(siloId, n);
            return ResponseEntity.ok(new SuccessBody<>(Map.of("data",history)));
        }catch (RuntimeException e){ //it's better if we use custom exceptions
            return ResponseEntity.status(404).body(new FailureBody<>(Map.of("message",e.getMessage())));
        }
    }

    @PutMapping(path = "/addOrHarvest")
    public ResponseEntity<Object> addOrHarvest(@RequestParam Long siloId){
        try {
            Silo silo = service.addOrHarvest(siloId);
            return ResponseEntity.ok(new SuccessBody<>(Map.of("message","done successfully","data",silo)));
        }catch (RuntimeException e){ //it's better if we use custom exceptions
            return ResponseEntity.status(404).body(new FailureBody<>(Map.of("message",e.getMessage())));
        }
    }


}
