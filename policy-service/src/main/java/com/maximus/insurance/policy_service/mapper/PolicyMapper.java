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
                .customerId(dto.customerId())
                .policyType(dto.policyType())
                .coverageAmount(dto.coverageAmount())
                .premiumAmount(dto.premiumAmount())
                .startDate(dto.startDate())
                .endDate(dto.endDate())
                .status(PolicyStatus.ACTIVE)
                .build();
    }

    public static PolicyResponseDTO toResponse(Policy policy){
        if(policy == null){
            return null;
        }

       return new PolicyResponseDTO(
        policy.getPolicyId(),
                policy.getCustomerId(),
                policy.getPolicyType(),
                policy.getCoverageAmount(),
                policy.getPremiumAmount(),
                policy.getStartDate(),
                policy.getEndDate(),
                policy.getStatus().name(),
                policy.getCreatedAt(),
                policy.getUpdatedAt()
       );
    }
}
