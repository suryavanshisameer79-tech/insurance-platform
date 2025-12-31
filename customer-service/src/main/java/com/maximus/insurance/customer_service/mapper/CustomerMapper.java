package com.maximus.insurance.customer_service.mapper;

import com.maximus.insurance.customer_service.dto.CustomerRequestDto;
import com.maximus.insurance.customer_service.dto.CustomerResponseDto;
import com.maximus.insurance.customer_service.entity.Customer;

public class CustomerMapper { //Mapper Class (Entity ↔ DTO)
    // You need a converter class for mapping.

    public static Customer toEntity(CustomerRequestDto dto) {
        return Customer.builder()
                .firstName(dto.getFirstName()).
                lastName(dto.getLastName()).
                email(dto.getEmail()).
                phone(dto.getPhone()).
                address(dto.getAddress()).
                city(dto.getCity()).
                state(dto.getState()).
                pincode(dto.getPincode()).
                build();
    }

    public static CustomerResponseDto toDTO(Customer entity){
        return CustomerResponseDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .city(entity.getCity())
                .state(entity.getState())
                .pincode(entity.getPincode())
                .build();
    }
}