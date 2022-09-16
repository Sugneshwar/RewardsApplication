package com.retail.rewards.model;

import java.util.Map;

import lombok.Data;

@Data
public class CustomerResponse {
	private int customerId;
	private Map<String, String> perMonthRewards;
}
