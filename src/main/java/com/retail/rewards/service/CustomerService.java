package com.retail.rewards.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
	ConcurrentHashMap<Integer, List<CustTransaction>> dataPerMonth = new ConcurrentHashMap<>();
	
	@Autowired
	CustomerTransactionDAO customerDao;

	public ConcurrentHashMap<String, String> caclulateRewards(int customerId) {
		List<CustTransaction> customerData = customerDao.getCustomerData(customerId);
		if(customerData != null) {
			buildDataPerMonth(customerData);
			ConcurrentHashMap<String, String> rewardsPerMonth = performOperation(dataPerMonth);
			return rewardsPerMonth;
		}else {
			return new ConcurrentHashMap<>();
		}
	}

	private ConcurrentHashMap<String, String> performOperation(ConcurrentHashMap<Integer, List<CustTransaction>> custDataPerMonth) {
		Set<Integer> setOfKeySet = custDataPerMonth.keySet();
		ConcurrentHashMap<String, String> rewardsPerMonthMap = new ConcurrentHashMap<>();
		DecimalFormat df = new DecimalFormat("0.00");
		
		setOfKeySet.forEach(key -> {
			String monthName = getMonthName(key+1);
			Double rewardsperMonth = 0d;
			rewardsperMonth = custDataPerMonth.get(key)
				.stream()
				.mapToDouble(customerTranInMonth -> this.computeRewards(customerTranInMonth.getTransactionAmount()))
				.sum();
				 
			log.debug("rewardsperMonth in service class :"+rewardsperMonth);
            rewardsPerMonthMap.put(monthName, df.format(rewardsperMonth.doubleValue()));
		});
		 
		return rewardsPerMonthMap;
	}

	public double computeRewards( double amount) {
		 
		double rewardsperMonth =0;
		if( amount > 50) {
			
			rewardsperMonth  +=  (double) (50*1);
			//rewardsperMonth = rewardsperMonth.add( BigDecimal.valueOf( (double) (50*1)));
        }
        if( amount > 100) {
        	rewardsperMonth += ( (double) ((amount-100)*2));
        	//rewardsperMonth = rewardsperMonth.add(BigDecimal.valueOf( (double) ((amount-100)*2)));
        } 
        return rewardsperMonth;
	}
	
	private void buildDataPerMonth(List<CustTransaction> customerData) {
		for(CustTransaction custTransaction: customerData) {
			log.debug("custTransaction in buildingData :"+custTransaction);
			Date date = custTransaction.getTransactionDate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int month = cal.get(Calendar.MONTH);
			if(dataPerMonth.get(month)!= null) {
				dataPerMonth.get(month).add(custTransaction);
			}else {
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
		CustomerResponse customerResponse = new CustomerResponse();
		ConcurrentHashMap<String, String> customerMonthlyRewards= caclulateRewards(customerId);
		if(customerMonthlyRewards.isEmpty()) {
			throw new CustomerNotFoundException();
		}
		customerResponse.setCustomerId(customerId);
		customerResponse.setPerMonthRewards(customerMonthlyRewards);
		log.debug("CustomerResponse :"+customerResponse);
		return customerResponse;
	}
	

}
