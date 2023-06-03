package com.airxelerate.dto.response;

import com.airxelerate.entity.Role;
import com.airxelerate.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private UUID id;
    private String fullName;
    private String userName;
    private Set<Role> roles;

    public static UserResponse fromEntity(User user){
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getUsername(),
                user.getRoles()
        );
    }
}
