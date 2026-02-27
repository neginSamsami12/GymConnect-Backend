package com.gymconnect.reports.dto;

import java.math.BigDecimal;

public record SummaryReportResponse(
        BigDecimal totalSells,
        BigDecimal  totalProfit,
        BigDecimal  incomeGrowth,
        int newUser
) {
}
