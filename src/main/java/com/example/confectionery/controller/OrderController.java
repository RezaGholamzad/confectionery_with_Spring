package com.example.confectionery.controller;

import com.example.confectionery.domain.Cart;
import com.example.confectionery.domain.OrderRegistration;
import com.example.confectionery.domain.Shop;
import com.example.confectionery.repository.CartRepository;
import com.example.confectionery.repository.OrderRepository;
import com.example.confectionery.service.ServiceDuplicate;
import net.time4j.PlainDate;
import net.time4j.SystemClock;
import net.time4j.calendar.PersianCalendar;
import net.time4j.engine.CalendarDays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


@Controller
public class OrderController {

	private CartRepository cartRepository;
	private OrderRepository orderRepository;
	private ServiceDuplicate serviceDuplicate;

	@Autowired
	public void setServiceDuplicate(ServiceDuplicate serviceDuplicate) {
		this.serviceDuplicate = serviceDuplicate;
	}
	@Autowired
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Autowired
	public void setCartRepository(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@PostMapping("/setOrder")
	public String setOrder(HttpSession session,
						   @RequestParam String day,
						   @RequestParam String hour,
						   @RequestParam @Nullable String deliveryMan){

		if (deliveryMan == null){
			if ( day.isEmpty() || hour.isEmpty()){
				return "redirect:/cart?dayOrHourIsEmpty=true";
			}
		}
		else {
			if ( day.isEmpty() || hour.isEmpty() || deliveryMan.isEmpty()){
				return "redirect:/cart?dayOrHourIsEmpty=true";
			}
		}


		List<Cart> carts = cartRepository.findCartsByUserEmail(String.valueOf(session.getAttribute("userEmail")));
		OrderRegistration orderRegistration = new OrderRegistration();

		for (Cart cart : carts){
			orderRegistration.addCart(cart);
		}

//		ChronoFormatter<PersianCalendar> formatter =
//				ChronoFormatter.setUp(PersianCalendar.axis(), new Locale("fa"))
//						.addPattern("EEE, d. MMMM yyyy", PatternType.CLDR_DATE).build();
		PlainDate today = SystemClock.inLocalView().today();

		//delivery date
		PersianCalendar persianCalendar = today.transform(PersianCalendar.class);

		//purchase date
		PersianCalendar persianCalendar1 = persianCalendar.plus(CalendarDays.of(Long.parseLong(day)));
		//System.out.println(formatter.format(persianCalendar));

		byte monthDeliveryDate = serviceDuplicate.getnumberMonthByNameMonth(persianCalendar.getMonth().toString());
		byte monthPurchaseDate = serviceDuplicate.getnumberMonthByNameMonth(persianCalendar1.getMonth().toString());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance(); // creates a new calendar instance
		calendar.setTime(date);   // assigns calendar to given date

		try {
			date = sdf.parse((persianCalendar.getYear() +"-"+ monthDeliveryDate +"-"+ persianCalendar.getDayOfMonth()+
					" "+ calendar.get(Calendar.HOUR_OF_DAY) +":"+ calendar.get(Calendar.MINUTE) +":"+ calendar.get(Calendar.SECOND)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		orderRegistration.setPurchaseDate(date);

		int partOfDate = 0;
		switch (hour){
			case "9 تا 12":
				partOfDate  = 9;
				break;
			case "12 تا 15":
				partOfDate = 12;
				break;
			case "15 تا 18":
				partOfDate = 15;
				break;
			case "18 تا 21":
				partOfDate = 18;
				break;
		}
		try {
			date = sdf.parse((persianCalendar1.getYear() +"-"+ monthPurchaseDate +"-"+ persianCalendar1.getDayOfMonth()+
					" "+ partOfDate +":"+ partOfDate +":"+ partOfDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		orderRegistration.setDeliveryDate(date);

		orderRegistration.setPrice(serviceDuplicate.calculateTotalOrderAmount(carts));

		if (deliveryMan != null){
			if (deliveryMan.equals("بله")){
				orderRegistration.setDeliveryMan(true);
			}
		}

		orderRepository.save(orderRegistration);
		return "redirect:/cart";
	}

	@PostMapping("/orderDelete")
	public String orderDelete(@RequestParam String orderCode){

		if (orderRepository.findById(orderCode).isPresent()){
			orderRepository.deleteById(orderCode);
		}
		return "redirect:/manageOrders";
	}

	@GetMapping("/manageOrders")
	public String manageOrders(HttpSession session,
							   Model model){

		List<OrderRegistration> orderRegistrations = orderRepository.getOrderByShopEmail(String.valueOf(session.getAttribute("shopEmail")));

		//sort order
		Comparator<OrderRegistration> orderRegistrationComparator = Comparator.comparing(OrderRegistration::getDeliveryDate);
		orderRegistrations.sort(orderRegistrationComparator);

		model.addAttribute("orders", orderRegistrations);
		return "manageOrders";
	}

	@PostMapping("/sendOrder")
	public String sendOrder(@RequestParam String orderCode){

		if (orderRepository.findById(orderCode).isPresent()){
			OrderRegistration orderRegistration = orderRepository.findById(orderCode).get();
			orderRegistration.setDeliver(true);
			orderRepository.save(orderRegistration);
		}
		return "redirect:/manageOrders";
	}
}
