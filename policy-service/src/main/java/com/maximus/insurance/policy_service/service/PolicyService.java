package com.maximus.insurance.policy_service.service;

import com.maximus.insurance.policy_service.dto.PolicyRequestDTO;
import com.maximus.insurance.policy_service.dto.PolicyResponseDTO;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface PolicyService {

    PolicyResponseDTO createPolicy(PolicyRequestDTO policyRequestDTO);

    PolicyResponseDTO getPolicyById(@Valid UUID policyId);

    List<PolicyResponseDTO> getAllPolicies();

    List<PolicyResponseDTO> getPoliciesByCustomerId(@Valid Long customerId);

    void cancelPolicy(UUID policyId);
}
