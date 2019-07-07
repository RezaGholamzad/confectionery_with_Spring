package com.example.confectionery.controller;

import com.example.confectionery.domain.DiscountCode;
import com.example.confectionery.domain.Product;
import com.example.confectionery.domain.Shop;
import com.example.confectionery.repository.OrderRepository;
import com.example.confectionery.repository.ProductRepository;
import com.example.confectionery.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 *2, // 2mg
		maxFileSize = 1024 * 1024 *10, //10mg
		maxRequestSize = 1024 * 1024 *50)
@Controller
public class ShopProfile {

	private ShopRepository shopRepository;
	private ProductRepository productRepository;
	private OrderRepository orderRepository;

	@Autowired
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Autowired
	public void setShopRepository(ShopRepository shopRepository) {
		this.shopRepository = shopRepository;
	}

	@GetMapping("/shopProfile")
	public String shopProfile(HttpSession session, Model model){
		if (shopRepository.findById(String.valueOf(session.getAttribute("shopEmail"))).isPresent()) {
			Shop shop = shopRepository.findById(String.valueOf(session.getAttribute("shopEmail"))).get();

			//send user model to userProfile.vue
			model.addAttribute("shop", shop);

			List<Product> products = productRepository.findByShopEmail(shop.getEmail());
			model.addAttribute("products",products);

			return "/shopProfile";
		}

		return "redirect:/";
	}

	@PostMapping("/cakeRegistration")
	public String cakeRegistration(@RequestParam String name,
								   @RequestParam Part productImage,
								   @RequestParam String price,
								   @RequestParam String explanation,
								   HttpSession session){

		if (productImage.getSize() == 0 || name.equals("") || price.equals("")){

			return "redirect:/shopProfile?addProduct=fail";
		}
		if (shopRepository.findById(String.valueOf(session.getAttribute("shopEmail"))).isPresent()){

			//get current shop
			Shop shop = shopRepository.findById(String.valueOf(session.getAttribute("shopEmail"))).get();

			//create new product for current shop
			Product product = new Product(shop);

			//Save the product object in the database to generate a record for which the key is generated
			// to add the rest of the information later.
			productRepository.save(product);

			try {
				//set productImageAddress
				String fileName = product.getCode()+".jpg";
				String savePath = "C:/Users/Reza/IdeaProjects/image/cake/" + fileName;
				productImage.write(savePath + File.separator);
				product.setProductImageAddress(savePath.substring(savePath.indexOf("/image")));
			} catch (IOException e) {
				e.printStackTrace();
			}

			product.setName(name);
			product.setPrice(Integer.parseInt(price));
			product.setCake(true);
			if (!explanation.isEmpty()){
				product.setExplanation(explanation);
			}

			//update product
			productRepository.save(product);
		}

		return "redirect:/shopProfile?addProduct=success";
	}

	@PostMapping("/cakeEditRegistration")
	public String cakeEditRegistration(@RequestParam @Nullable String name,
									   @RequestParam @Nullable Part productImage,
									   @RequestParam @Nullable String price,
									   @RequestParam @Nullable String explanation,
									   @RequestParam String productCode){


		if (productRepository.findById(productCode).isPresent()){
			Product product = productRepository.findById(productCode).get();

			if (price != null && !price.equals("")){
				product.setPrice(Integer.parseInt(price));
			}

			if (name != null && !name.equals("")){
				product.setName(name);
			}

			if (productImage != null && productImage.getSize() != 0){
				try {
					//set productImageAddress
					String fileName = productCode+".jpg";
					String savePath = "C:/Users/Reza/IdeaProjects/image/cake/" + fileName;
					productImage.write(savePath + File.separator);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (explanation != null && !explanation.equals("")){
				product.setExplanation(explanation);
			}
			productRepository.save(product);
		}

		return "redirect:/shopProfile?editProduct=success";
	}
	@PostMapping("sweetEditRegistration")
	public String sweetEditRegistration(@RequestParam String name,
										@RequestParam Part productImage,
										@RequestParam String price,
										@RequestParam String numberInKilo,
										@RequestParam String explanation,
										@RequestParam String productCode){

		if (productRepository.findById(productCode).isPresent()){
			Product product = productRepository.findById(productCode).get();

			if (name != null && !name.equals("")){
				product.setName(name);
			}
			if (productImage != null && productImage.getSize() != 0){
				try {
					//set productImageAddress
					String fileName = product.getCode()+".jpg";
					String savePath = "C:/Users/Reza/IdeaProjects/image/sweet/" + fileName;
					productImage.write(savePath + File.separator);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (numberInKilo != null && !numberInKilo.equals("")){
				product.setNumberInKil(Byte.parseByte(numberInKilo));
			}
			if (price != null && !price.equals("")){
				product.setPrice(Integer.parseInt(price));
			}
			if (explanation != null && !explanation.equals("")){
				product.setExplanation(explanation);
			}
			productRepository.save(product);
		}


		return "redirect:/shopProfile?editProduct=success";
	}
	@PostMapping("/sweetRegistration")
	public String sweetRegistration(@RequestParam String name,
									@RequestParam Part productImage,
									@RequestParam String price,
									@RequestParam String numberInKilo,
									@RequestParam String explanation,
									HttpSession session){

		if (productImage.getSize() == 0 || name.equals("") || price.equals("") || numberInKilo.equals("")){

			return "redirect:/shopProfile?addProduct=fail";
		}
		if (shopRepository.findById(String.valueOf(session.getAttribute("shopEmail"))).isPresent()){

			//get current shop
			Shop shop = shopRepository.findById(String.valueOf(session.getAttribute("shopEmail"))).get();

			//create new product for current shop
			Product product = new Product(shop);

			//Save the product object in the database to generate a record for which the key is generated
			// to add the rest of the information later.
			productRepository.save(product);

			try {
				//set productImageAddress
				String fileName = product.getCode()+".jpg";
				String savePath = "C:/Users/Reza/IdeaProjects/image/sweet/" + fileName;
				productImage.write(savePath + File.separator);
				product.setProductImageAddress(savePath.substring(savePath.indexOf("/image")));
			} catch (IOException e) {
				e.printStackTrace();
			}

			product.setName(name);
			product.setPrice(Integer.parseInt(price));
			product.setCake(false);
			product.setNumberInKil(Byte.parseByte(numberInKilo));
			if (!explanation.isEmpty()){
				product.setExplanation(explanation);
			}

			//update product
			productRepository.save(product);

		}
		return "redirect:/shopProfile?addProduct=success";
	}

	@PostMapping("/productDelete")
	public String deleteProduct(@RequestParam String productCode){

		productRepository.deleteById(productCode);

		return "redirect:/shopProfile?deleteProduct=success";
	}

	@PostMapping("/setDiscountCode")
	public String setDiscountCode(@RequestParam String productCode,
								  @RequestParam String percentage,
								  @RequestParam @Nullable String expirationDate,
								  @RequestParam @Nullable String numberLimit){

		if (percentage.isEmpty()){
			return "redirect:/shopProfile?setDiscount=fail";
		}
		if (productRepository.findById(productCode).isPresent()){

			//get current product
			Product product = productRepository.findById(productCode).get();

			//create new discount code for current product
			DiscountCode discountCode = new DiscountCode();
			discountCode.setPercentage(Byte.parseByte(percentage));
			discountCode.setType('A');

			//set discount code for product
			product.setDiscountCode(discountCode);

			//set expiration date
			if (expirationDate != null && !expirationDate.equals("")){
				Date date = Date.valueOf(LocalDate.now());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				try {
					calendar.setTime(sdf.parse(String.valueOf(date)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(expirationDate));
				product.getDiscountCode().setExpirationDate(calendar.getTime());
			}

			//set number limit
			if (numberLimit != null && !numberLimit.equals("")){
				product.getDiscountCode().setNumberLimit(Short.parseShort(numberLimit));
			}

			//update product
			productRepository.save(product);

		}

		return "redirect:/shopProfile?setDiscount=success";
	}

	@PostMapping("/deleteDiscountCode")
	public String deleteDiscountCode(@RequestParam String productCode){

		if (productRepository.findById(productCode).isPresent()){

			//get current product
			Product product = productRepository.findById(productCode).get();

			//delete discount code
			product.setDiscountCode(null);

			//update
			productRepository.save(product);
		}

		return "redirect:/shopProfile?deleteDiscount=success";
	}

	@PostMapping("/notAvailable")
	public String notAvailable(@RequestParam String productCode){

		if (productRepository.findById(productCode).isPresent()){

			Product product = productRepository.findById(productCode).get();
			product.setAvailable(false);
			productRepository.save(product);
		}

		return "redirect:/shopProfile";
	}

	@PostMapping("/available")
	public String available(@RequestParam String productCode){

		if (productRepository.findById(productCode).isPresent()){
			Product product = productRepository.findById(productCode).get();
			product.setAvailable(true);
			productRepository.save(product);
		}

		return "redirect:/shopProfile";
	}


}
