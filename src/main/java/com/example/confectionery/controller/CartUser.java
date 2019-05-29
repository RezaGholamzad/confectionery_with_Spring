package com.example.confectionery.controller;

import com.example.confectionery.domain.Cart;
import com.example.confectionery.domain.OrderRegistration;
import com.example.confectionery.domain.User;
import com.example.confectionery.repository.CartRepository;
import com.example.confectionery.repository.OrderRepository;
import com.example.confectionery.repository.UserRepository;
import com.example.confectionery.service.ServiceDuplicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class CartUser {
	private UserRepository userRepository;
	private CartRepository cartRepository;
	private ServiceDuplicate serviceDuplicate;
	private OrderRepository orderRepository;


	@Autowired
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Autowired
	public void setServiceDuplicate(ServiceDuplicate serviceDuplicate) {
		this.serviceDuplicate = serviceDuplicate;
	}

	@Autowired
	public void setCartRepository(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/cart")
	public String cart(HttpSession session,
					   Model model,
					   @RequestParam @Nullable String dayOrHourIsEmpty){

		if (userRepository.findById(String.valueOf(session.getAttribute("userEmail"))).isPresent()){
			User user = userRepository.findById(String.valueOf(session.getAttribute("userEmail"))).get();


			if (!cartRepository.findCartsByUserEmail(user.getEmail()).isEmpty()){
				List<Cart> carts = cartRepository.findCartsByUserEmail(user.getEmail());
				Comparator<Cart> compareByShop = Comparator.comparing((Cart cart) -> cart.getProduct().getShop().getShopName());
				carts.sort(compareByShop);

				model.addAttribute("carts",carts);

				model.addAttribute("price",serviceDuplicate.calculateTotalOrderAmount(carts));
			}

			//The day and the hours are not empty for when the order is registered
			if (dayOrHourIsEmpty != null){
				model.addAttribute("dayOrHourIsEmpty","true");
			}

//			if (!cartRepository.findCartsOrderedByUserEmail(user.getEmail()).isEmpty()){
//				List<Cart> cartsOrdered = cartRepository.findCartsOrderedByUserEmail(user.getEmail());

//				List<OrderRegistration> orderRegistrations = orderRepository.getOrderByUserEmail(user.getEmail());
//				model.addAttribute("orderRegistrations", orderRegistrations);

//			}

			return "cart";
		}
		return "redirect:/";
	}

	@PostMapping("/cartUpdate")
	public String cartUpdate(@RequestParam String number,
							 @RequestParam String codeCart){

		if (cartRepository.findById(codeCart).isPresent()){
			Cart cart = cartRepository.findById(codeCart).get();
			cart.setNumber(Byte.parseByte(number));
			cartRepository.save(cart);
		}
		return "redirect:/cart";
	}

	@PostMapping("/cartDelete")
	public String cartDelete(@RequestParam String codeCart){

		cartRepository.deleteById(codeCart);
		return "redirect:/cart";
	}

	@GetMapping("/recordsOfOrders")
	public String recordsOfOrders(Model model,
								  HttpSession session){

		if (userRepository.findById(String.valueOf(session.getAttribute("userEmail"))).isPresent()) {
			User user = userRepository.findById(String.valueOf(session.getAttribute("userEmail"))).get();
			List<OrderRegistration> orderRegistrations = orderRepository.getOrderByUserEmail(user.getEmail());
			model.addAttribute("orderRegistrations", orderRegistrations);
		}
		return "recordsOfOrders";
	}
}
