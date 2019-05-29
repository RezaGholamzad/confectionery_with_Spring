package com.example.confectionery.repository;

import com.example.confectionery.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {

	@Query("SELECT p FROM Product p WHERE p.shop.email = ?1")
	List<Product> findByShopEmail(String shopEmail);
}
