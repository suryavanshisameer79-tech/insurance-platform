package com.maximus.insurance.customer_service.service;

import com.maximus.insurance.customer_service.dto.CustomerRequestDto;
import com.maximus.insurance.customer_service.dto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {


    CustomerResponseDto createCustomer(CustomerRequestDto requestDto);

    CustomerResponseDto getCustomer(Long id);

    List<CustomerResponseDto> getAllCustomers();

    CustomerResponseDto updateCustomer(Long id, CustomerRequestDto updateRequestDto);

    void deleteById(Long id);
}
