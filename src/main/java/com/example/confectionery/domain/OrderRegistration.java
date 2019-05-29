package com.example.confectionery.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class OrderRegistration {

	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "order",orphanRemoval = true)
	private List<Cart> carts = new ArrayList<>();

	public void addCart(Cart cart) {
		carts.add(cart);
		cart.setOrder(this);
	}

	public void removeCart(Cart cart) {
		carts.remove(cart);
		cart.setOrder(null);
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String orderCode;

	private int price;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date purchaseDate;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date deliveryDate;

	private Boolean deliveryMan = false;

	private Boolean deliver = false;

	private Boolean deleteOrderByShop = false;

}
