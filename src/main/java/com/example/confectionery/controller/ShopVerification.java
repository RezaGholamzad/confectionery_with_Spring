package com.example.confectionery.controller;

import com.example.confectionery.domain.Shop;
import com.example.confectionery.repository.ShopRepository;
import com.example.confectionery.service.ServiceDuplicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Controller
public class ShopVerification {
	private ShopRepository shopRepository;
	private ServiceDuplicate serviceDuplicate;

	@Autowired
	public void setServiceDuplicate(ServiceDuplicate serviceDuplicate) {
		this.serviceDuplicate = serviceDuplicate;
	}

	@Autowired
	public void setShopRepository(ShopRepository shopRepository) {
		this.shopRepository = shopRepository;
	}

	@GetMapping("/shopCheck")
	public String shopCheck(Model model,
							@RequestParam String shopEmail,
							HttpSession session){

		// Check the correctness admin
		if (session.getAttribute("adminEmail").equals("admin@gmail.com")){

			//check shop exist
			if (shopRepository.findById(shopEmail).isPresent()) {

				//get shop products
				serviceDuplicate.getProductByShopEmail(shopEmail,model);
				return "shopCheck";
			}

			return "shopVerification";
		}
		return "redirect:/";
	}


	@GetMapping("/shopVerification")
	public String shopVerification(HttpSession session, Model model){

		if (session.getAttribute("adminEmail").equals("admin@gmail.com")){

			List<Shop> shopsValidate = shopRepository.shopsValidate(true);
			List<Shop> shopsNotValidate = shopRepository.shopsValidate(false);

			model.addAttribute("shopsValidate",shopsValidate);
			model.addAttribute("shopsNotValidate",shopsNotValidate);

			return "shopVerification";
		}

		return "redirect:/loginPage";
	}

	@GetMapping("/shopImageDownload")
	@ResponseBody
	public InputStreamResource shopImageDownload (HttpServletResponse response,
												  @RequestParam String shopEmail,
												  HttpSession session) throws IOException {

		if (session.getAttribute("adminEmail").equals("admin@gmail.com")){

			if (shopRepository.findById(shopEmail).isPresent()){

				Shop shop = shopRepository.findById(shopEmail).get();

				response.setContentType("image/png");
				response.setHeader("Content-Disposition", "attachment; filename=\""+ shop.getEmail() +".png\"");

				return new InputStreamResource(new FileInputStream("C:\\Users\\Reza\\IdeaProjects" + shop.getShopImageAddress()));
			}
		}
		return null;
	}

	@GetMapping("/businessLicenseImageDownload")
	@ResponseBody
	public InputStreamResource businessLicenseImageDownload(HttpServletResponse response,
															@RequestParam String shopEmail,
															HttpSession session) throws FileNotFoundException {

		if (session.getAttribute("adminEmail").equals("admin@gmail.com")) {

			if (shopRepository.findById(shopEmail).isPresent()){

				Shop shop = shopRepository.findById(shopEmail).get();

				response.setContentType("image/png");
				response.setHeader("Content-Disposition", "attachment; filename=\""+ shop.getBusinessLicense() +".png\"");

				return new InputStreamResource(new FileInputStream("C:\\Users\\Reza\\IdeaProjects" + shop.getBusinessLicenseImageAddress()));

			}
		}
		return null;
	}

	@PostMapping("/shopVerification")
	public String shopVerification(@RequestParam String shopEmail){

		if (shopRepository.findById(shopEmail).isPresent()){

			Shop shop = shopRepository.findById(shopEmail).get();
			shop.setValidate(true);
			shopRepository.save(shop);
		}

		return "redirect:/shopVerification";
	}

	@PostMapping("/shopDelete")
	public String shopDelete(@RequestParam String shopEmail){

		if (shopRepository.findById(shopEmail).isPresent()){

			shopRepository.deleteById(shopEmail);
		}

		return "redirect:/shopVerification";
	}

	@PostMapping("/shopSuspension")
	public String shopSuspension(@RequestParam String shopEmail){

		if (shopRepository.findById(shopEmail).isPresent()){

			Shop shop = shopRepository.findById(shopEmail).get();
			shop.setValidate(false);
			shopRepository.save(shop);
		}

		return "redirect:/shopVerification";
	}


}
