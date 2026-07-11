package com.pr.productservice.service;

import com.pr.productservice.dto.CreateProductRequest;
import com.pr.productservice.dto.ProductResponse;
import com.pr.productservice.dto.UpdateProductRequest;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductResponse createProduct(CreateProductRequest request);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(UUID id);

    ProductResponse updateProduct(UUID id, UpdateProductRequest request);

    void deleteProduct(UUID id);
}
