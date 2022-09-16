package com.retail.rewards.dao;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.retail.rewards.MyTestConfiguration;
import com.retail.rewards.model.CustTransaction;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Data
public class CustomerTransactionDAO {

	ConcurrentHashMap<Integer, List<CustTransaction>> data = new ConcurrentHashMap<>();
	
	@Autowired
	MyTestConfiguration myTestConfiguration;

	@PostConstruct
	public void init() {
		data = myTestConfiguration.getData();
	}

	public List<CustTransaction> getCustomerData(Integer customerId) {
		log.debug("customerId: " + customerId);
		log.debug("data for " + customerId + " :" + data.get(customerId));
		return data.get(customerId);
	}
 
}
