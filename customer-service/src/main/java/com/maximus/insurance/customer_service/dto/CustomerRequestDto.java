package com.maximus.insurance.customer_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequestDto(

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @Email(message = "Invalid email format")
        String email,

        String phone,
        String address,
        String city,
        String state,
        String pincode
) {
}
