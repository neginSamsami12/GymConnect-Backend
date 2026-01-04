package com.gymconnect.common.exception;

import com.gymconnect.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(BusinessException.class)
//    public ResponseEntity<ApiResponse> handleBusiness(BusinessException ex) {
//        return ResponseEntity
//                .badRequest()
//                .body(ApiResponse.failure(ex.getMessage()));
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGeneric(Exception ex) {
        return ResponseEntity
                .internalServerError()
                .body(ApiResponse.failure("Unexpected error"));
    }
}
