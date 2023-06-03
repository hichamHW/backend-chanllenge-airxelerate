package com.airxelerate.service.impl;

import com.airxelerate.dto.request.UserCreateRequest;
import com.airxelerate.dto.request.UserUpdateRequest;
import com.airxelerate.dto.response.UserResponse;
import com.airxelerate.entity.User;
import com.airxelerate.exception.EntityNotFoundException;
import com.airxelerate.exception.UserNameExistException;
import com.airxelerate.repository.UserRepository;
import com.airxelerate.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public UserResponse create(UserCreateRequest userCreateRequest) {
        Optional<User> userNameExist =  userRepository.findByUserName(userCreateRequest.getUserName());
        if(userNameExist.isPresent()){
            log.error("Username {} a ready exist! ", userCreateRequest.getUserName());
            throw new UserNameExistException("Username :  "+ userCreateRequest.getUserName() +" a ready exist !");
        }
        userCreateRequest.setPassword( encoder.encode(userCreateRequest.getPassword()));
        User userCreated = userRepository.save(userCreateRequest.toEntity());
        return UserResponse.fromEntity(userCreated);
    }

    @Override
    public UserResponse update(UserUpdateRequest userUpdateRequest) {
      userRepository.findByUserName(userUpdateRequest.getUserName())
                .orElseThrow(()-> new EntityNotFoundException("User :  "+userUpdateRequest.getId()+ " not found!"));
        userUpdateRequest.setPassword( encoder.encode(userUpdateRequest.getPassword()));
        User userCreated = userRepository.save(userUpdateRequest.toEntity());
        return UserResponse.fromEntity(userCreated);
    }


    @Override
    public Page<UserResponse> getAllByPageable(Pageable pageable) {
        Page<User> userPage =  userRepository.findAll(pageable);
        List<UserResponse> userResponses = userPage
                .getContent()
                .stream()
                .map(element-> UserResponse.fromEntity(element))
                .collect(Collectors.toList());
        return new PageImpl<>(
                userResponses,
                pageable ,
                userPage.getTotalElements()
        );
    }
}
