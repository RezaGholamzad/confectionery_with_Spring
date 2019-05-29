package com.example.confectionery.repository;

import com.example.confectionery.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, String> {

	@Query("select shop from Shop shop where shop.validate = ?1")
	List<Shop> shopsValidate(Boolean state);

	@Query("select shop from Shop shop where shop.state = ?1")
	List<Shop> shopOrderByState(String state);

	@Query("select shop from Shop shop where shop.city = ?1")
	List<Shop> shopOrderByCity(String city);

	@Query("select shop from Shop shop where shop.state = ?1 and shop.city = ?2")
	List<Shop> shopOrderByStateAndCity(String state, String city);

}
