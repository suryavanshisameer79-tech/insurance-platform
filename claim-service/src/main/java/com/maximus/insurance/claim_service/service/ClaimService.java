package com.maximus.insurance.claim_service.service;

import com.maximus.insurance.claim_service.dto.ClaimCreateRequestDto;
import com.maximus.insurance.claim_service.dto.ClaimResponseDto;
import com.maximus.insurance.claim_service.dto.ClaimUpdateStatusRequestDto;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface ClaimService {

    ClaimResponseDto createClaim( ClaimCreateRequestDto requestDto);

    ClaimResponseDto getClaimById(Long customerId);

    List<ClaimResponseDto> showAllClaims();

    ClaimResponseDto updateClaimStatus(UUID id, @Valid ClaimUpdateStatusRequestDto request);
}
