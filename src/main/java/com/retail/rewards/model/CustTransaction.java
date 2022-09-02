package com.retail.rewards.model;

import java.util.Date;

import lombok.Data;

@Data
public class CustTransaction {
	
	int customerId;
	double transactionAmount;
	Date transactionDate;
	

}
