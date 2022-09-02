package com.retail.rewards.model;

import java.util.concurrent.ConcurrentHashMap;

import lombok.Data;

@Data
public class CustomerResponse {
	int customerId;
	ConcurrentHashMap<String, String> perMonthRewards;
}
