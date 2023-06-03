package com.airxelerate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthenticatedResponse {
    private String token;
    private String type;
    private String refreshToken;
    private UserResponse userResponse;
}
