package com.tech.springboot.repository;

import com.tech.springboot.entity.User;
import com.tech.springboot.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserById(UUID id);
    Optional<User> findUserByUsername(String username);
    List<User> findUsersByStatus(StatusEnum status);
}
