package com.airxelerate.controller.v1;


import com.airxelerate.dto.request.UserCreateRequest;
import com.airxelerate.dto.request.UserUpdateRequest;
import com.airxelerate.dto.response.UserResponse;
import com.airxelerate.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<UserResponse> create( @Valid  @RequestBody UserCreateRequest userCreateRequest){
        log.info("create user: {}", userCreateRequest);
        UserResponse created = this.userService.create(userCreateRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Page<UserResponse>> getUserByPageable(Pageable pageable){
        log.info("create user: {}", pageable);
        Page<UserResponse> pageUserResponse = this.userService.getAllByPageable(pageable);
        return ResponseEntity
                .ok(pageUserResponse);
    }
    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<UserResponse> update(@Valid @RequestBody UserUpdateRequest userUpdateRequest){
        log.info("create user: {}", userUpdateRequest);
        UserResponse updated = this.userService.update(userUpdateRequest);
        return ResponseEntity.ok(updated);
    }
}
