package com.kalyan.reactivewebflux.mapper;

import com.kalyan.reactivewebflux.dto.ProductDto;
import com.kalyan.reactivewebflux.entity.Product;
import org.springframework.beans.BeanUtils;

public class ProductMapper {

    public static ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
