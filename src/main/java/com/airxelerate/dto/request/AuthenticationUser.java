package com.airxelerate.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationUser{
    @NotBlank
    @Schema(name = "user_name")
    private String userName;
    @NotBlank
    @Schema(name = "password")
    private String password;
}
