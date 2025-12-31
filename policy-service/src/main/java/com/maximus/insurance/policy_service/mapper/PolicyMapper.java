package com.maximus.insurance.policy_service.mapper;

import com.maximus.insurance.policy_service.dto.PolicyRequestDTO;
import com.maximus.insurance.policy_service.dto.PolicyResponseDTO;
import com.maximus.insurance.policy_service.entity.Policy;
import com.maximus.insurance.policy_service.entity.PolicyStatus;

public class PolicyMapper {

    private PolicyMapper() {

    }

    public static Policy toEntity(PolicyRequestDTO dto){
        if (dto == null){
            return null;
        }
        return Policy.builder()
                .customerId(dto.getCustomerId())
                .policyType(dto.getPolicyType())
                .coverageAmount(dto.getCoverageAmount())
                .premiumAmount(dto.getPremiumAmount())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .status(PolicyStatus.ACTIVE)
                .build();
    }

    public static PolicyResponseDTO toResponse(Policy policy){
        if(policy == null){
            return null;
        }

        PolicyResponseDTO dto = new PolicyResponseDTO();
        dto.setPolicyId(policy.getPolicyId());
        dto.setCustomerId(policy.getCustomerId());
        dto.setPolicyType(policy.getPolicyType());
        dto.setCoverageAmount(policy.getCoverageAmount());
        dto.setPremiumAmount(policy.getPremiumAmount());
        dto.setStartDate(policy.getStartDate());
        dto.setEndDate(policy.getEndDate());
        dto.setStatus(policy.getStatus().name());
        dto.setCreatedAt(policy.getCreatedAt());
        dto.setUpdatedAt(policy.getUpdatedAt());

        return dto;
    }
}
