package com.airxelerate.service.impl;

import com.airxelerate.entity.RefreshToken;
import com.airxelerate.entity.User;
import com.airxelerate.exception.EntityNotFoundException;
import com.airxelerate.repository.RefreshTokenRepository;
import com.airxelerate.repository.UserRepository;
import com.airxelerate.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.airxelerate.constant.AppConstant.REFRESH_TOKEN_EXPIRATION_TIME;

@RequiredArgsConstructor
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;


    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return this.refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken create(User user,String token) {
        RefreshToken toSave = RefreshToken
                .builder()
                .token(token)
                .expirationDate( LocalDateTime.now().plusSeconds(REFRESH_TOKEN_EXPIRATION_TIME))
                .user(user)
                .build();
        RefreshToken saved =  this.refreshTokenRepository.save(toSave);
        return saved;
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpirationDate().compareTo(LocalDateTime.now()) < 0) {
            this.refreshTokenRepository.delete(token);
            throw new RuntimeException( "Refresh token was expired. Please make a new signIn request");
        }
        return token;
    }

    @Override
    public int deleteByUserId(UUID id) {
       User user =  this.userRepository.findById(id)
               .orElseThrow(()-> new EntityNotFoundException("User : "+ id +"not found !"));
        return refreshTokenRepository.deleteByUser(user);
    }
}
