package com.airxelerate.service;

import com.airxelerate.dto.request.AuthRequest;
import com.airxelerate.dto.request.SignupRequest;
import com.airxelerate.dto.request.TokenRefreshRequest;
import com.airxelerate.dto.response.AuthenticatedResponse;
import com.airxelerate.dto.response.UserResponse;

public interface AuthService {
    AuthenticatedResponse authenticate(AuthRequest authRequest);
    UserResponse registerUser(SignupRequest signUpRequest);
    String refreshToken(TokenRefreshRequest tokenRefreshRequest);
    boolean logoutUser();
}
