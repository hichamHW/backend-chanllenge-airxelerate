package com.airxelerate.repository;

import com.airxelerate.entity.RefreshToken;
import com.airxelerate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
   Optional<RefreshToken> findByToken(String token);
   int deleteByUser(User user);
}
