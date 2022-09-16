package com.retail.rewards;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.retail.rewards.model.CustTransaction;

import lombok.Data;
@Component
@Data
public class MyTestConfiguration {
	ConcurrentHashMap<Integer, List<CustTransaction>> data = new ConcurrentHashMap<>();
	@PostConstruct
	public void init() {
		int id = 12345;
		buildCustomerTransaction(id);
		buildCustomerTransaction1(++id);
		buildCustomerTransaction2(++id);
	}
	
	private void buildCustomerTransaction(int id) {
		data.put(id, new ArrayList<>());
		Calendar calendar = Calendar.getInstance();
		data.get(id).add(buildTransaction(id, 120.23, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 10.43, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 30.00, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		data.get(id).add(buildTransaction(id, 150.73, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 50.73, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 60.70, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		data.get(id).add(buildTransaction(id, 50.73, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 150.00, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 30.73, calendar.getTime()));
	}
	
	
	private void buildCustomerTransaction1(int id) {
		data.put(id, new ArrayList<>());
		Calendar calendar = Calendar.getInstance();
		data.get(id).add(buildTransaction(id, 120.23, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 10.43, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 130.00, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		data.get(id).add(buildTransaction(id, 150.73, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 50.73, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 150.55, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		data.get(id).add(buildTransaction(id, 80.30, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 50.00, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 130.73, calendar.getTime()));
	}

	private void buildCustomerTransaction2(int id) {
		data.put(id, new ArrayList<>());
		Calendar calendar = Calendar.getInstance();
		data.get(id).add(buildTransaction(id, 20.00, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 50.43, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 130.00, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		data.get(id).add(buildTransaction(id, 50.73, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 80.35, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 90.35, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		data.get(id).add(buildTransaction(id, 70.73, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 80.00, calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 130.73, calendar.getTime()));
	}

	private CustTransaction buildTransaction(Integer id, Double amount, Date date) {
		CustTransaction custTransaction = new CustTransaction();
		custTransaction.setCustomerId(id);
		custTransaction.setTransactionAmount(amount);
		custTransaction.setTransactionDate(date);
		return custTransaction;
	}


}
