package com.retail.rewards.service;

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

import com.retail.rewards.dao.CustomerTransactionDAO;
import com.retail.rewards.model.CustTransaction;

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
		for(Integer key : setOfKeySet) {
			String monthName = getMonthName(key+1);
			Double rewardsperMonth = new Double(0);
            for(CustTransaction customerTranInMonth : custDataPerMonth.get(key)) {
            	 log.debug("customerTranInMonth in service class :"+customerTranInMonth);
                if(customerTranInMonth.getTransactionAmount()>50) {
                	rewardsperMonth += (double) (50*1);
                }
                if(customerTranInMonth.getTransactionAmount()>100) {
                	rewardsperMonth += (double) ((customerTranInMonth.getTransactionAmount()-100)*2);
                }
            }
            log.debug("rewardsperMonth in service class :"+rewardsperMonth);
            rewardsPerMonthMap.put(monthName, df.format(rewardsperMonth));
        }
		return rewardsPerMonthMap;
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
	

}
