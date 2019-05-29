package com.example.confectionery.controller;

import com.example.confectionery.domain.Shop;
import com.example.confectionery.repository.ShopRepository;
import com.example.confectionery.service.ServiceDuplicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class Home {

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

    @GetMapping("/")
    public String displayHome(Model model) {

        List<Shop> shops = shopRepository.shopsValidate(true);
        model.addAttribute("shops",shops);

        return "index";
    }


    @GetMapping("/goToShop")
    public String goToShop(@RequestParam String shopEmail,
                                 Model model,
                           @RequestParam @Nullable String addToCart){

        if (shopRepository.findById(shopEmail).isPresent()) {
            serviceDuplicate.getProductByShopEmail(shopEmail,model);

			//Messages that are displayed in the cart when adding the product
            if (addToCart != null){
                model.addAttribute("addToCart",addToCart);
            }

            return "goToShop";
        }
        return "redirect:/";
    }

    @PostMapping("/destroy")
    public String logOut(HttpServletRequest request){
        //invalidate session
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/filter")
    public String filter(@RequestParam @Nullable String state,
                         @RequestParam @Nullable String city,
                         @RequestParam @Nullable String sort,
                         Model model){

        List<Shop> shops = shopRepository.shopsValidate(true);

        if (state != null && !state.isEmpty() && (city == null || city.isEmpty())){ // state
            shops = shopRepository.shopOrderByState(state);

        }else if (state != null && !state.isEmpty()){ // state and city
            shops = shopRepository.shopOrderByStateAndCity(state,city);

        }else if (city != null && !city.isEmpty()){ // city
            shops = shopRepository.shopOrderByCity(city);
        }

        if (sort != null && !sort.isEmpty()){ // sort

            if (sort.equals("پربازدیدترین")){
                Comparator<Shop> shopOrderByNumberOfVisit = Comparator.comparing(Shop::getNumberOfVisits);
                shops.sort(shopOrderByNumberOfVisit);
            }
            else {
                Comparator<Shop> shopOrderByNewRegistration = Comparator.comparing(Shop::getDateOfRegistration);
                shops.sort(shopOrderByNewRegistration);
            }
            Collections.reverse(shops);
        }

        model.addAttribute("shops",shops);
        return "index";
    }
}
