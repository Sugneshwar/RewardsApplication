package com.retail.rewards.service;

import java.text.DecimalFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.retail.rewards.Exception.CustomerNotFoundException;
import com.retail.rewards.dao.CustomerTransactionDAO;
import com.retail.rewards.model.CustTransaction;
import com.retail.rewards.model.CustomerResponse;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Data
public class CustomerService {
	

	@Autowired
	CustomerTransactionDAO customerDao;

	private Map<String, String> caclulateRewards(int customerId,Map<Integer, List<CustTransaction>> dataPerMonth) {
		List<CustTransaction> customerData = customerDao.getCustomerData(customerId);
		if (customerData != null) {
			buildDataPerMonth(customerData, dataPerMonth);
			Map<String, String> rewardsPerMonth = performOperation(dataPerMonth);
			return rewardsPerMonth;
		} else {
			return new HashMap<>();
		}
	}

	private Map<String, String> performOperation(
			Map<Integer, List<CustTransaction>> custDataPerMonth) {
		Set<Integer> setOfKeySet = custDataPerMonth.keySet();
		Map<String, String> rewardsPerMonthMap = new HashMap<>();
		DecimalFormat df = new DecimalFormat("0.00");

		setOfKeySet.forEach(key -> {
			String monthName = getMonthName(key + 1);
			Double rewardsperMonth = 0d;
			rewardsperMonth = custDataPerMonth.get(key).stream()
					.mapToDouble(customerTranInMonth -> this.computeRewards(customerTranInMonth.getTransactionAmount()))
					.sum();

			log.debug("rewardsperMonth in service class :" + rewardsperMonth);
			rewardsPerMonthMap.put(monthName, df.format(rewardsperMonth.doubleValue()));
		});

		return rewardsPerMonthMap;
	}

	private double computeRewards(double amount) {
		double rewardsperMonth = 0;
		if (amount > 50) {
			rewardsperMonth += (double) (50 * 1);
		}
		if (amount > 100) {
			rewardsperMonth += ((double) ((amount - 100) * 2));
		}
		return rewardsperMonth;
	}

	private void buildDataPerMonth(List<CustTransaction> customerData,Map<Integer, List<CustTransaction>> dataPerMonth) {
		for (CustTransaction custTransaction : customerData) {
			log.debug("custTransaction in buildingData :" + custTransaction);
			Date date = custTransaction.getTransactionDate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int month = cal.get(Calendar.MONTH);
			if (dataPerMonth.get(month) != null) {
				dataPerMonth.get(month).add(custTransaction);
			} else {
				List<CustTransaction> newMonthList = new ArrayList<>();
				newMonthList.add(custTransaction);
				dataPerMonth.put(month, newMonthList);
			}
		}
	}

	private String getMonthName(int month) {
		if (month <= 0 || month > 12) {
			return null;
		}
		return Month.of(month).getDisplayName(TextStyle.FULL, new Locale("us"));
	}

	public CustomerResponse getRewards(int customerId) {
		Map<Integer, List<CustTransaction>> dataPerMonth = new HashMap<>();
		CustomerResponse customerResponse = new CustomerResponse();
		Map<String, String> customerMonthlyRewards = caclulateRewards(customerId,dataPerMonth);
		if (customerMonthlyRewards.isEmpty()) {
			throw new CustomerNotFoundException();
		}
		customerResponse.setCustomerId(customerId);
		customerResponse.setPerMonthRewards(customerMonthlyRewards);
		log.debug("CustomerResponse :" + customerResponse);
		return customerResponse;
	}

}
