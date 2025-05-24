package com.f1app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.f1app.model.RaceInfoPlayer;
import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Mono;

@Service
public class OpenF1Service {

        private final WebClient webClient;

        private RaceInfoPlayer.DRS parseDrsStatus(String status, boolean inPit) {
                if (inPit)
                        return RaceInfoPlayer.DRS.PITS;
                return switch (status) {
                        case "1" -> RaceInfoPlayer.DRS.DRS_OFF;
                        case "2" -> RaceInfoPlayer.DRS.DRS_AVAILABLE;
                        case "3" -> RaceInfoPlayer.DRS.DRS_ON;
                        default -> RaceInfoPlayer.DRS.DRS_OFF;
                };
        }

        private RaceInfoPlayer.TyreType parseTyre(String tyre) {
                return switch (tyre) {
                        case "SOFT" -> RaceInfoPlayer.TyreType.SOFT;
                        case "MEDIUM" -> RaceInfoPlayer.TyreType.MEDIUMS;
                        case "HARD" -> RaceInfoPlayer.TyreType.HARDS;
                        case "INTERMEDIATE" -> RaceInfoPlayer.TyreType.INTERS;
                        case "WET" -> RaceInfoPlayer.TyreType.WETS;
                        default -> RaceInfoPlayer.TyreType.UNKNOWN;
                };
        }

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
                                                .queryParam("session_key", "latest")
                                                .build())
                                .retrieve()
                                .bodyToFlux(JsonNode.class)
                                .map(jsonNode -> jsonNode.get("session_key").asInt())
                                .next()
                                .switchIfEmpty(Mono.error(new RuntimeException("No session found")));
        }

        public List<RaceInfoPlayer> getLivePositionsForAllDrivers(int sessionKey) {
                // Fetch driver positions
                List<JsonNode> positionData = webClient.get()
                                .uri(uriBuilder -> uriBuilder
                                                .path("/position")
                                                .queryParam("session_key", sessionKey)
                                                .build())
                                .retrieve()
                                .bodyToFlux(JsonNode.class)
                                .collectList()
                                .block();

                // Fetch car data (DRS, Tyre info, etc.)
                List<JsonNode> carData = webClient.get()
                                .uri(uriBuilder -> uriBuilder
                                                .path("/car_data")
                                                .queryParam("session_key", sessionKey)
                                                .build())
                                .retrieve()
                                .bodyToFlux(JsonNode.class)
                                .collectList()
                                .block();

                Map<Integer, JsonNode> latestCarDataPerDriver = new HashMap<>();
                for (JsonNode node : carData) {
                        int driverNumber = node.get("driver_number").asInt();
                        latestCarDataPerDriver.put(driverNumber, node); // Replace with latest entry
                }

                // Merge both and construct RaceInfoPlayer
                List<RaceInfoPlayer> result = new ArrayList<>();
                for (JsonNode pos : positionData) {
                        int driverNumber = pos.get("driver_number").asInt();
                        RaceInfoPlayer player = new RaceInfoPlayer();

                        player.setDriverNumber(driverNumber);
                        player.setShortName(pos.get("name_abbreviated").asText());
                        player.setGapToLeader((float) pos.get("gap_to_leader").asDouble());
                        player.setGapToCarInFront((float) pos.get("gap_to_car_ahead").asDouble());

                        JsonNode carNode = latestCarDataPerDriver.get(driverNumber);
                        if (carNode != null) {
                                String drs = carNode.path("drs_status").asText("");
                                player.setDrsStatus(parseDrsStatus(drs, carNode.path("in_pit").asBoolean(false)));

                                String tyre = carNode.path("tyre_compound").asText("UNKNOWN").toUpperCase();
                                player.setTyreType(parseTyre(tyre));
                        }

                        result.add(player);
                }

                return result;
        }

}