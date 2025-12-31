package com.maximus.insurance.claim_service.mapper;

import com.maximus.insurance.claim_service.dto.ClaimCreateRequestDto;
import com.maximus.insurance.claim_service.dto.ClaimResponseDto;
import com.maximus.insurance.claim_service.dto.ClaimUpdateStatusRequestDto;
import com.maximus.insurance.claim_service.entity.Claim;
import com.maximus.insurance.claim_service.entity.ClaimStatus;

import java.time.LocalDateTime;

public class ClaimMapper {

    // Convert CreateRequest → Entity
    public static Claim toEntity(ClaimCreateRequestDto requestDto){
        return Claim.builder()
                .customerId(requestDto.getCustomerId())
                .policyId(requestDto.getPolicyId())
                .claimAmount(requestDto.getClaimAmount())
                .claimType(requestDto.getClaimType())
                .description(requestDto.getDescription())
                .claimStatus(ClaimStatus.OPEN)                       // default
                .submittedDate(LocalDateTime.now())                 // default timestamp
                .build();
    }

    // Convert Entity → Response DTO
    public static ClaimResponseDto toResponse(Claim claim){
        return ClaimResponseDto.builder()
                .id(claim.getId())
                .customerId(claim.getCustomerId())
                .policyId(claim.getPolicyId())
                .claimAmount(claim.getClaimAmount())
                .claimType(claim.getClaimType())
                .claimStatus(claim.getClaimStatus())
                .submittedDate(claim.getSubmittedDate())
                .updatedDate(claim.getUpdatedDate())
                .description(claim.getDescription())
                .build();
    }

    // Apply status update → Entity (partial update)
    public static void applyStatusUpdate(Claim claim, ClaimUpdateStatusRequestDto claimUpdateStatusRequestDto){
                claim.setClaimStatus(claimUpdateStatusRequestDto.getClaimStatus());
                claim.setUpdatedDate(LocalDateTime.now());

                if (claimUpdateStatusRequestDto.getDescription() != null){
                    claim.setDescription(claimUpdateStatusRequestDto.getDescription());
                }
    }
}
