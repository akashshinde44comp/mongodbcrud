package com.electionique.service;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.electionique.entity.Product;
import com.electionique.repository.ProductRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@AllArgsConstructor
public class ProductService {

	@Autowired
	private ProductRepository registrationRepository;

	public Flux<Product> getAll() {
		return registrationRepository.findAll().switchIfEmpty(Flux.empty());
	}

	public Mono<Product> getById(final String id) {
		return registrationRepository.findById(id);
	}

	public Mono update(final String id, final Product Product) {
		return registrationRepository.save(Product);
	}

	public Mono save(final Product Product) {
		return registrationRepository.save(Product);
	}
	
	public Mono delete(final String id) {
		final Mono<Product> dbProduct = getById(id);
		if (Objects.isNull(dbProduct)) {
			return Mono.empty();
		}
		return getById(id).switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(ProductToBeDeleted -> registrationRepository
				.delete(ProductToBeDeleted).then(Mono.just(ProductToBeDeleted)));
	}
}
