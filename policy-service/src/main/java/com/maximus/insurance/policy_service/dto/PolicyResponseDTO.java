package com.maximus.insurance.policy_service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record PolicyResponseDTO(

        UUID policyId,
        Long customerId,
        String policyType,
        BigDecimal coverageAmount,
        BigDecimal premiumAmount,
        LocalDate startDate,
        LocalDate endDate,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    // PolicyResponseDTO: Read API
}
