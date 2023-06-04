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
public class TokenRefreshRequest {
    @NotBlank(message = "refresh_token not blank")
    @NotNull(message = "refresh_token not null")
    @JsonProperty( "refresh_token")
    private String refreshToken;
}
