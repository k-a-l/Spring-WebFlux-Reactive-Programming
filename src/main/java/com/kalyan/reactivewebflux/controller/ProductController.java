package com.kalyan.reactivewebflux.controller;

import com.kalyan.reactivewebflux.dto.ProductDto;
import com.kalyan.reactivewebflux.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Flux<ProductDto> getAllProducts() {
        return productService.getAllProducts();

    }
    @GetMapping("/{id}")
    public Mono<ProductDto> getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductDto> createProduct(@RequestBody Mono<ProductDto> productDto) {
        return productService.createProduct(productDto);
    }

    @PutMapping("/{id}")
    public Mono<ProductDto> updateProduct(@PathVariable String id, @RequestBody Mono<ProductDto> productDto) {
        return productService.updateProduct(productDto, id);

    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("delete/{id}")
    public Mono<?> deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }

}
