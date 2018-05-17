package com.paavieira.quickstarts;

import java.util.Arrays;

import com.paavieira.quickstarts.customer.model.Customer;
import com.paavieira.quickstarts.customer.persistence.CustomerRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}

    @Bean
    CommandLineRunner init(CustomerRepository customerRepository) {
		return (args) -> Arrays.asList("Smith,Adam;Marx,Karl".split(";"))
			.forEach(fullName -> {
				final String[] names = fullName.split(",");
				customerRepository.save(new Customer(names[1], names[0]));
			});
    }

}
