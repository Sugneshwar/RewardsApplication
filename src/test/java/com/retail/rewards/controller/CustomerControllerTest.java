package com.retail.rewards.controller;
 
import static org.junit.Assert.assertTrue;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.retail.rewards.model.CustomerResponse;
import com.retail.rewards.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@SpringJUnitConfig
@Slf4j
public class CustomerControllerTest {
	 
	@InjectMocks
	CustomerController customerController;
	
	@Mock
	CustomerService customerService;
	  
	@Test
	void testCalculateRewards() {
		ConcurrentHashMap<String, String> customerMonthlyRewards= new ConcurrentHashMap<>();
		Mockito.when(customerService.caclulateRewards(Mockito.anyInt())).thenReturn(customerMonthlyRewards);
		CustomerResponse customerResponse= customerController.calculateRewards(12345);
		log.debug("customerResponse.getCustomerId() in test class :"+customerResponse.getCustomerId());
		assertTrue(customerResponse.getCustomerId() == 12345);
	}

}
