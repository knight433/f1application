package com.f1app.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedule", schema = "dev")
public class ScheduleModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "race_id", nullable = false)
	private int raceId;

	@Column(name = "race_name", length = 64, nullable = false)
	private String raceName;

	@Column(name = "circut_name", length = 64, nullable = false)
	private String circutName;

	@Column(name = "schedule_date", nullable = false)
	private java.time.LocalDateTime scheduleDate;

	@Column(name = "type_of_weekend", length = 8, nullable = false)
	private String typeOfWeekend;

	@Column(name = "session_one_time", nullable = false)
	private java.time.LocalDateTime sessionOneTime;

	@Column(name = "session_two_time", nullable = false)
	private java.time.LocalDateTime sessionTwoTime;

	@Column(name = "session_three_time", nullable = false)
	private java.time.LocalDateTime sessionThreeTime;

	@Column(name = "quali_time", nullable = false)
	private java.time.LocalDateTime qualiTime;

	@Column(name = "race_time", nullable = false)
	private java.time.LocalDateTime raceTime;
	
	public ScheduleModel() {
	}
	public ScheduleModel(long id, int raceId, String raceName, String circutName, java.time.LocalDateTime scheduleDate,
			String typeOfWeekend, java.time.LocalDateTime sessionOneTime, java.time.LocalDateTime sessionTwoTime,
			java.time.LocalDateTime sessionThreeTime, java.time.LocalDateTime qualiTime,
			java.time.LocalDateTime raceTime) {
		this.id = id;
		this.raceId = raceId;
		this.raceName = raceName;
		this.circutName = circutName;
		this.scheduleDate = scheduleDate;
		this.typeOfWeekend = typeOfWeekend;
		this.sessionOneTime = sessionOneTime;
		this.sessionTwoTime = sessionTwoTime;
		this.sessionThreeTime = sessionThreeTime;
		this.qualiTime = qualiTime;
		this.raceTime = raceTime;

	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getRaceId() {
		return raceId;
	}
	public void setRaceId(int raceId) {
		this.raceId = raceId;
	}
	public String getRaceName() {
		return raceName;
	}
	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}
	public String getCircutName() {
		return circutName;
	}
	public void setCircutName(String circutName) {
		this.circutName = circutName;
	}
	public java.time.LocalDateTime getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(java.time.LocalDateTime scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public String getTypeOfWeekend() {
		return typeOfWeekend;
	}
	public void setTypeOfWeekend(String typeOfWeekend) {
		this.typeOfWeekend = typeOfWeekend;
	}
	public java.time.LocalDateTime getSessionOneTime() {
		return sessionOneTime;
	}
	public void setSessionOneTime(java.time.LocalDateTime sessionOneTime) {
		this.sessionOneTime = sessionOneTime;
	}
	public java.time.LocalDateTime getSessionTwoTime() {
		return sessionTwoTime;
	}
	public void setSessionTwoTime(java.time.LocalDateTime sessionTwoTime) {
		this.sessionTwoTime = sessionTwoTime;
	}
	public java.time.LocalDateTime getSessionThreeTime() {
		return sessionThreeTime;
	}
	public void setSessionThreeTime(java.time.LocalDateTime sessionThreeTime) {
		this.sessionThreeTime = sessionThreeTime;
	}
	public java.time.LocalDateTime getQualiTime() {
		return qualiTime;
	}
	public void setQualiTime(java.time.LocalDateTime qualiTime) {
		this.qualiTime = qualiTime;
	}
	public java.time.LocalDateTime getRaceTime() {
		return raceTime;
	}
	public void setRaceTime(java.time.LocalDateTime raceTime) {
		this.raceTime = raceTime;
	}
	@Override
	public String toString() {
		return "ScheduleModel [" + "Id = " + id + ", " + "RaceId = " + raceId + ", " + "RaceName = " + raceName + ", "
				+ "CircutName = " + circutName + ", " + "ScheduleDate = " + scheduleDate + ", " + "TypeOfWeekend = "
				+ typeOfWeekend + ", " + "SessionOneTime = " + sessionOneTime + ", " + "SessionTwoTime = "
				+ sessionTwoTime + ", " + "SessionThreeTime = " + sessionThreeTime + ", " + "QualiTime = " + qualiTime
				+ ", " + "RaceTime = " + raceTime + "]";
	}
	public void updateFrom(ScheduleModel scheduleModel) {
		setRaceId(scheduleModel.getRaceId());
		setRaceName(scheduleModel.getRaceName());
		setCircutName(scheduleModel.getCircutName());
		setScheduleDate(LocalDateTime.now());
		setTypeOfWeekend(scheduleModel.getTypeOfWeekend());
		setSessionOneTime(LocalDateTime.now());
		setSessionTwoTime(LocalDateTime.now());
		setSessionThreeTime(LocalDateTime.now());
		setQualiTime(LocalDateTime.now());
		setRaceTime(LocalDateTime.now());
	}
	
}
