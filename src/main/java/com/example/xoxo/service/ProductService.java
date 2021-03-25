package com.example.xoxo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.xoxo.models.Product;

public interface ProductService {
	ResponseEntity<List<Product>> getAllProducts(String name);
	ResponseEntity<Product> getProductById(long id);
	ResponseEntity<Product> createProduct(Product product);
	ResponseEntity<HttpStatus> deleteProduct(long id);
	ResponseEntity<HttpStatus> deleteAllProducts();
	ResponseEntity<Product> updateProduct(long id, Product product);
}
