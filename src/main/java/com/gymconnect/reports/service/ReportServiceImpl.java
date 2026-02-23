package com.gymconnect.reports.service;

import com.gymconnect.common.entity.Payment;
import com.gymconnect.common.repository.PaymentRepository;
import com.gymconnect.common.response.ApiResponse;
import com.gymconnect.reports.dto.SummaryReportResponse;
import com.gymconnect.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ReportServiceImpl implements ReportService {


    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;


    @Override
    public ApiResponse getSummaryReport() {
        BigDecimal lastMonthPayments = paymentRepository.sumAmountBetween(Instant.now().minus(30,
                ChronoUnit.DAYS), Instant.now());

        BigDecimal lastTwoMonthPayments = paymentRepository.sumAmountBetween(Instant.now().minus(60,
                ChronoUnit.DAYS), Instant.now().minus(30, ChronoUnit.DAYS));

        int newUserCount = userRepository.countByCreatedAtBetween(Instant.now().minus(30,
                ChronoUnit.DAYS), Instant.now());

        SummaryReportResponse response = new SummaryReportResponse(
                lastMonthPayments, lastMonthPayments.multiply(new BigDecimal(0.6)),
                lastMonthPayments.add(lastTwoMonthPayments.negate()), newUserCount
        );
        return ApiResponse.success(response);
    }



}
