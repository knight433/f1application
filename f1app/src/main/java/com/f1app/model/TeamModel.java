package com.f1app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name = "Team", schema = "dev")
public class TeamModel {

	@Column(name = "id")
	@Id 
 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	@NotNull
	private String name;

	@Column(name = "base_location")
	@NotNull
	private String baseLocation;

	@Column(name = "country")
	@NotNull
	private String country;

	@Column(name = "team_principal")
	
	private String teamPrincipal;

	@Column(name = "power_unit_supplier")
	
	private String powerUnitSupplier;

	@Column(name = "first_entry_year")
	
	private int firstEntryYear;

	@Column(name = "championships_won")
	
	private int championshipsWon;

	@Column(name = "pole_positions")
	
	private int polePositions;

	@Column(name = "wins")
	
	private int wins;

	@Column(name = "fastest_laps")
	
	private int fastestLaps;

	@Column(name = "podiums")
	
	private int podiums;

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

	public String getBaseLocation() {
		return baseLocation;
	}

	public void setBaseLocation(String baseLocation) {
		this.baseLocation = baseLocation;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTeamPrincipal() {
		return teamPrincipal;
	}

	public void setTeamPrincipal(String teamPrincipal) {
		this.teamPrincipal = teamPrincipal;
	}

	public String getPowerUnitSupplier() {
		return powerUnitSupplier;
	}

	public void setPowerUnitSupplier(String powerUnitSupplier) {
		this.powerUnitSupplier = powerUnitSupplier;
	}

	public int getFirstEntryYear() {
		return firstEntryYear;
	}

	public void setFirstEntryYear(int firstEntryYear) {
		this.firstEntryYear = firstEntryYear;
	}

	public int getChampionshipsWon() {
		return championshipsWon;
	}

	public void setChampionshipsWon(int championshipsWon) {
		this.championshipsWon = championshipsWon;
	}

	public int getPolePositions() {
		return polePositions;
	}

	public void setPolePositions(int polePositions) {
		this.polePositions = polePositions;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getFastestLaps() {
		return fastestLaps;
	}

	public void setFastestLaps(int fastestLaps) {
		this.fastestLaps = fastestLaps;
	}

	public int getPodiums() {
		return podiums;
	}

	public void setPodiums(int podiums) {
		this.podiums = podiums;
	}

	public boolean isIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public TeamModel() {}

	public TeamModel(long id, String name, String baseLocation, String country, String teamPrincipal, String powerUnitSupplier, int firstEntryYear, int championshipsWon, int polePositions, int wins, int fastestLaps, int podiums, boolean isActive) {
		this.id = id;
		this.name = name;
		this.baseLocation = baseLocation;
		this.country = country;
		this.teamPrincipal = teamPrincipal;
		this.powerUnitSupplier = powerUnitSupplier;
		this.firstEntryYear = firstEntryYear;
		this.championshipsWon = championshipsWon;
		this.polePositions = polePositions;
		this.wins = wins;
		this.fastestLaps = fastestLaps;
		this.podiums = podiums;
		this.isActive = isActive;
	}

}