package com.schule.schichtplaner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schule.schichtplaner.entitys.Einsatz;

public interface EinsatzRepository extends JpaRepository<Einsatz, Integer> {
}
