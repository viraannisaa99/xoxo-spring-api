package com.example.xoxo.serviceimpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.omg.CORBA.INTERNAL;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.multipart.MultipartFile;

import com.example.xoxo.models.Product;
import com.example.xoxo.controllers.ProductController;
import com.example.xoxo.message.ResponseMessage;
import com.example.xoxo.repository.ProductRepository;
import com.example.xoxo.service.ProductService;
import com.sun.prism.impl.Disposer.Record;

import javassist.tools.web.BadHttpRequest;

@Service(value = "productService")

public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts(@RequestParam(required = false) String name) {
		List<Product> products = new ArrayList<Product>();
		if (name == null) {
			productRepository.findAll().forEach(products::add);
		}else { 
			productRepository.findByNameContaining(name).forEach(products::add);
		}

		return products;
	}

	//get product by id
	public Product getProductById(@PathVariable("id") long id) {
		Optional<Product> productData = productRepository.findById(id);
		return productData.get();
	}

	public Product createProduct(@RequestBody Product product) {
		Product products = productRepository.save(product);
		return products;
	}

	public void deleteProduct(@PathVariable("id") long id) {
		productRepository.deleteById(id);
	}

	//delete all product
	public void deleteAllProducts() {
		productRepository.deleteAll();
	}

	//update product
	public Product updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
		Optional<Product> productData = productRepository.findById(id);

		if (productData.isPresent()) {
			Product products = productData.get();
			products.setName(product.getName());
			products.setPrice(product.getPrice());
			products.setDescription(product.getDescription());
			return productRepository.save(products);
		} else {
			return productData.get();
		}
	}

}
