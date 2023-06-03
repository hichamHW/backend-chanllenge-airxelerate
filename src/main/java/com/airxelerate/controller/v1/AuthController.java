package com.airxelerate.controller.v1;

import com.airxelerate.dto.request.AuthRequest;
import com.airxelerate.dto.request.SignupRequest;
import com.airxelerate.dto.request.TokenRefreshRequest;
import com.airxelerate.dto.response.AuthenticatedResponse;
import com.airxelerate.dto.response.UserResponse;
import com.airxelerate.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/authentication")
public class AuthController {
   private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<AuthenticatedResponse> authenticate(  @RequestBody AuthRequest authRequest){
        log.info("authenticate user : {}", authRequest);
       AuthenticatedResponse authenticated = this.authService.authenticate(authRequest);
        return ResponseEntity.ok(authenticated);
    }
    @PostMapping("signup")
    public ResponseEntity<UserResponse> signup ( @RequestBody SignupRequest signupRequest){
        log.info("signup user: {}", signupRequest);
        UserResponse created = this.authService.registerUser(signupRequest);
        return ResponseEntity.ok(created);
    }

    @PostMapping("refresh-token")
    public ResponseEntity<String> refreshToken ( @RequestBody TokenRefreshRequest tokenRefreshRequest){
        log.info("refresh Token user");
        String refreshToken = this.authService.refreshToken(tokenRefreshRequest);
        return ResponseEntity.ok(refreshToken);
    }
    @PostMapping("logout")
    public ResponseEntity<Boolean> logout ( ){
        log.info("logout user");
        boolean logoutUser = this.authService.logoutUser();
        return ResponseEntity.ok(logoutUser);
    }
}
