package com.f1app.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "races", schema = "dev")
public class RacesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "race_id")
	private long raceId;

	@Column(name = "race_name")
	private String raceName;

	@Column(name = "circuit_id")
	private long circuitId;

	@Column(name = "season")
	private int season;

	@Column(name = "date")
	private LocalDateTime date;

	@Column(name = "time")
	private String time;

	@Column(name = "laps")
	private int laps;

	@Column(name = "winner_id")
	private long winnerId;

	@Column(name = "status")
	private String status;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRaceId() {
		return raceId;
	}

	public void setRaceId(long raceId) {
		this.raceId = raceId;
	}

	public String getRaceName() {
		return raceName;
	}

	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}

	public long getCircuitId() {
		return circuitId;
	}

	public void setCircuitId(long circuitId) {
		this.circuitId = circuitId;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getLaps() {
		return laps;
	}

	public void setLaps(int laps) {
		this.laps = laps;
	}

	public long getWinnerId() {
		return winnerId;
	}

	public void setWinnerId(long winnerId) {
		this.winnerId = winnerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public RacesModel() {}

	public RacesModel(long id, long raceId, String raceName, long circuitId, int season, LocalDateTime date, String time, int laps, long winnerId, String status) {
		this.id = id;
		this.raceId = raceId;
		this.raceName = raceName;
		this.circuitId = circuitId;
		this.season = season;
		this.date = date;
		this.time = time;
		this.laps = laps;
		this.winnerId = winnerId;
		this.status = status;
	}

}