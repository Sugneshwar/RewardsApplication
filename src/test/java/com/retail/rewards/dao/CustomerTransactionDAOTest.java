package com.retail.rewards.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.retail.rewards.MyTestConfiguration;
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
		MyTestConfiguration testConfiguration = new MyTestConfiguration();
		testConfiguration.init();
		dao.setMyTestConfiguration(testConfiguration);
		dao.init();
		List<CustTransaction> customersData = dao.getCustomerData(12345);
		log.debug("customersData in test class: "+customersData);
		assertTrue(customersData != null);
		assertTrue(customersData.size() == 9);
	}

}
