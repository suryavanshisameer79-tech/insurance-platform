package com.maximus.insurance.policy_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PolicyRequestDTO(

            @NotNull
            Long customerId,

            @NotNull
            String policyType,

            @NotNull
            @Positive
            BigDecimal coverageAmount,

            @NotNull
            @Positive
            BigDecimal premiumAmount,

            @NotNull
            LocalDate startDate,

            @NotNull
            LocalDate endDate

) {
}
