package com.electionique.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electionique.entity.Product;
import com.electionique.service.ProductService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/api/products/")
@AllArgsConstructor
@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping
	public Flux<Product> getAll() {
		return productService.getAll();
	}
	
	@GetMapping("{id}")
	public Mono<Product> getById(@PathVariable("id") final String id) {
		return productService.getById(id);
	}

	@PutMapping("{id}")
	public Mono updateById(@PathVariable("id") final String id, @RequestBody final Product Product) {
		return productService.update(id, Product);
	}

	@PostMapping
	public Mono save(@RequestBody final Product Product) {
		return productService.save(Product);
	}

	@DeleteMapping("{id}")
	public Mono delete(@PathVariable final String id) {
		return productService.delete(id);
	}
}
