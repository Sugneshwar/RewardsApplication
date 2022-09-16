package com.retail.rewards.model;

import java.util.Date;

import lombok.Data;

@Data
public class CustTransaction {

	private int customerId;
	private double transactionAmount;
	private Date transactionDate;

}
