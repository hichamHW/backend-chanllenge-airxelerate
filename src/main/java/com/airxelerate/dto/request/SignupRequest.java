package com.airxelerate.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequest {
    @NotBlank(message = "full_name is empty")
    @NotNull(message = "full_name not null")
    @Size(min = 3, max = 20)
    @JsonProperty( "full_name")
    private String fullName;
    @NotNull(message = "user_name not null")
    @NotBlank(message = "user_name is empty")
    @Size(min = 3, max = 20)
    @JsonProperty( "user_name")
    private String userName;
    @NotNull(message = "password not null")
    @NotBlank(message = "password is empty")
    @Size(min = 6, max = 40)
    @JsonProperty( "password")
    private String password;
}
