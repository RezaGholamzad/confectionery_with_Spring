package com.example.confectionery.controller;

import com.example.confectionery.domain.Shop;
import com.example.confectionery.domain.ShopPhone;
import com.example.confectionery.repository.ShopRepository;
import com.example.confectionery.validator.ShopValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 *2, // 2mg
		maxFileSize = 1024 * 1024 *10, //10mg
		maxRequestSize = 1024 * 1024 *50)
@Controller
public class ShopRegister {
	private ShopRepository shopRepository;

	@Autowired
	public ShopRegister(ShopRepository shopRepository) {
		this.shopRepository = shopRepository;
	}

	@GetMapping("/shopRegister")
	public String shopRegister(Model model){
		model.addAttribute("shop",new Shop());
		return "shopRegister";
	}
	@PostMapping("/shopRegisterSubmit")
	public ModelAndView shopRegisterSubmit(@ModelAttribute @Valid Shop shop,
									 BindingResult bindingResult, ModelAndView modelAndView,
									 HttpServletRequest request){

		Part part = null;
		try {
			//set BusinessLicenseImageAddress
			part = request.getPart("businessLicenseImage");
			String fileName = shop.getBusinessLicense()+".jpg";
			String savePath = "C:/Users/Reza/IdeaProjects/image/businessLicenseImages/" + fileName;
			part.write(savePath + File.separator);
			shop.setBusinessLicenseImageAddress(savePath.substring(savePath.indexOf("/image")));

			//set ShopImageAddress
			part = request.getPart("shopImage");
			savePath = "C:/Users/Reza/IdeaProjects/image/shopImage/" + fileName;
			part.write(savePath + File.separator);
			shop.setShopImageAddress(savePath.substring(savePath.indexOf("/image")));

		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

		//validate the information entered
		ShopValidator shopValidator = new ShopValidator();
		shopValidator.validate(shop,bindingResult);
		if (bindingResult.hasErrors()){
			modelAndView.setViewName("shopRegister");
			return modelAndView;
		}

		//check duplicate email
		if(shopRepository.findById(shop.getEmail()).isPresent()){
			modelAndView.addObject("emailStatus","duplicate");
			modelAndView.setViewName("shopRegister");
			return modelAndView;
		}

		//set DateOfRegistration
		shop.setDateOfRegistration(Date.valueOf(LocalDate.now()));

		//set phone
		int i = 0;
		String phone = shop.getPhone();
		while (i < phone.length()){
			shop.addPhone(new ShopPhone(phone.substring(i,i + 11) ) );
			i += 12;
		}

		//save user
		shopRepository.save(shop);

		//set session attribute
		request.getSession().setAttribute("shopEmail", shop.getEmail());
		request.getSession().setMaxInactiveInterval(24 * 60 * 60);// one day

		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}

}
