package com.maximus.insurance.claim_service.service;

import com.maximus.insurance.claim_service.dto.ClaimCreateRequestDto;
import com.maximus.insurance.claim_service.dto.ClaimResponseDto;
import com.maximus.insurance.claim_service.dto.ClaimUpdateStatusRequestDto;
import com.maximus.insurance.claim_service.entity.Claim;
import com.maximus.insurance.claim_service.mapper.ClaimMapper;
import com.maximus.insurance.claim_service.repository.ClaimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService{

    private final ClaimRepository repository;
    @Override
    public ClaimResponseDto createClaim(ClaimCreateRequestDto requestDto) {
        Claim claim = ClaimMapper.toEntity(requestDto);

        Claim saved = repository.save(claim);

        return ClaimMapper.toResponse(saved);
    }

    @Override
    public ClaimResponseDto getClaimById(Long customerId) {
        Claim claim = repository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        return ClaimMapper.toResponse(claim);
    }

    @Override
    public List<ClaimResponseDto> showAllClaims() {
        List<Claim> entity = repository.findAll();

        return entity.stream()
                .map(ClaimMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClaimResponseDto updateClaimStatus(UUID id, ClaimUpdateStatusRequestDto request) {
        return null;
    }
}
