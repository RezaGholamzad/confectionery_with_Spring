package com.example.confectionery.controller;

import com.example.confectionery.domain.User;
import com.example.confectionery.repository.UserRepository;
import com.example.confectionery.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserRegister {

	private UserRepository userRepository;

	@Autowired
	public UserRegister(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/userRegister")
    public String userRegisterForm(Model model){
        model.addAttribute("user",new User());
        return "userRegister";
    }

    @PostMapping("/userRegisterSubmit")
    public ModelAndView userRegisterSubmit(@ModelAttribute @Valid  User user,
										   BindingResult bindingResult, ModelAndView modelAndView,
										   HttpServletRequest request){

		//validate the information entered
		UserValidator userValidator = new UserValidator();
		userValidator.validate(user,bindingResult);
		if (bindingResult.hasErrors()){
			modelAndView.setViewName("userRegister");
			return modelAndView;
		}

		//check duplicate email
		if(userRepository.findById(user.getEmail()).isPresent()){
			modelAndView.addObject("emailStatus","duplicate");
			modelAndView.setViewName("userRegister");
			return modelAndView;
		}

		//save user
		userRepository.save(user);

		//set session attribute
		request.getSession().setAttribute("userEmail", user.getEmail());
		request.getSession().setMaxInactiveInterval(24 * 60 * 60);// one day

		modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}
