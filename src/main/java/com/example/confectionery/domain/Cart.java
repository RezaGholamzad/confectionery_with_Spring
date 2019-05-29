package com.example.confectionery.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Cart {

	@ManyToOne
	@JoinColumn(name = "userEmail", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "productCode", nullable = false)
	private Product product;

	//To directly access the email in the repository
//	@Column(name = "userEmail", insertable = false, updatable = false)
//	private String userEmail;
//
//	@Column(name = "productCode", insertable = false, updatable = false)
//	private String productCode;

	@ManyToOne
	@JoinColumn(name = "orderCode")
	private OrderRegistration order;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String codeCart;

	private byte number = 1;

}
