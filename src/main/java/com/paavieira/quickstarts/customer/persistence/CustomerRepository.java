package com.paavieira.quickstarts.customer.persistence;

import java.util.List;

import com.paavieira.quickstarts.customer.model.Customer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	public Customer findByFirstName(String firstName);

	public List<Customer> findByLastName(String lastName);

}