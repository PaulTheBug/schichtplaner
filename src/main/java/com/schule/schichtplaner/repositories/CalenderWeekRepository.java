package com.schule.schichtplaner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schule.schichtplaner.entitys.CalenderWeek;

public interface CalenderWeekRepository extends JpaRepository<CalenderWeek, Integer> {
}
