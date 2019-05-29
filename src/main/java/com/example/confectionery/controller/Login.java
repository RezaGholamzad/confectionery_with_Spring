package com.example.confectionery.controller;

import com.example.confectionery.domain.Shop;
import com.example.confectionery.domain.User;
import com.example.confectionery.repository.ShopRepository;
import com.example.confectionery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Login {
	private UserRepository userRepository;
	private ShopRepository shopRepository;

	@Autowired
	public void setShopRepository(ShopRepository shopRepository) {
		this.shopRepository = shopRepository;
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	//display login page
	@GetMapping("/loginPage")
	public String loginPage() {
		return "loginPage";
	}

	//submit login page
	@PostMapping("/loginPage")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
						@RequestParam("rememberMe") @Nullable String rememberMe // @Nullable To say that it can be null
			, HttpServletRequest request) {
		// check for admin
		if (email.equals("admin@gmail.com") && password.equals("123")) {//set attribute email for session

			request.getSession().setAttribute("adminEmail", email);

			//Checking the user should be remembered for a long time
			if (rememberMe != null) {
				request.getSession().setMaxInactiveInterval(7 * 24 * 60 * 60);// one week
			} else {
				request.getSession().setMaxInactiveInterval(60 * 60);// one hour
			}

			return "redirect:/shopVerification";
		}

		//check that the user exists
		if (userRepository.findById(email).isPresent()) {

			//Get user information from repository
			User user = userRepository.findById(email).get();

			//check whether that the password entered for the correct email is valid
			if (user.getPassword().equals(password)) {

				//set attribute email for session
				request.getSession().setAttribute("userEmail", email);

				//Checking the user should be remembered for a long time
				if (rememberMe != null) {
					request.getSession().setMaxInactiveInterval(7 * 24 * 60 * 60);// one week
				} else {
					request.getSession().setMaxInactiveInterval(60 * 60);// one hour
				}

				return "redirect:/";
			}
		}
		//check that the shop exists
		if (shopRepository.findById(email).isPresent()) {

			//Get shop information from repository
			Shop shop = shopRepository.findById(email).get();

			if (shop.getPassword().equals(password)) {

				//set attribute email for session
				request.getSession().setAttribute("shopEmail", email);

				//Checking the user should be remembered for a long time
				if (rememberMe != null) {
					request.getSession().setMaxInactiveInterval(7 * 24 * 60 * 60);// one week
				} else {
					request.getSession().setMaxInactiveInterval(60 * 60);// one hour
				}

				return "redirect:/shopProfile";
			}
		}
		//Send failed message in the page loginPage.vue
		return "redirect:/loginPage?loginStatus=failed";
	}
}
