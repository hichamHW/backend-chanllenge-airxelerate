package com.airxelerate.service;

import com.airxelerate.entity.RefreshToken;
import com.airxelerate.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenService {
     Optional<RefreshToken> findByToken(String token) ;
     Optional<RefreshToken> findByRefreshToken(String token) ;
     RefreshToken update(RefreshToken token) ;
     RefreshToken create(User user, String token,String rToken) ;
     RefreshToken verifyExpiration(RefreshToken token) ;
     int deleteByUserId(UUID id) ;
}
