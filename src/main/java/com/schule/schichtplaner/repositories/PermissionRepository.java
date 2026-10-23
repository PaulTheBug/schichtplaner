package com.schule.schichtplaner.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schule.schichtplaner.entitys.Permission;

public interface PermissionRepository extends JpaRepository<Permission, String> {
    Optional<Permission> findByName(String name);
}
