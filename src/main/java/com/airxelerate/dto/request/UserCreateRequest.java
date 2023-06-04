package com.airxelerate.dto.request;

import com.airxelerate.entity.Role;
import com.airxelerate.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateRequest {
    @NotBlank(message = "full_name not blank")
    @NotNull(message = "full_name not null")
    @Size(min = 3, max = 20)
    @JsonProperty( "full_name")
    private String fullName;
    @NotBlank(message = "user_name not blank")
    @NotNull(message = "user_name not null")
    @Size(min = 3, max = 20)
    @JsonProperty( "user_name")
    private String userName;
    @NotNull(message = "roles not null")
    @JsonProperty( "roles")
    private Set<Role> roles;
    @NotBlank(message = "password not blank")
    @NotNull(message = "password not null")
    @Size(min = 6, max = 40)
    @JsonProperty( "password")
    private String password;

    public User toEntity(){
        User user =  new User(
                this.fullName,
                this.userName,
                this.password,
                this.roles
        );
        return user;
    }
}
