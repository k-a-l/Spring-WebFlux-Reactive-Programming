package com.kalyan.reactivewebflux.service;

import com.kalyan.reactivewebflux.dto.ProductDto;
import com.kalyan.reactivewebflux.mapper.ProductMapper;
import com.kalyan.reactivewebflux.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Flux<ProductDto> getAllProducts() {
        return productRepository.findAll().map(ProductMapper::entityToDto);
    }

    public Mono<ProductDto> getProductById(String id) {
        return productRepository.findById(id)
                .map(ProductMapper::entityToDto)
                .onErrorResume(e->Mono.error(new RuntimeException("Product not found")));

    }
    public Mono<ProductDto> createProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono
                .map(ProductMapper::dtoToEntity)
                .flatMap(productRepository::save)
                .map(ProductMapper::entityToDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id) {
        return productRepository.findById(id)
                .flatMap(existingProduct ->
                        productDtoMono
                                .map(ProductMapper::dtoToEntity)
                                .doOnNext(updated -> updated.setId(id))
                )
                .flatMap(productRepository::save)
                .map(ProductMapper::entityToDto);
    }

    public Mono<?> deleteProduct(String id) {
        return productRepository.findById(id).
                doOnNext(productRepository::delete)
                .onErrorResume(e->Mono.error(new RuntimeException("Product not found")));

    }
}




