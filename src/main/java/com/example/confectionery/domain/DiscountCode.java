package com.example.confectionery.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class DiscountCode {

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productCode")
	private  Product product;


	@ManyToOne
	@JoinColumn(name = "shopEmail")
	private Shop shop;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String  code;


	private byte percentage;

	//A -> Discount on a product
	//B -> Discount on all product
	//C -> Discount on a orderRegistration
	private Character type;

	private short numberLimit = -1;

	public DiscountCode(@Size(max = 100) byte percentage) {
		this.percentage = percentage;
	}

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	public Date expirationDate;
}
