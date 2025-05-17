package com.f1app.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Track", schema = "dev")
public class TrackModel {

	@Column(name = "id")
	@Id 
 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	@NotNull
	private String name;

	@Column(name = "location")
	@NotNull
	private String location;

	@Column(name = "country")
	@NotNull
	private String country;

	@Column(name = "length_km")
	@NotNull
	private String lengthKm;

	@Column(name = "number_of_laps")
	@NotNull
	private int numberOfLaps;

	@Column(name = "total_distance_km")
	
	private String totalDistanceKm;

	@Column(name = "lap_record_time")
	
	private String lapRecordTime;

	@Column(name = "lap_record_driver")
	
	private String lapRecordDriver;

	@Column(name = "lap_record_year")
	
	private int lapRecordYear;

	@Column(name = "first_grand_prix_year")
	
	private int firstGrandPrixYear;

	@Column(name = "is_active")
	
	private boolean isActive;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLengthKm() {
		return lengthKm;
	}

	public void setLengthKm(String lengthKm) {
		this.lengthKm = lengthKm;
	}

	public int getNumberOfLaps() {
		return numberOfLaps;
	}

	public void setNumberOfLaps(int numberOfLaps) {
		this.numberOfLaps = numberOfLaps;
	}

	public String getTotalDistanceKm() {
		return totalDistanceKm;
	}

	public void setTotalDistanceKm(String totalDistanceKm) {
		this.totalDistanceKm = totalDistanceKm;
	}

	public String getLapRecordTime() {
		return lapRecordTime;
	}

	public void setLapRecordTime(String lapRecordTime) {
		this.lapRecordTime = lapRecordTime;
	}

	public String getLapRecordDriver() {
		return lapRecordDriver;
	}

	public void setLapRecordDriver(String lapRecordDriver) {
		this.lapRecordDriver = lapRecordDriver;
	}

	public int getLapRecordYear() {
		return lapRecordYear;
	}

	public void setLapRecordYear(int lapRecordYear) {
		this.lapRecordYear = lapRecordYear;
	}

	public int getFirstGrandPrixYear() {
		return firstGrandPrixYear;
	}

	public void setFirstGrandPrixYear(int firstGrandPrixYear) {
		this.firstGrandPrixYear = firstGrandPrixYear;
	}

	public boolean isIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public TrackModel() {}

	public TrackModel(long id, String name, String location, String country, String lengthKm, int numberOfLaps, String totalDistanceKm, String lapRecordTime, String lapRecordDriver, int lapRecordYear, int firstGrandPrixYear, boolean isActive) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.country = country;
		this.lengthKm = lengthKm;
		this.numberOfLaps = numberOfLaps;
		this.totalDistanceKm = totalDistanceKm;
		this.lapRecordTime = lapRecordTime;
		this.lapRecordDriver = lapRecordDriver;
		this.lapRecordYear = lapRecordYear;
		this.firstGrandPrixYear = firstGrandPrixYear;
		this.isActive = isActive;
	}

}