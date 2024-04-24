package com.tech.springboot.repository;


import com.tech.springboot.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {

    Optional<Token> findTokenByRefreshToken(String refreshToken);

    Optional<Token> findTokenByRefreshTokenAndRevoked(String refreshToken, Boolean revoked);
}
