package com.example.confectionery.repository;

import com.example.confectionery.domain.OrderRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderRegistration,String> {
	@Query("select distinct orderRegistration from OrderRegistration orderRegistration inner join orderRegistration.carts cart where cart.order <> null and cart.user.email = ?1")
	List<OrderRegistration> getOrderByUserEmail(String userEmail);

	@Query("select distinct orderRegistration from OrderRegistration orderRegistration inner join orderRegistration.carts cart where cart.order <> null and cart.product.shop.email = ?1 and orderRegistration.deliver =false ")
	List<OrderRegistration> getOrderByShopEmail(String shopEmail);
}
