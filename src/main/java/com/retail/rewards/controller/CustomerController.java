package com.retail.rewards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.rewards.model.CustomerResponse;
import com.retail.rewards.service.CustomerService;

@RestController
@RequestMapping("/rewards")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@RequestMapping("/{customerId}/rewardPoints")
	public CustomerResponse calculateRewards(@PathVariable int customerId) {
		return customerService.getRewards(customerId);
	}
}
