package com.example.confectionery.validator;

import com.example.confectionery.domain.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;

public class UserValidator implements Validator {


	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;

		checkNationalCode(user.getNationalCode(), errors);

		// check confirm password equal with password
		if (!user.getPassword().equals(user.getConfirmPassword()) && user.getConfirmPassword() != null) {
			errors.rejectValue("password", "validation.password.notValid", "تکرار پسورد درست نمیباشد");
		}
	}

	static boolean checkNationalCode(String code, Errors errors) {

		// check national code valid
		if (!code.equals("")) {
			//check length
			if (code.length() != 10) {
				errors.rejectValue("nationalCode", "validation.nationalCode.notValid", "کد ملی وارد شده صحیح نیست");
				return false;
			}
			long nationalCode = Long.parseLong(code);
			byte[] arrayNationalCode = new byte[10];

			//extract digits from number
			for (int i = 0; i < 10; i++) {
				arrayNationalCode[i] = (byte) (nationalCode % 10);
				nationalCode = nationalCode / 10;
			}

			//Checking the control digit
			int sum = 0;
			for (int i = 9; i > 0; i--) {
				sum += arrayNationalCode[i] * (i + 1);
			}
			int temp = sum % 11;
			if (temp < 2) {
				if (arrayNationalCode[0] != temp) {
					errors.rejectValue("nationalCode", "validation.nationalCode.notValid", "کد ملی وارد شده صحیح نیست");
					return false;
				}
			} else {
				if (arrayNationalCode[0] != (11 - temp)) {
					errors.rejectValue("nationalCode", "validation.nationalCode.notValid", "کد ملی وارد شده صحیح نیست");
					return false;
				}
			}
		}
		return true;
	}
}
