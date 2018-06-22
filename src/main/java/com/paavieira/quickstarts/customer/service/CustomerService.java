package com.paavieira.quickstarts.customer.service;

import java.util.List;
import java.util.Optional;

import com.paavieira.quickstarts.customer.model.Customer;
import com.paavieira.quickstarts.customer.persistence.CustomerRepository;
import com.paavieira.quickstarts.exception.BadRequestException;
import com.paavieira.quickstarts.exception.ConflictException;
import com.paavieira.quickstarts.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	private CustomerRepository repository;

	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}
	
	public Customer save(Customer customer) {

		if (customer.isNameInvalid()) {
			throw new BadRequestException();
		}

		if (exists(customer.getFirstName(), customer.getLastName())) {
			throw new ConflictException();
		}

		return repository.save(customer);
	}

	public List<Customer> findAll(String firstName, String lastName) {
		final Customer example = new Customer(firstName, lastName);
		return repository.findAll(Example.of(example));
	}

	public Customer findById(String id) {
		final Optional<Customer> customer = repository.findById(id);
		if (customer.isPresent()) {
			return customer.get();
		} else {
			throw new NotFoundException();
		}
	}

	public Customer update(String id, String firstName, String lastName) {
		final Customer existingCustomer = findById(id);
		existingCustomer.setFirstName(firstName);
		existingCustomer.setLastName(lastName);
		return save(existingCustomer);
	}

	public boolean exists(final String firstName, final String lastName) {
		final Customer example = new Customer(firstName, lastName);
		return repository.count(Example.of(example)) > 0;
	}

	public void delete(String id) {
		final Customer customer = findById(id);
		repository.delete(customer);
	}

	public void deleteAll() {
		repository.deleteAll();
	}

}
