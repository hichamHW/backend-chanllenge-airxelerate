package com.airxelerate.dto.request;

import com.airxelerate.entity.Role;
import com.airxelerate.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    @Schema(name = "full_name")
    private String fullName;
    @NotBlank
    @Size(min = 3, max = 20)
    @Schema(name = "user_name")
    private String userName;
    @Schema(name = "roles")
    private Set<Role> roles;
    @NotBlank
    @Size(min = 6, max = 40)
    @Schema(name = "password")
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
