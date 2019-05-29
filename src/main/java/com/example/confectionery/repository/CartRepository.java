package com.example.confectionery.repository;

import com.example.confectionery.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,String> {

	@Query("select cart from Cart cart where cart.user.email = ?1 and cart.order = null")
	List<Cart> findCartsByUserEmail(String userEmail);
}
