package com.gymconnect.user.controller;

import com.gymconnect.common.response.ApiResponse;
import com.gymconnect.user.dto.UserCreateRequest;
import com.gymconnect.user.dto.UserUpdateRequest;
import com.gymconnect.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> create(
            @RequestPart("data") @Valid UserCreateRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        ApiResponse response = userService.create(request, image);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ApiResponse> update(
            @PathVariable UUID id,
            @RequestPart("data") @Valid UserUpdateRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        ApiResponse response = userService.update(id, request, image);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAll() {
        ApiResponse apiResponse = userService.findAll();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable UUID id) {
        ApiResponse apiResponse = userService.findById(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UUID> deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
        return ResponseEntity.ok(id);
    }
}
