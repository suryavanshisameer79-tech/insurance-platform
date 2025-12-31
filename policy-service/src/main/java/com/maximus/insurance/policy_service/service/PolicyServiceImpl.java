package com.maximus.insurance.policy_service.service;

import com.maximus.insurance.policy_service.dto.PolicyRequestDTO;
import com.maximus.insurance.policy_service.dto.PolicyResponseDTO;
import com.maximus.insurance.policy_service.entity.Policy;
import com.maximus.insurance.policy_service.entity.PolicyStatus;
import com.maximus.insurance.policy_service.mapper.PolicyMapper;
import com.maximus.insurance.policy_service.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService{

    private final PolicyRepository policyRepository;

    @Override
    public PolicyResponseDTO createPolicy(PolicyRequestDTO policyRequestDTO) {
        Policy policy = PolicyMapper.toEntity(policyRequestDTO);

        Policy savedPolicy = policyRepository.save(policy);

        return PolicyMapper.toResponse(savedPolicy);
    }

    @Override
    public PolicyResponseDTO getPolicyById(UUID policyId) {
        Policy policy = policyRepository.findById(policyId).orElseThrow(() -> new RuntimeException("Policy not found"));

        return PolicyMapper.toResponse(policy);
    }

    @Override
    public List<PolicyResponseDTO> getAllPolicies() {
        List<Policy> policyList = policyRepository.findAll();

        return policyList.stream()
                .map(PolicyMapper :: toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PolicyResponseDTO> getPoliciesByCustomerId(Long customerId) {
        List<Policy> policies = policyRepository.findByCustomerId(customerId);

        return policies.stream()
                .map(PolicyMapper :: toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void cancelPolicy(UUID policyId) {
        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        policy.setStatus(PolicyStatus.CANCELLED);
        policyRepository.save(policy);
    }
}
