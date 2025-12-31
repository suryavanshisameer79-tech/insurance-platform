package com.maximus.insurance.policy_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class PolicyRequestDTO {
   // Use: PolicyRequestDTO (Create / Update)

    @NotNull
    private Long customerId;

    @NotNull
    private String policyType;

    @NotNull
    @Positive
    private BigDecimal coverageAmount;

    @NotNull
    @Positive
    private BigDecimal premiumAmount;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}
