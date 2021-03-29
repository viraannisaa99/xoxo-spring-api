package com.example.xoxo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.xoxo.models.Product;
import com.example.xoxo.service.ProductService;


@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProductController {
	@Autowired
	ProductService productService;

	//get all product
	@GetMapping("/products")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String name) {
		try {
			if(productService.getAllProducts(name).isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(productService.getAllProducts(name), HttpStatus.OK);
			}
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//get product by id
	@GetMapping("/products/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//create product
	@PostMapping("/products/")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		try {
			return new ResponseEntity<>(productService.createProduct(product), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//delete product
	@DeleteMapping("/products/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") long id) {
		try {
			productService.deleteProduct(id);
			return new ResponseEntity<>("Successfully Deleted Product", HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//delete all product
	@DeleteMapping("/products")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<String> deleteAllProducts() {
		try {
			productService.deleteAllProducts();
			return new ResponseEntity<>("Successfully Deleted Product", HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//update product
	@PutMapping("/products/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
		try {
			return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


}
