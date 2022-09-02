package com.retail.rewards.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.retail.rewards.model.CustTransaction;

import lombok.extern.slf4j.Slf4j;
@SpringJUnitConfig
@Slf4j
class CustomerTransactionDAOTest {

	@InjectMocks
	CustomerTransactionDAO customerTransactionDAO;
	
	@Test
	void testGetCustomerData() {
		CustomerTransactionDAO dao = new CustomerTransactionDAO();
		dao.init();
		List<CustTransaction> customersData = dao.getCustomerData(12345);
		log.debug("customersData in test class: "+customersData);
	}

}
