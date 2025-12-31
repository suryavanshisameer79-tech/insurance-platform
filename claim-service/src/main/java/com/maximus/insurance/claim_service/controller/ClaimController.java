package com.maximus.insurance.claim_service.controller;

import com.maximus.insurance.claim_service.dto.ClaimCreateRequestDto;
import com.maximus.insurance.claim_service.dto.ClaimResponseDto;
import com.maximus.insurance.claim_service.dto.ClaimUpdateStatusRequestDto;
import com.maximus.insurance.claim_service.service.ClaimService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/claims")
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService claimService;

    @PostMapping
    public ResponseEntity<ClaimResponseDto> createClaim(@Valid @RequestBody ClaimCreateRequestDto requestDto){
        ClaimResponseDto response = claimService.createClaim(requestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<ClaimResponseDto> getClaimById(@PathVariable Long customerId){
        ClaimResponseDto response = claimService.getClaimById(customerId);
        return  ResponseEntity.ok(response);
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<ClaimResponseDto>> showAllClaims(){
        List<ClaimResponseDto> response = claimService.showAllClaims();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ClaimResponseDto> updateClaimStatus(@PathVariable UUID id,
                                                              @Valid @RequestBody ClaimUpdateStatusRequestDto request){
        return ResponseEntity.ok(claimService.updateClaimStatus(id, request));

    }

}
