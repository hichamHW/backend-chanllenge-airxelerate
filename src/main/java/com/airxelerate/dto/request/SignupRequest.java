package com.airxelerate.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequest {
    @NotBlank(message = "full_name is empty")
    @Size(min = 3, max = 20)
    @Schema(name = "full_name")
    private String fullName;
    @NotBlank(message = "user_name is empty")
    @Size(min = 3, max = 20)
    @Schema(name = "user_name")
    private String userName;
    @NotBlank(message = "password is empty")
    @Size(min = 6, max = 40)
    @Schema(name = "password")
    private String password;
}
