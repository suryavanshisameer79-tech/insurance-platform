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
                .customerId(requestDto.customerId())
                .policyId(requestDto.policyId())
                .claimAmount(requestDto.claimAmount())
                .claimType(requestDto.claimType())
                .description(requestDto.description())
                .claimStatus(ClaimStatus.OPEN)                       // default
                .submittedDate(LocalDateTime.now())                 // default timestamp
                .build();
    }

    // Convert Entity → Response DTO
    public static ClaimResponseDto toResponse(Claim claim){
        return new ClaimResponseDto(
                claim.getId(),
                claim.getCustomerId(),
                claim.getPolicyId(),
                claim.getClaimAmount(),
                claim.getClaimType(),
                claim.getClaimStatus(),
                claim.getSubmittedDate(),
                claim.getUpdatedDate(),
                claim.getDescription()
        );
    }

    // Apply status update → Entity (partial update)
    public static void applyStatusUpdate(Claim claim, ClaimUpdateStatusRequestDto claimUpdateStatusRequestDto){
                claim.setClaimStatus(claimUpdateStatusRequestDto.claimStatus());
                claim.setUpdatedDate(LocalDateTime.now());

                if (claimUpdateStatusRequestDto.description() != null){
                    claim.setDescription(claimUpdateStatusRequestDto.description());
                }
    }
}
