package com.maximus.insurance.claim_service.dto;

import com.maximus.insurance.claim_service.entity.ClaimStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClaimResponseDto {

    private UUID id;

    private Long customerId;

    private UUID policyId;

    private Double claimAmount;

    private String claimType;

    private ClaimStatus claimStatus;

    private LocalDateTime submittedDate;

    private LocalDateTime updatedDate;

    private String description;
}
