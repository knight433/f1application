package com.f1app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Mono;

@Service
public class OpenF1Service {

    private final WebClient webClient;

    public OpenF1Service(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openf1.org/v1").build();
    }

    public Mono<String> getLapData(int sessionKey, int driverNumber, int lapNumber) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/laps")
                        .queryParam("session_key", sessionKey)
                        .queryParam("driver_number", driverNumber)
                        .queryParam("lap_number", lapNumber)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<Integer> getLatestSessionKey() {
    return webClient.get()
            .uri(uriBuilder -> uriBuilder
                    .path("/sessions")
                    .queryParam("limit", 1)
                    .queryParam("order_by", "-date_start")
                    .build())
            .retrieve()
            .bodyToFlux(JsonNode.class)
            .map(jsonNode -> jsonNode.get("session_key").asInt())
            .next() // get the first one if exists
            .switchIfEmpty(Mono.error(new RuntimeException("No session found")));
}

}