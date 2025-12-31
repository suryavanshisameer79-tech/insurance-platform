package com.maximus.insurance.policy_service.controller;

import com.maximus.insurance.policy_service.dto.PolicyRequestDTO;
import com.maximus.insurance.policy_service.dto.PolicyResponseDTO;
import com.maximus.insurance.policy_service.entity.Policy;
import com.maximus.insurance.policy_service.service.PolicyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/policies")
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService policyService;

    @PostMapping
    public ResponseEntity<PolicyResponseDTO> createPolicy(@Valid @RequestBody PolicyRequestDTO policyRequestDTO){
        return ResponseEntity.ok(policyService.createPolicy(policyRequestDTO));
    }

    @GetMapping("/{policyId}")
    public ResponseEntity<PolicyResponseDTO> getPolicyById(@PathVariable UUID policyId){
        return ResponseEntity.ok(policyService.getPolicyById(policyId));
    }

    @GetMapping("/allPolicies")
    public ResponseEntity<List<PolicyResponseDTO>> getAllPolicies(){
        List<PolicyResponseDTO> policyList = policyService.getAllPolicies();
        return ResponseEntity.ok(policyList);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<PolicyResponseDTO>> getPoliciesByCustomerId(@PathVariable Long customerId){
        List<PolicyResponseDTO> responseDTOList = policyService.getPoliciesByCustomerId(customerId);

        return ResponseEntity.ok(responseDTOList);

        //or
     //   return ResponseEntity.ok(policyService.getPoliciesByCustomerId(customerId));
    }

    @PatchMapping("/{policyId}/cancel")
    public void cancelPolicy(@PathVariable UUID policyId){
    policyService.cancelPolicy(policyId);
    }
}