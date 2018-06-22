package com.paavieira.quickstarts.customer;

import static org.assertj.core.api.Assertions.assertThat;

import com.paavieira.quickstarts.customer.model.Customer;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void get_returnsCustomer() throws Exception {

		// arrange

		// act
		ResponseEntity<Customer[]> response = restTemplate.getForEntity("/customers?firstName=Lionel",
			Customer[].class);

		// assert
		final HttpStatus statusCode = response.getStatusCode();
		final Customer[] body = response.getBody();
		assertThat(statusCode).isEqualTo(HttpStatus.OK);
		assertThat(body.length).isEqualTo(1);
		assertThat(body[0].getFirstName()).isEqualTo("Lionel");
		assertThat(body[0].getLastName()).isEqualTo("Messi");
	}

}