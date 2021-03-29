package com.example.xoxo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.xoxo.models.Product;

public interface ProductService {
	public List<Product> getAllProducts(String name);
	public Product getProductById(long id);
	public Product createProduct(Product product);
	public void deleteProduct(long id);
	public void deleteAllProducts();
	public Product updateProduct(long id, Product product);
}
