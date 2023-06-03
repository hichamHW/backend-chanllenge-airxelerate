package com.airxelerate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageErrorResponse {
    private Set<String> messages;
    private int statusCode;
    private LocalDateTime dateTime;
    private String path;
}
