package com.gymconnect.reports.controller;

import com.gymconnect.common.response.ApiResponse;
import com.gymconnect.reports.service.ReportService;
import com.gymconnect.user.dto.UserCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor

public class ReportsController {

    private final ReportService reportService;

    @GetMapping("summary")
    public ResponseEntity<ApiResponse> reports() {
        ApiResponse apiResponse = reportService.getSummaryReport();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
