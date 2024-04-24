package com.tech.springboot.repository;

import com.tech.springboot.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    Optional<Permission> findPermissionById(UUID id);
    Optional<Permission> findPermissionByName(String name);
}
