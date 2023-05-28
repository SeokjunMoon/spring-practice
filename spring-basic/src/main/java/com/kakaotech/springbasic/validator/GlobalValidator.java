package com.kakaotech.springbasic.validator;

import com.kakaotech.springbasic.classes.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class GlobalValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("GlobalValidator.validate() is called. (UserValidator)");

        User user = (User) target;
        String id = user.getId();

//        문자열 검사
//        if (id == null || id.trim().equals("")) {
//            errors.rejectValue("id", "required");
//        }

//        간단히 한 문장으로 압축
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");

        if (id == null || id.length() < 5 || id.length() > 12) {
            errors.rejectValue("id","invalidLength", new String[] {"5", "12"}, null);
        }
    }
}
