package com.electionique.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.electionique.entity.Product;
import com.electionique.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebMvcTest
@AutoConfigureMockMvc
public class ProductControllerTest {
	@Mock
	private ProductService productService;
	@InjectMocks
	private ProductController productController;
	private MockMvc mockMvc;
	ObjectMapper mapper = new ObjectMapper();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllTest() throws Exception {
		Product product = new Product("1", "Product", "$1200", "Description");
		List<Product> products = new ArrayList<>();
		products.add(product);
		Flux<Product> flux = Flux.fromIterable(products);
		when(productService.getAll()).thenReturn(flux);
		mockMvc.perform(get("/api/products/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getById() throws Exception {
		Product product = new Product("1", "Product", "$1200", "Description");
		Mono<Product> mono = Mono.just(product);
		when(productService.getById(any())).thenReturn(mono);
		mockMvc.perform(get("/api/products/{id}", "12").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void updateByIdTest() throws Exception {
		Product product = new Product("1", "Product", "$1200", "Description");
		String requestJson = mapper.writeValueAsString(product);
		Mono<Product> mono = Mono.just(product);
		when(productService.getById(any())).thenReturn(mono);
		mockMvc.perform(get("/api/products/{id}", "12").content(requestJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void saveTest() throws Exception {
		Product product = new Product("1", "Product", "$1200", "Description");
		String requestJson = mapper.writeValueAsString(product);
		Mono<Product> mono = Mono.just(product);
		when(productService.getById(any())).thenReturn(mono);
		mockMvc.perform(get("/api/products/").content(requestJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteByIdTest() throws Exception {
		Product product = new Product("1", "Product", "$1200", "Description");
		Mono<Product> mono = Mono.just(product);
		when(productService.delete(any())).thenReturn(mono);
		mockMvc.perform(get("/api/products/{id}", "12").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
