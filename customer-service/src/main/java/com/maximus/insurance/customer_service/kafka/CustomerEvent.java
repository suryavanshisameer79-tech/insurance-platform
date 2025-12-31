package com.maximus.insurance.customer_service.kafka;

import com.maximus.insurance.customer_service.dto.CustomerResponseDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerEvent {
    // (used when publishing customer-created/updated events)
    private String eventType;                    // CUSTOMER_CREATED, CUSTOMER_UPDATED, etc
    private CustomerResponseDto data;         // customer JSON data
}
