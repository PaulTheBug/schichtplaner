package com.schule.schichtplaner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schule.schichtplaner.entitys.Schicht;

public interface SchichtRepository extends JpaRepository<Schicht, Integer> {
}
