package com.f1app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.f1app.model.TrackModel;

public interface TrackRepo extends JpaRepository<TrackModel, Long> {
}