package com.retail.rewards.controller;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.rewards.Exception.CustomerNotFoundException;
import com.retail.rewards.model.CustomerResponse;
import com.retail.rewards.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/rewards")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;

	@RequestMapping("/{customerId}")
	public CustomerResponse calculateRewards(@PathVariable int customerId) {
		CustomerResponse CustomerResponse = new CustomerResponse();
		ConcurrentHashMap<String, String> customerMonthlyRewards= customerService.caclulateRewards(customerId);
		if(customerMonthlyRewards.isEmpty()) {
			throw new CustomerNotFoundException();
		}
		CustomerResponse.setCustomerId(customerId);
		CustomerResponse.setPerMonthRewards(customerMonthlyRewards);
		log.debug("CustomerResponse :"+CustomerResponse);
		return CustomerResponse;
	}
}
