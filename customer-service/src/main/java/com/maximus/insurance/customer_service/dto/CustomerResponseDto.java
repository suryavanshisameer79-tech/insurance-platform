package com.maximus.insurance.customer_service.dto;

public record CustomerResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String address,
        String city,
        String state,
        String pincode
) {
}
