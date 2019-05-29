package com.example.confectionery.controller;

import com.example.confectionery.domain.User;
import com.example.confectionery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.internet.MimeMessage;

@Controller
public class EmailController {
	private JavaMailSender javaMailSender;
	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	//Send password to email for occasions when the user has forgotten the password
	@PostMapping("/sendEmail")
	private String sendEmail(@RequestParam("email")String email , Model model) throws Exception{
		//check that the user exists
		if (userRepository.findById(email).isPresent()){

			//Get user information from repository
			User user = userRepository.findById(email).get();

			//create email
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);

			//send email to
			helper.setTo(email);
			//text email
			helper.setText(user.getPassword());
			//subject email
			helper.setSubject("Hi" + user.getFirstName());
			javaMailSender.send(message);

			//The message that the email was successful
			return "redirect:/loginPage?sendStatus=success";
		}
		//The message that the email was failed
		return "redirect:/loginPage?sendStatus=fail";
	}

}
