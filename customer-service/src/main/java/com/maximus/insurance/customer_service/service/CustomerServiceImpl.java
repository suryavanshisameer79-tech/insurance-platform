package com.maximus.insurance.customer_service.service;

import com.maximus.insurance.customer_service.dto.CustomerRequestDto;
import com.maximus.insurance.customer_service.dto.CustomerResponseDto;
import com.maximus.insurance.customer_service.entity.Customer;
import com.maximus.insurance.customer_service.exception.ResourceNotFoundException;
import com.maximus.insurance.customer_service.kafka.CustomerEvent;
import com.maximus.insurance.customer_service.mapper.CustomerMapper;
import com.maximus.insurance.customer_service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String CUSTOMER_CACHE = "CUSTOMER_";

    // CREATE CUSTOMER
    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto requestDto) {

        Customer entity = CustomerMapper.toEntity(requestDto);   //        RequestDTO → Entity
        Customer saved = customerRepository.save(entity);        //        Save Entity to DB

        // // Save to Redis
        redisTemplate.opsForValue().set(CUSTOMER_CACHE + saved.getId(), saved);

        // Prepare event
        CustomerResponseDto response = CustomerMapper.toDTO(saved);
        CustomerEvent event =   CustomerEvent.builder()
                .eventType("CUSTOMER_CREATED")
                .data(response)
                .build();

        // Publish event
        kafkaTemplate.send("customer-created", event);

        return response;                      // Entity → ResponseDTO
    }

     // GET CUSTOMER
    @Override
    public CustomerResponseDto getCustomer(Long id) {
        String key = CUSTOMER_CACHE + id;

        // Check from Redis cache first
        Customer cached = (Customer) redisTemplate.opsForValue().get(key);
        if (cached != null) {
            return CustomerMapper.toDTO(cached);
        }

        // If not found in cache, load from DB
        Customer entity = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));

        // Save to Redis cache
        redisTemplate.opsForValue().set(key, entity);

        return CustomerMapper.toDTO(entity);
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() { //Fetch all customers from DB → convert each entity to DTO → return list of DTOs.
        String key = "CUSTOMER_LIST";

        // 1. Check Redis Cache first
        List<Customer> cachedList = (List<Customer>) redisTemplate.opsForValue().get(key);

        if (cachedList != null && !cachedList.isEmpty()){
            return cachedList.stream()
                    .map(CustomerMapper::toDTO)
                    .collect(Collectors.toList());
        }

        // 2. If not found in cache → Load from DB
        List<Customer> entities = customerRepository.findAll(); // // findAll() is a Spring Data JPA method.It fetches all rows from the customer table.

        // 3. Save list into Redis Cache
        redisTemplate.opsForValue().set(key,entities);


        return entities.stream()                // entities.stream(): Converts the list into a stream so we can process items one-by-one.
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
    }

    // UPDATE CUSTOMER
    @Override
    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto updateRequestDto) {
        Customer existing = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));

        existing.setFirstName(updateRequestDto.firstName());
        existing.setLastName(updateRequestDto.lastName());
        existing.setEmail(updateRequestDto.email());
        existing.setPhone(updateRequestDto.phone());
        existing.setAddress(updateRequestDto.address());
        existing.setCity(updateRequestDto.city());
        existing.setState(updateRequestDto.state());
        existing.setPincode(updateRequestDto.pincode());

        Customer updated = customerRepository.save(existing);

        // Update Redis cache
        redisTemplate.opsForValue().set(CUSTOMER_CACHE + id, updated);

        // Prepare event
        CustomerResponseDto response = CustomerMapper.toDTO(updated);
        CustomerEvent event = CustomerEvent.builder()
                .eventType("CUSTOMER_UPDATED")
                .data(response)
                .build();

        // Publish event
        kafkaTemplate.send("customer-updated", event);

        return response;
    }

    // DELETE CUSTOMER
    @Override
    public void deleteById(Long id) {

        customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
        customerRepository.deleteById(id);

        // Remove from Redis
        redisTemplate.delete(CUSTOMER_CACHE + id);

        // Send event
        CustomerEvent event = CustomerEvent.builder()
                .eventType("CUSTOMER_DELETED")
                .data(null)
                .build();

        kafkaTemplate.send("customer-deleted", event);
    }
}
