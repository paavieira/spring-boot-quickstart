package com.paavieira.quickstarts.customer.resource;

import java.util.List;

import com.paavieira.quickstarts.customer.controller.CustomerController;
import com.paavieira.quickstarts.customer.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@EnableAutoConfiguration
public class CustomerResource {

	@Autowired
	private CustomerController controller;

	@PostMapping
	@ResponseBody
	public Customer postCreate(@RequestBody(required = true) Customer customer) {
		return controller.save(customer);
	}

	@GetMapping
	@ResponseBody
	public List<Customer> getCollection() {
		return controller.findAll();
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Customer getSingle(@PathVariable("id") String id) {
		return controller.findById(id);
	}

	@PostMapping("/{id}")
	@ResponseBody
	public Customer postUpdate(@PathVariable("id") String id, @RequestBody(required = true) Customer customer) {
		return controller.update(id, customer.getFirstName(), customer.getLastName());
	}

	@DeleteMapping("/{id}")
	public void deleteSingle(@PathVariable("id") String id) {
		controller.delete(id);
	}

	@DeleteMapping
	public void deleteCollection() {
		controller.deleteAll();
	}

}
