package com.electionique.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.electionique.entity.Product;
import com.electionique.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootConfiguration
@SpringBootTest
public class ProductServiceTest {
	@Mock
	private ProductRepository productRepository;
	@InjectMocks
	private ProductService productService;
	private MockMvc mockMvc;
	ObjectMapper mapper = new ObjectMapper();

	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllTest() throws Exception {
		Product product = new Product("1", "Product", "$1200", "Description");
		List<Product> products = new ArrayList<>();
		products.add(product);
		Flux<Product> flux = Flux.fromIterable(products);
		when(productRepository.findAll()).thenReturn(flux);
		assertNotNull(productService.getAll());
	}

	@Test
	public void getById() throws Exception {
		Product product = new Product("1", "Product", "$1200", "Description");
		Mono<Product> mono = Mono.just(product);
		when(productRepository.findById(Mockito.anyString())).thenReturn(mono);
		assertNotNull(productService.getById("1"));
	}

	@Test
	public void updateTest() throws Exception {
		Product product = new Product("1", "Product", "$1200", "Description");
		Mono<Product> mono = Mono.just(product);
		when(productRepository.save(any())).thenReturn(mono);
		assertNotNull(productService.save(product));
	}

	@Test
	public void saveTest() throws Exception {
		Product product = new Product("1", "Product", "$1200", "Description");
		Mono<Product> mono = Mono.just(product);
		when(productRepository.save(any())).thenReturn(mono);
		assertNotNull(productService.save(product));
	}

	@Test
	public void deleteByIdTest() throws Exception {
		Product product = new Product("1", "Product", "$1200", "Description");
		Mono<Void> mono = Mono.empty();
		when(productRepository.delete(product)).thenReturn(mono);
		assertNotNull(productService.delete("1"));
	}
}
