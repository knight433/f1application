package com.f1app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.f1app.service.RacesService;
import com.f1app.CrudOperationException;
import com.f1app.CrudValidationException;
import com.f1app.model.RacesModel;

@RestController
@RequestMapping("/f1app/api/v1/races")
public class RacesController {
    @Autowired
    private RacesService racesService;
    private Logger log = LogManager.getLogger(getClass());

    @GetMapping("/get/by/id/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        if (id <= 0) return ResponseEntity.badRequest().body("Invalid ID");
        try {
            return ResponseEntity.ok(racesService.getById(id));
        }
        catch (CrudValidationException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (CrudOperationException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNew(@RequestBody RacesModel racesmodel) {
        if (racesmodel == null)
            return ResponseEntity.badRequest().body("Invalid model");
        try {
            return ResponseEntity.ok(racesService.addNewRacesModel(racesmodel));
        }
        catch (CrudValidationException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (CrudOperationException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(racesService.getAll());
        }
        catch (CrudOperationException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}