package com.schule.schichtplaner.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schule.schichtplaner.entitys.Mitarbeiter;

public interface MitarbeiterRepository extends JpaRepository<Mitarbeiter, Integer> {
    Optional<Mitarbeiter> findByBenutzername(String benutzername);
}
