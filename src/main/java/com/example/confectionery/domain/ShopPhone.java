package com.example.confectionery.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class ShopPhone {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@ManyToOne
	@JoinColumn(name = "shopEmail", nullable = false)
	private Shop shop;

	private String phone;

	public ShopPhone(String phone) {
		this.phone = phone;
	}
}
