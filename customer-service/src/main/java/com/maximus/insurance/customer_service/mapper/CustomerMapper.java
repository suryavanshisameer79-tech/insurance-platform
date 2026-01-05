package com.maximus.insurance.customer_service.mapper;

import com.maximus.insurance.customer_service.dto.CustomerRequestDto;
import com.maximus.insurance.customer_service.dto.CustomerResponseDto;
import com.maximus.insurance.customer_service.entity.Customer;

public class CustomerMapper { //Mapper Class (Entity ↔ DTO)
    // You need a converter class for mapping.

    public static Customer toEntity(CustomerRequestDto dto) {
        return Customer.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .phone(dto.phone())
                .address(dto.address())
                .city(dto.city())
                .state(dto.state())
                .pincode(dto.pincode())
                .build();
    }

    public static CustomerResponseDto toDTO(Customer entity) {
        return new CustomerResponseDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getAddress(),
                entity.getCity(),
                entity.getState(),
                entity.getPincode()
                );
    }
}