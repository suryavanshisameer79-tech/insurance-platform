package com.maximus.insurance.claim_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

public record ClaimCreateRequestDto(

        @NotNull
        Long customerId,

        @NotNull
        UUID policyId,

        @NotNull
        @Positive
        Double claimAmount,

        @NotNull
        String claimType,

        String description
) {

}
