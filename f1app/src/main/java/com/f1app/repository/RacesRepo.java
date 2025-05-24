package com.f1app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.f1app.model.RacesModel;

public interface RacesRepo extends JpaRepository<RacesModel, Long> {
}
