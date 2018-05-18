package com.paavieira.quickstarts.configuration;

import java.util.Arrays;

import com.paavieira.quickstarts.customer.model.Customer;
import com.paavieira.quickstarts.customer.persistence.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Example;

@Configuration
@Profile("development")
public class DevelopmentConfiguration {

	@Autowired
	private CustomerRepository customerRepository;

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Arrays.asList("Smith,Adam;Marx,Karl".split(";")).forEach(fullName -> {
			final String[] names = fullName.split(",");
			final Customer customer = new Customer(names[1], names[0]);
			if (!customerRepository.exists(Example.of(customer))) {
				customerRepository.save(customer);
			}
		});
	}

}