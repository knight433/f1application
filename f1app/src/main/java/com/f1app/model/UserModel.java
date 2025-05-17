package com.f1app.model;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "user", schema = "dev")
public class UserModel {

	@Column(name = "id")
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "username", unique = true)
	@NotNull 
	private String username;

	@Column(name = "email", unique = true)
	@NotNull
	private String email;

	@Column(name = "password")
	@NotNull
	private String password;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "country")
	private String country;

	@Column(name = "role")
	private String role;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public UserModel() {}

	public UserModel(long id, String username, String email, String password, String fullName, String country, String role, boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.country = country;
		this.role = role;
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public void update(UserModel newModel )
	{
		setCountry(newModel.getCountry());
		setEmail(newModel.getEmail());
		setFullName(newModel.getFullName());
		setIsActive(newModel.isIsActive());
		setRole(newModel.getRole());
		setUpdatedAt(LocalDateTime.now());
		setUsername(newModel.getUsername());
		setPassword(newModel.getPassword());
	}

}