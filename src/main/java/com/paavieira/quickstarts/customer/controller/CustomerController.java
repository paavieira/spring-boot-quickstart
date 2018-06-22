package com.paavieira.quickstarts.customer.controller;

import java.util.List;

import com.paavieira.quickstarts.customer.model.Customer;
import com.paavieira.quickstarts.customer.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;

	private CustomerController(CustomerService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseBody
	public Customer postCreate(@RequestBody(required = true) Customer customer) {
		return service.save(customer);
	}

	@GetMapping
	@ResponseBody
	public List<Customer> getCollection(@RequestParam(value = "firstName", required = false) String firstName, @RequestParam(value = "lastName", required = false) String lastName) {
		return service.findAll(firstName, lastName);
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Customer getSingle(@PathVariable("id") String id) {
		return service.findById(id);
	}

	@PostMapping("/{id}")
	@ResponseBody
	public Customer postUpdate(@PathVariable("id") String id, @RequestBody(required = true) Customer customer) {
		return service.update(id, customer.getFirstName(), customer.getLastName());
	}

	@DeleteMapping("/{id}")
	public void deleteSingle(@PathVariable("id") String id) {
		service.delete(id);
	}

	@DeleteMapping
	public void deleteCollection() {
		service.deleteAll();
	}

}
