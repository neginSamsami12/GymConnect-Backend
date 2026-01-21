package com.gymconnect.classes.controller;

import com.gymconnect.classes.dto.ClassCreateRequest;
import com.gymconnect.classes.service.ClassesService;
import com.gymconnect.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class ClassesController {

    private final ClassesService classesService;

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ApiResponse> create(
            @RequestPart("data") @Valid ClassCreateRequest request,
            @RequestPart(value = "image") MultipartFile image
    ) {
        ApiResponse apiResponse = classesService.create(request, image);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    // GET /api/classes
    // GET /api/classes?athleteId=...
    @GetMapping
    public ResponseEntity<ApiResponse> findAll(
            @RequestParam(value = "athleteId", required = false) UUID athleteId
    ) {
        ApiResponse apiResponse = classesService.findAll(athleteId);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
