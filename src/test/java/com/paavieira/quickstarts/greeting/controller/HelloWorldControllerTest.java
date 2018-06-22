package com.paavieira.quickstarts.greeting.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void hello_ShouldReturnHelloWorld() throws Exception {
		mockMvc.perform(get("/greeting"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("content").value("Hello, World!"));
	}

	@Test
	public void hello_ShouldReturnHelloCustomName() throws Exception {
		mockMvc.perform(get("/greeting").param("name", "Karl"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("content").value("Hello, Karl!"));
	}

}