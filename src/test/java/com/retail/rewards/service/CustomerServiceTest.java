package com.retail.rewards.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.retail.rewards.dao.CustomerTransactionDAO;
import com.retail.rewards.model.CustTransaction;

import lombok.extern.slf4j.Slf4j;
@SpringJUnitConfig
@Slf4j
class CustomerServiceTest {
	@InjectMocks
	CustomerService customerService;
	
	@Mock
	CustomerTransactionDAO customerDao;

	@Test
	void testCaclulateRewards() {
		List<CustTransaction> customerData = buidTestData(12345);
		Mockito.when(customerDao.getCustomerData(Mockito.anyInt())).thenReturn(customerData);
		ConcurrentHashMap<String, String> rewardsPerMonth = customerService.caclulateRewards(12345);
		log.debug("rewardsPerMonth in test class :"+rewardsPerMonth);
		assertTrue(rewardsPerMonth != null);
	}
	
	private List<CustTransaction> buidTestData(int id) {
		ConcurrentHashMap<Integer, List<CustTransaction>> data = new ConcurrentHashMap<>();
		data.put(id, new ArrayList<>());
		Calendar calendar = Calendar.getInstance();
		data.get(id).add(buildTransaction(id, 120.23,calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 10.43,calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 30.00,calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		data.get(id).add(buildTransaction(id, 150.73,calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 50.73,calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 60.70,calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		data.get(id).add(buildTransaction(id, 50.73,calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 150.00,calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		data.get(id).add(buildTransaction(id, 130.73,calendar.getTime()));
		return data.get(id);
	}
	
	private CustTransaction buildTransaction(Integer id, Double amount, Date date) {
		CustTransaction custTransaction = new CustTransaction();
		custTransaction.setCustomerId(id);
		custTransaction.setTransactionAmount(amount); 	
		custTransaction.setTransactionDate(date);
		return custTransaction;
	}

}
