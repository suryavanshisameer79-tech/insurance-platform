package com.maximus.insurance.policy_service.repository;

import com.maximus.insurance.policy_service.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, UUID> {

    List<Policy> findByCustomerId(Long customerId);
}
