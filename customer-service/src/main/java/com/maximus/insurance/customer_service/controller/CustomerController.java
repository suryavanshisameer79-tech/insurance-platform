package com.maximus.insurance.customer_service.controller;

import com.maximus.insurance.customer_service.dto.CustomerRequestDto;
import com.maximus.insurance.customer_service.dto.CustomerResponseDto;
import com.maximus.insurance.customer_service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<List<CustomerResponseDto>> getAll(){
       List<CustomerResponseDto> response = customerService.getAllCustomers();
       return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getById(@PathVariable Long id){
    return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> create(@RequestBody CustomerRequestDto requestDto){
    return ResponseEntity.ok(customerService.createCustomer(requestDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerResponseDto> update(@PathVariable Long id, @RequestBody CustomerRequestDto updateRequestDto){
        return ResponseEntity.ok(customerService.updateCustomer(id,updateRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        customerService.deleteById(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }
}
