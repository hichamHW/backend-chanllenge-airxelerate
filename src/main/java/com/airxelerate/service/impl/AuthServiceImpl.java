package com.airxelerate.service.impl;

import com.airxelerate.dto.request.AuthRequest;
import com.airxelerate.dto.request.SignupRequest;
import com.airxelerate.dto.request.TokenRefreshRequest;
import com.airxelerate.dto.response.AuthenticatedResponse;
import com.airxelerate.dto.response.UserResponse;
import com.airxelerate.entity.RefreshToken;
import com.airxelerate.entity.Role;
import com.airxelerate.entity.User;
import com.airxelerate.exception.EntityNotFoundException;
import com.airxelerate.exception.UserNameExistException;
import com.airxelerate.repository.UserRepository;
import com.airxelerate.service.AuthService;
import com.airxelerate.service.RefreshTokenService;
import com.airxelerate.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;



@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RefreshTokenService refreshTokenService;
    private final JwtUtil jwtUtil;


    @Transactional
    @Override
    public AuthenticatedResponse authenticate(AuthRequest authRequest) {
             authenticationManager.authenticate(
                     new UsernamePasswordAuthenticationToken(
                             authRequest.getUserName(),
                             authRequest.getPassword()
                     )
             );
        User user = userRepository.findByUserName(authRequest.getUserName())
                .orElseThrow(()-> new EntityNotFoundException("User : "+authRequest.getUserName()+" not found !" ));
        String token = jwtUtil.generateToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);
        refreshTokenService.create(user, token);
        return AuthenticatedResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .userResponse(UserResponse.fromEntity(user))
                .build();
    }

    @Transactional
    public UserResponse registerUser(SignupRequest signUpRequest) {
      Optional<User> userNameExist =  userRepository.findByUserName(signUpRequest.getUserName());
       if(userNameExist.isPresent()){
           log.error("Username {} a ready exist! ");
           throw new UserNameExistException("Username :  "+ signUpRequest.getUserName() +" a ready exist !");
       }
        Set<Role> roleSet = Set.of(Role.USER);
        User user = new User(
                signUpRequest.getFullName(),
                signUpRequest.getUserName(),
                encoder.encode(signUpRequest.getPassword()),
                roleSet
        );
       User saved =  userRepository.save(user);
        return UserResponse.fromEntity(saved);
    }

    @Transactional
    public String refreshToken(TokenRefreshRequest tokenRefreshRequest) {
        String requestRefreshToken = tokenRefreshRequest.getRefreshToken();
        return this.refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtil.generateToken(user);
                    return token;
                })
                .orElseThrow(() -> {
                    log.error("Token {} not found !", tokenRefreshRequest);
                   return new EntityNotFoundException("Token:  " + tokenRefreshRequest + " not found !");
                });
    }

    public boolean logoutUser() {
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();
            UUID userId = userDetails.getId();
            log.info("user logout : {}", userId);
            refreshTokenService.deleteByUserId(userId);
        return true ;
    }


}
