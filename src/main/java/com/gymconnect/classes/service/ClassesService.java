package com.gymconnect.classes.service;

import com.gymconnect.classes.dto.ClassCreateRequest;
import com.gymconnect.common.response.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ClassesService {
    ApiResponse create(ClassCreateRequest request, MultipartFile image);

    ApiResponse findAll(UUID athleteId);

}
