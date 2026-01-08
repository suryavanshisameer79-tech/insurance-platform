package com.maximus.insurance.customer_service;

import com.maximus.insurance.customer_service.controller.CustomerController;
import com.maximus.insurance.customer_service.dto.CustomerResponseDto;
import com.maximus.insurance.customer_service.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.List;

@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
class CustomerServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerService customerService;

	@Test
	void contextLoads() {
	}

    @Test
    void getAllCustomers_success() throws Exception {

        List<CustomerResponseDto> customers = List.of(
                new CustomerResponseDto(
                        1L, "Sameer", "Suryavanshi",
                        "sameer@gmail.com", "9876543210",
                        "MG Road", "Pune", "MH", "411001"
                ),
                new CustomerResponseDto(
                        2L, "John", "Doe",
                        "john@gmail.com", "9123456789",
                        "Park Street", "Mumbai", "MH", "400001"
                )
        );

        Mockito.when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/customers/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("Sameer"))
                .andExpect(jsonPath("$[0].city").value("Pune"));;
    }

}
