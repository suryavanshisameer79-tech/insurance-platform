package com.maximus.insurance.customer_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponseDto { //This is what API returns — safe and formatted.

    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String pincode;
}
