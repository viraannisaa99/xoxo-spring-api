package com.example.xoxo.repository;

import java.util.List;
import com.example.xoxo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByNameContaining(String name);
}
