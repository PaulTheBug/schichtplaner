package com.schule.schichtplaner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schule.schichtplaner.entitys.Urlaub;

public interface UrlaubRepository extends JpaRepository<Urlaub, Integer> {
}
