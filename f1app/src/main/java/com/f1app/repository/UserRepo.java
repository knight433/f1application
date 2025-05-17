package com.f1app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.f1app.model.UserModel;


public interface UserRepo extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByUsername(String username);
}