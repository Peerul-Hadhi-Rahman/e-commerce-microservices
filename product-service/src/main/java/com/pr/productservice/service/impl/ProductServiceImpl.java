package com.pr.productservice.service.impl;

import com.pr.productservice.dto.CreateProductRequest;
import com.pr.productservice.dto.ProductResponse;
import com.pr.productservice.dto.UpdateProductRequest;
import com.pr.productservice.entity.Product;
import com.pr.productservice.exception.ProductNotFoundException;
import com.pr.productservice.repository.ProductRepository;
import com.pr.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(CreateProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .category(request.getCategory())
                .imageUrl(request.getImageUrl())
                .active(true)
                .build();
        Product savedProduct = productRepository.save(product);
        return ProductResponse.builder()
                .id(savedProduct.getId())
                .name(savedProduct.getName())
                .description(savedProduct.getDescription())
                .price(savedProduct.getPrice())
                .stock(savedProduct.getStock())
                .category(savedProduct.getCategory())
                .imageUrl(savedProduct.getImageUrl())
                .active(savedProduct.isActive())
                .createdAt(savedProduct.getCreatedAt())
                .updatedAt(savedProduct.getUpdatedAt())
                .build();
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .stock(product.getStock())
                        .category(product.getCategory())
                        .imageUrl(product.getImageUrl())
                        .active(product.isActive())
                        .createdAt(product.getCreatedAt())
                        .updatedAt(product.getUpdatedAt())
                        .build())
                .toList();
    }

    @Override
    public ProductResponse getProductById(UUID id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .category(product.getCategory())
                .imageUrl(product.getImageUrl())
                .active(product.isActive())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    @Override
    public ProductResponse updateProduct(UUID id, UpdateProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(request.getCategory());
        product.setImageUrl(request.getImageUrl());
        product.setActive(request.getActive());

        Product updatedProduct = productRepository.save(product);

        return ProductResponse.builder()
                .id(updatedProduct.getId())
                .name(updatedProduct.getName())
                .description(updatedProduct.getDescription())
                .price(updatedProduct.getPrice())
                .stock(updatedProduct.getStock())
                .category(updatedProduct.getCategory())
                .imageUrl(updatedProduct.getImageUrl())
                .active(updatedProduct.isActive())
                .createdAt(updatedProduct.getCreatedAt())
                .updatedAt(updatedProduct.getUpdatedAt())
                .build();
    }

    @Override
    public void deleteProduct(UUID id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        productRepository.delete(product);
    }
}
