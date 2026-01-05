package com.maximus.insurance.claim_service.dto;

import com.maximus.insurance.claim_service.entity.ClaimStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClaimResponseDto(

         UUID id,

         Long customerId,

         UUID policyId,

         Double claimAmount,

         String claimType,

         ClaimStatus claimStatus,

         LocalDateTime submittedDate,

         LocalDateTime updatedDate,

         String description

) {
}
