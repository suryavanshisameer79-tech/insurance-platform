package com.maximus.insurance.claim_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClaimCreateRequestDto {

    @NotNull
    private Long customerId;

    @NotNull
    private UUID policyId;

    @NotNull
    @Positive
    private Double claimAmount;

    @NotNull
    private String claimType;

    private String description;
}
