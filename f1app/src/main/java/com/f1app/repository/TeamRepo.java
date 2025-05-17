package com.f1app.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
 import com.f1app.model.TeamModel;

public interface TeamRepo extends JpaRepository<TeamModel, Long> {}