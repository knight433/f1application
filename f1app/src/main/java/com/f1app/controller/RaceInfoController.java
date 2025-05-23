package com.f1app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.f1app.service.OpenF1Service;
import com.f1app.model.RaceInfoPlayer;

@RestController
@RequestMapping("raceinfo/v1")
public class RaceInfoController {

    @Autowired
    private OpenF1Service openF1Service;

    @GetMapping("/get/drivers/data/{sessionId}")
    public ResponseEntity<?> DriversData(@PathVariable int sessionId)
    {
        try {
            List<RaceInfoPlayer> driversInfo = openF1Service.getLivePositionsForAllDrivers(sessionId);
            return ResponseEntity.status(HttpStatus.OK).body(driversInfo);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
