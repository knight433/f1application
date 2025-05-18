package com.f1app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.f1app.service.OpenF1Service;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("openf1/api")
public class OpenF1Controller {

    private final OpenF1Service openF1Service;

    public OpenF1Controller(OpenF1Service openF1Service) {
        this.openF1Service = openF1Service;
    }

    @GetMapping("/get/latest/session")
    public ResponseEntity<?> getLatestSession() {
        try {
            Integer sessionKey = openF1Service.getLatestSessionKey().block();
            return ResponseEntity.ok(sessionKey);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch latest session: " + e.getMessage());
        }
    }

    @GetMapping("/laps")
    public Mono<String> getLapData(@RequestParam int sessionKey,
            @RequestParam int driverNumber,
            @RequestParam int lapNumber) {
        return openF1Service.getLapData(sessionKey, driverNumber, lapNumber);
    }
}
