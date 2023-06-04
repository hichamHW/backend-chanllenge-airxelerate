package com.airxelerate.dto.response;

import com.airxelerate.entity.Role;
import com.airxelerate.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("roles")
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
