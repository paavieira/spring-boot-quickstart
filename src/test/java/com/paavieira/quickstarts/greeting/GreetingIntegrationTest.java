package com.paavieira.quickstarts.greeting;

import static org.assertj.core.api.Assertions.assertThat;

import com.paavieira.quickstarts.greeting.model.Greeting;

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
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GreetingIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void hello_returnsHelloWorld() throws Exception {

		// arrange

		// act
		ResponseEntity<Greeting> response = restTemplate.getForEntity("/greeting", Greeting.class);

		// assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getContent()).isEqualTo("Hello, World!");
	}

}