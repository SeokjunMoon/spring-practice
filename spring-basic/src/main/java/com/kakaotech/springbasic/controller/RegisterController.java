package com.kakaotech.springbasic.controller;


import com.kakaotech.springbasic.classes.User;
import com.kakaotech.springbasic.validator.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @InitBinder
    public void toDate(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
        binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#"));
        binder.setValidator(new UserValidator());
    }

    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String register() {
        return "registerForm";
    }

    @PostMapping("/save")
    public String save(@Valid User user, BindingResult result, Model model) throws Exception {
        System.out.println("result = " + result);
        System.out.println("user = " + user);

        if (!isValid(user)) {
            String msg = URLEncoder.encode("id를 잘못 입력하였습니다.", "utf-8");
            model.addAttribute("msg", msg);
            return "forward:/register/add";
        }
        return "registerInfo";
    }

    private boolean isValid(User user) {
        return true;
    }
}
