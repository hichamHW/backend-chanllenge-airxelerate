package com.airxelerate.security;

import com.airxelerate.entity.RefreshToken;
import com.airxelerate.exception.EntityNotFoundException;
import com.airxelerate.repository.RefreshTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.split(" ")[1].trim();
        RefreshToken storedToken = refreshTokenRepository.findByToken(jwt)
                .orElseThrow(()-> new EntityNotFoundException("token not found"));
        if (storedToken != null) {
            refreshTokenRepository.save(storedToken);
            SecurityContextHolder.clearContext();
        }

    }
}
