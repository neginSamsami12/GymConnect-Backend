package com.gymconnect.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    private final boolean success;
    private final Object data;
    private final String message;
    private final LocalDateTime timestamp;

    private ApiResponse(boolean success, Object data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public static ApiResponse success(Object data) {
        return new ApiResponse(true, data, null);
    }

    public static ApiResponse success(Object data, String message) {
        return new ApiResponse(true, data, message);
    }

    public static ApiResponse failure(String message) {
        return new ApiResponse(false, null, message);
    }
}
