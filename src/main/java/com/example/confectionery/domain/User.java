package com.example.confectionery.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class User {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
	private List<Cart> carts = new ArrayList<>();

	public void addCart(Cart cart) {
		carts.add(cart);
		cart.setUser(this);
	}

	public void removeCart(Cart cart) {
		carts.remove(cart);
		cart.setProduct(null);
	}

	@Email
	@Id
	@Size(max = 100, min = 3, message = "{validation.field.notValid}")
	private String email;

	@Size(max = 60, min = 1, message = "{validation.field.notValid}")
	private String firstName, lastName;

	@Min(value = 1, message = "{validation.field.notValid}")
	private String password;

	@Size(max = 11, min = 11, message = "{validation.phone.notNull}")
	private String phone;

	@Size(max = 60, min = 1, message = "فیلد بالا ضروری")
	private String state, city;

	@Size(max = 200)
	private String address;

	//custom validation
	private String nationalCode;

	@Transient
	private String confirmPassword;
}
