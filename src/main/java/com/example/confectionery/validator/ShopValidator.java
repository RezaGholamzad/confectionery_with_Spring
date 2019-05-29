package com.example.confectionery.validator;

import com.example.confectionery.domain.Shop;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ShopValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Shop shop = (Shop) target;

		// check national code valid
		if (shop.getNationalCode().isEmpty()){
			errors.rejectValue("nationalCode", "validation.field.notValid","کد ملی وارد نشده است");
		}
		else {
			UserValidator.checkNationalCode(shop.getNationalCode(),errors);
		}

		// check confirm password equal with password
		if(!shop.getPassword().equals(shop.getConfirmPassword()) && shop.getConfirmPassword() != null){
			errors.rejectValue("password", "validation.password.notValid", "تکرار پسورد درست نمیباشد");
		}
	}
}
