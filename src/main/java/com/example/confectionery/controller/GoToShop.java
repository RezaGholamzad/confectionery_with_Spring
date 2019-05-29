package com.example.confectionery.controller;

import com.example.confectionery.domain.Cart;
import com.example.confectionery.domain.Product;
import com.example.confectionery.domain.User;
import com.example.confectionery.repository.CartRepository;
import com.example.confectionery.repository.ProductRepository;
import com.example.confectionery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class GoToShop {

	private UserRepository userRepository;
	private ProductRepository productRepository;
	private CartRepository cartRepository;

	@Autowired
	public void setCartRepository(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostMapping("/loginUserInGoToShop")
	public String loginUserInGoToShop(@RequestParam String email,
									  @RequestParam String password,
									  HttpServletRequest request,
									  @RequestParam String shopEmail){
		//check that the user exists
		if (userRepository.findById(email).isPresent()){

			//Get user information from repository
			User user = userRepository.findById(email).get();

			//check whether that the password entered for the correct email is valid
			if (user.getPassword().equals(password)){

				//set attribute email for session
				request.getSession().setAttribute("userEmail", email);

				request.getSession().setMaxInactiveInterval(60 * 60);// one hour

				return "redirect:/goToShop?shopEmail=" + shopEmail;
			}
		}
		return "redirect:/loginPage";
	}

	@PostMapping("/addToCart")
	public String addToCart(@RequestParam String productCode,
							@RequestParam String number,
							@RequestParam String shopEmail,
							HttpSession session){

		if (userRepository.findById(String.valueOf(session.getAttribute("userEmail"))).isPresent()){

			User user = userRepository.findById(String.valueOf(session.getAttribute("userEmail"))).get();

			if (productRepository.findById(productCode).isPresent()){

				Product product = productRepository.findById(productCode).get();

				//The state and city stores are not compatible with the state and city of the user
				if (!product.getShop().getState().equals(user.getState()) && !product.getShop().getCity().equals(user.getCity())){
					return "redirect:/goToShop?addToCart=notCompatible&shopEmail=" + shopEmail;
				}

				List<Cart> carts = cartRepository.findCartsByUserEmail(user.getEmail());

				// cart is vacant
				if (!carts.isEmpty()){
					//get The first product of the cart
					Product firstProductInCart = productRepository.findById(carts.get(0).getProduct().getCode()).get();

					//check duplicate shop
					if (!product.getShop().getEmail().equals(firstProductInCart.getShop().getEmail())){
						return "redirect:/goToShop?addToCart=fail_duplicateShop&shopEmail=" + shopEmail;
					}

					//check duplicate product
					for (Cart cart : carts){
						if (cart.getProduct().getCode().equals(product.getCode())){
							return "redirect:/goToShop?addToCart=fail_duplicateProduct&shopEmail=" + shopEmail;
						}
					}
				}
				Cart cart = new Cart();
				cart.setNumber(Byte.parseByte(number));
				product.addCart(cart);
				user.addCart(cart);
				cartRepository.save(cart);
				return "redirect:/goToShop?addToCart=success&shopEmail=" + shopEmail;
			}
		}
		return "redirect:/goToShop?addToCart=fail&shopEmail=" + shopEmail;
	}
}
