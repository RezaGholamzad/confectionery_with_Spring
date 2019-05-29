package com.example.confectionery.service;

import com.example.confectionery.domain.Cart;
import com.example.confectionery.domain.Product;
import com.example.confectionery.domain.Shop;
import com.example.confectionery.repository.ProductRepository;
import com.example.confectionery.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ServiceDuplicate {
	private ShopRepository shopRepository;
	private ProductRepository productRepository;

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Autowired
	public void setShopRepository(ShopRepository shopRepository) {
		this.shopRepository = shopRepository;
	}

	public Model getProductByShopEmail(String email, Model model){

			Shop shop = shopRepository.findById(email).get();
			model.addAttribute("shop",shop);
			List<Product> products = productRepository.findByShopEmail(email);
			model.addAttribute("products",products);
			return model;

	}

	public byte getnumberMonthByNameMonth(String nameMonth){
		switch (nameMonth) {
			case "FARVARDIN":
				return 1;
			case "ORDIBEHESHT":
				return 2;
			case "KHORDAD":
				return 3;
			case "TIR":
				return 4;
			case "MORDAD":
				return 5;
			case "SHAHRIVAR":
				return 6;
			case "MEHR":
				return 7;
			case"ABAN":
				return 8;
			case "AZAR":
				return 9;
			case "DEY":
				return 10;
			case "BAHMAN":
				return 11;
			case "ESFAND":
				return 12;
			default:
				return 0;
		}
	}

	public int calculateTotalOrderAmount(List<Cart> carts){
		int price = 0;
		for (Cart cart : carts){
			if (cart.getProduct().getDiscountCode() != null){
				price += (cart.getProduct().getPrice() * (100 - cart.getProduct().getDiscountCode().getPercentage())/100) * cart.getNumber();
			}
			else {
				price += cart.getProduct().getPrice() * cart.getNumber();
			}
		}
		return price;
	}

}
