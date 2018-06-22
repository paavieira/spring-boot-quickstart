package com.paavieira.quickstarts.customer.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.paavieira.quickstarts.customer.model.Customer;
import com.paavieira.quickstarts.customer.service.CustomerService;
import com.paavieira.quickstarts.exception.NotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerService customerService;

	@Test
	public void getSingle_ShouldReturnCustomer() throws Exception {
		given(customerService.findById(Mockito.anyString()))
			.willReturn(new Customer("Karl", "Marx"));
		mockMvc.perform(get("/customers/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("firstName").value("Karl"))
			.andExpect(jsonPath("lastName").value("Marx"));			
	}

	@Test
	public void getSingle_CustomerNotFound() throws Exception {
		given(customerService.findById(Mockito.anyString()))
			.willThrow(new NotFoundException());
		mockMvc.perform(get("/customers/1"))
			.andExpect(status().isNotFound());
	}

}