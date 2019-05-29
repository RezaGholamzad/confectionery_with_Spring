package com.example.confectionery.controller;

import com.example.confectionery.domain.User;
import com.example.confectionery.repository.UserRepository;
import com.example.confectionery.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserProfile {
	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/userProfile")
	public String displayProfile(HttpSession session, Model model){
		if (userRepository.findById(String.valueOf(session.getAttribute("userEmail"))).isPresent()) {
			User user = userRepository.findById(String.valueOf(session.getAttribute("userEmail"))).get();
			//send user model to userProfile.vue
			model.addAttribute("user", user);
			return "/userProfile";
		}
		else {
			return "redirect:/";
		}

	}

	@PostMapping("/changeProfile")
	public String userRegisterSubmit(@ModelAttribute @Valid User user,
										   BindingResult bindingResult,Model model,
										   HttpServletRequest request,HttpSession session){

		//validate the information entered
		UserValidator userValidator = new UserValidator();
		userValidator.validate(user,bindingResult);
		if (bindingResult.hasErrors()){
			return "userProfile";
		}

		if (userRepository.findById(String.valueOf(session.getAttribute("userEmail"))).isPresent()) {

			//Get user information from repository through the session
			User currentUser = userRepository.findById(String.valueOf(session.getAttribute("userEmail"))).get();

			//If the email has not changed
			if (user.getEmail().equals(currentUser.getEmail())){
				//update repository
				userRepository.save(user);
			}
			//If the email has changed
			else {

				//check duplicate email
				if(userRepository.findById(user.getEmail()).isPresent()){
					model.addAttribute("emailStatus","duplicate");
					return "userProfile";
				}

				//delete current user
				userRepository.delete(currentUser);

				//The new information is registered as a new user
				userRepository.save(user);

				//change session attribute
				request.getSession().setAttribute("userEmail", user.getEmail());
				request.getSession().setMaxInactiveInterval(24 * 60 * 60);// one day
			}

		}
		return "redirect:/userProfile";

	}

	@PostMapping("/changePassword")
	public String changePassword(@RequestParam("currentPassword") String currentPassword,
								 @RequestParam("newPassword") String newPassword,
								 @RequestParam("confirmNewPassword") String confirmNewPassword,
								 HttpSession session){

		if (userRepository.findById(String.valueOf(session.getAttribute("userEmail"))).isPresent()){

			//Get user information from repository through the session
			User user = userRepository.findById(String.valueOf(session.getAttribute("userEmail"))).get();

			//check whether that the password entered for the correct email is valid
			if (user.getPassword().equals(currentPassword)){

				//check newPassword equal with confirmNewPassword
				if (newPassword.equals(confirmNewPassword)){

					user.setPassword(newPassword);

					//update repository
					userRepository.save(user);

					return "redirect:/userProfile?changeStatus=success";
				}
				else {
					return "redirect:/userProfile?changeStatus=notValidConfirmPassword";
				}
			}
			else {
				return "redirect:/userProfile?changeStatus=wrongPassword";
			}
		}
		return "redirect:/";
	}
}
