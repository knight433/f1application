
package com.f1app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.f1app.model.DriversModel;


public interface DriversRepo extends JpaRepository<DriversModel, Long> {}