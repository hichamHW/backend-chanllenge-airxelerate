package com.airxelerate.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRequest {
    @NotNull(message = "user_name not null")
    @NotBlank(message = "user_name not blank")
    @JsonProperty( "user_name")
    private String userName;
    @NotNull(message = "password not null")
    @NotBlank(message = "password not blank")
    @JsonProperty( "password")
    private String password;
}
