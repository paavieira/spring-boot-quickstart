package com.paavieira.quickstarts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.paavieira.quickstarts.greeting.controller.HelloWorld;

@SpringBootApplication
public class App {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}

}
