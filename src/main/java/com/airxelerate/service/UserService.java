package com.airxelerate.service;

import com.airxelerate.dto.request.UserCreateRequest;
import com.airxelerate.dto.request.UserUpdateRequest;
import com.airxelerate.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserResponse create(UserCreateRequest userCreateRequest);
    UserResponse update(UserUpdateRequest userUpdateRequest);
    Page<UserResponse> getAllByPageable(Pageable pageable);
}
