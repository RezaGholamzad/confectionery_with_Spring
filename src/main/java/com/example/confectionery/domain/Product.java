package com.example.confectionery.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Product {

	@ManyToOne
	@JoinColumn(name = "shopEmail", nullable = false)
	private Shop shop;

	public Product(Shop shop) {
		this.shop = shop;
	}

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "product",fetch = FetchType.LAZY,orphanRemoval = true)
	private DiscountCode discountCode;

//	@ManyToMany(mappedBy = "products")
//	private List<User> users = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "product",orphanRemoval = true)
	private List<Cart> carts = new ArrayList<>();

	public void addCart(Cart cart){
		carts.add(cart);
		cart.setProduct(this);
	}
	public void removeCart(Cart cart){
		carts.remove(cart);
		cart.setProduct(null);
	}

	public void setDiscountCode(DiscountCode discountCode) {
		if (discountCode == null) {
			if (this.discountCode != null) {
				this.discountCode.setProduct(null);
			}
		}
		else {
			discountCode.setProduct(this);
		}
		this.discountCode = discountCode;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String code;

	@Size(max = 60)
	private String name;

	private boolean cake = false;

	@Size(max = 400)
	private String explanation;

	private int price = 0;

	private byte numberInKil = 1;

	private String productImageAddress;

	private boolean available = true;

	@Transient
	private MultipartFile productImage;

}
