package com.retail.rewards.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void getRewardsForCustomer() throws Exception 
	{
	  mvc.perform( MockMvcRequestBuilders
	      .get("/rewards/12345/rewardPoints")
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.perMonthRewards").exists());
	}
	
	@Test
	public void getRewardsForCustomerNotExists() throws Exception 
	{
	  mvc.perform( MockMvcRequestBuilders
	      .get("/rewards/12348/rewardPoints")
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isBadRequest())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.status").exists());
	}
}
