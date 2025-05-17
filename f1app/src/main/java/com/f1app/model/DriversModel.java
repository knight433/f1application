package com.f1app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "drivers", schema = "dev")
public class DriversModel {

    @Column(name = "id")
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "origin")
    private String origin;

    @Column(name = "team")
    private String team;

    @Column(name = "number")
    private int number;

    @Column(name = "world_championships")
    private Integer worldChampionships; // Nullable field

    @Column(name = "debut_year")
    private Integer debutYear; // Nullable field

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "races_entered")
    private Integer racesEntered; // Nullable field

    @Column(name = "races_started")
    private Integer racesStarted; // Nullable field

    @Column(name = "wins")
    private Integer wins; // Nullable field

    @Column(name = "podiums")
    private Integer podiums; // Nullable field

    @Column(name = "pole_positions")
    private Integer polePositions; // Nullable field

    @Column(name = "fastest_laps")
    private Integer fastestLaps; // Nullable field

    @Column(name = "career_points")
    private String careerPoints;

    @Column(name = "last_race")
    private String lastRace;

    public DriversModel() {}

    public DriversModel(long id, String name, int age, String origin, String team, int number, Integer worldChampionships, Integer debutYear, Boolean isActive, Integer racesEntered, Integer racesStarted, Integer wins, Integer podiums, Integer polePositions, Integer fastestLaps, String careerPoints, String lastRace) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.origin = origin;
        this.team = team;
        this.number = number;
        this.worldChampionships = worldChampionships;
        this.debutYear = debutYear;
        this.isActive = isActive;
        this.racesEntered = racesEntered;
        this.racesStarted = racesStarted;
        this.wins = wins;
        this.podiums = podiums;
        this.polePositions = polePositions;
        this.fastestLaps = fastestLaps;
        this.careerPoints = careerPoints;
        this.lastRace = lastRace;
    }

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Integer getWorldChampionships() {
		return worldChampionships;
	}

	public void setWorldChampionships(Integer worldChampionships) {
		this.worldChampionships = worldChampionships;
	}

	public Integer getDebutYear() {
		return debutYear;
	}

	public void setDebutYear(Integer debutYear) {
		this.debutYear = debutYear;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getRacesEntered() {
		return racesEntered;
	}

	public void setRacesEntered(Integer racesEntered) {
		this.racesEntered = racesEntered;
	}

	public Integer getRacesStarted() {
		return racesStarted;
	}

	public void setRacesStarted(Integer racesStarted) {
		this.racesStarted = racesStarted;
	}

	public Integer getWins() {
		return wins;
	}

	public void setWins(Integer wins) {
		this.wins = wins;
	}

	public Integer getPodiums() {
		return podiums;
	}

	public void setPodiums(Integer podiums) {
		this.podiums = podiums;
	}

	public Integer getPolePositions() {
		return polePositions;
	}

	public void setPolePositions(Integer polePositions) {
		this.polePositions = polePositions;
	}

	public Integer getFastestLaps() {
		return fastestLaps;
	}

	public void setFastestLaps(Integer fastestLaps) {
		this.fastestLaps = fastestLaps;
	}

	public String getCareerPoints() {
		return careerPoints;
	}

	public void setCareerPoints(String careerPoints) {
		this.careerPoints = careerPoints;
	}

	public String getLastRace() {
		return lastRace;
	}

	public void setLastRace(String lastRace) {
		this.lastRace = lastRace;
	}

	@Override
	public String toString() {
		return "DriversModel [id=" + id + ", name=" + name + ", age=" + age + ", origin=" + origin + ", team=" + team
				+ ", number=" + number + ", worldChampionships=" + worldChampionships + ", debutYear=" + debutYear
				+ ", isActive=" + isActive + ", racesEntered=" + racesEntered + ", racesStarted=" + racesStarted
				+ ", wins=" + wins + ", podiums=" + podiums + ", polePositions=" + polePositions + ", fastestLaps="
				+ fastestLaps + ", careerPoints=" + careerPoints + ", lastRace=" + lastRace + "]";
	}

    
}
