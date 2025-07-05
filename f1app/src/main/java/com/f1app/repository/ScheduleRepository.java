package com.f1app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.f1app.model.ScheduleModel;
@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleModel, Long> {
}
