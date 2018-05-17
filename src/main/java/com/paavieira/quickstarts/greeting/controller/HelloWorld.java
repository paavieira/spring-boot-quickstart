package com.paavieira.quickstarts.greeting.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.paavieira.quickstarts.greeting.model.Greeting;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/greeting")
@EnableAutoConfiguration
public class HelloWorld {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping
	@ResponseBody
	public Greeting hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

}
