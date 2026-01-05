package com.maximus.insurance.claim_service.dto;

import com.maximus.insurance.claim_service.entity.ClaimStatus;

import java.util.List;

public record ClaimListResponseDto(

        List<ClaimResponseDto> claims
) {

}
