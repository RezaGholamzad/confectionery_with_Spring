package com.example.confectionery.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Data
@Entity
@NoArgsConstructor
public class Shop {

	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "shop",orphanRemoval = true)
	private List<ShopPhone> shopPhones = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "shop",orphanRemoval = true)
	private List<DiscountCode> discountCodes = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "shop",orphanRemoval = true)
	private List<Product> products = new ArrayList<>();

	public void addPhone(ShopPhone shopPhone) {
		shopPhones.add(shopPhone);
		shopPhone.setShop(this);
	}

	public void removePhone(ShopPhone shopPhone) {
		shopPhones.remove(shopPhone);
		shopPhone.setShop(null);
	}

	@Email
	@Id
	@Size(max = 100 , min = 3, message = "{validation.field.notValid}")
	private String email;

	@Size(max = 60,min = 1,message = "{validation.field.notValid}")
	private String shopName;

	@Min(value = 1,message = "{validation.field.notValid}")
	private String password;

	@Size(max = 60, min = 2, message = "{validation.field.notValid}")
	private String state, city;

	@Size(max = 200, min = 5, message = "{validation.field.notValid}")
	private String address;

	@Size(max = 10,min = 10,message = "{validation.field.notValid}")
	private String businessLicense;

	@Size(max = 400, message = "{validation.field.notValid}")
	private String explanation;

	@Size(min = 1,message = "{validation.field.notValid}")
	private String businessLicenseImageAddress,shopImageAddress;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date dateOfRegistration;

	private boolean deliveryMan = false;

	private byte score = 0;

	private int numberOfVisits = 0;

	private boolean validate = false;

	//custom validation
	private String nationalCode;

	@Size(min = 1,message = "{validation.field.notValid}")
	private String descriptionOfWorkingHours ;

	@Transient
	@Size(min = 11,message = "{validation.field.notValid}")
	private String phone;

	@Transient
	private String confirmPassword;

	@Transient
	@Lob
	private MultipartFile businessLicenseImage,shopImage;

}
