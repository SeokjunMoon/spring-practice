package com.example.springapp.controller;


import com.example.springapp.classes.MyDate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

@Controller
public class getDayMVC2 {

    @ExceptionHandler(Exception.class)
    public String catcher(Exception err, BindingResult result) {
        System.out.println(result);
        FieldError error = result.getFieldError();

        System.out.println("code = " + error.getCode());
        System.out.println("filed = " + error.getField());
        System.out.println("msg = " + error.getDefaultMessage());
        err.printStackTrace();

        return "dayError";
    }

    @RequestMapping("/getDayMVC2")
    public String main(MyDate myDate, BindingResult result) {
        if (!isValid(myDate))
            return "dayError";

        return "getDay";
    }

    private boolean isValid(MyDate myDate) {
        return isValid(myDate.getYear(), myDate.getMonth(), myDate.getDate());
    }

    private boolean isValid(int year, int month, int date) {
        if (year == -1 || month == -1 || date == -1)
            return false;
        return (1 <= month && month <= 12) && (1 <= date && date <= 31);
    }

    private char calcDay(int year, int month, int date) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, date);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return " 일월화수목금토".charAt(dayOfWeek);
    }

    private @ModelAttribute("day") char getDay(MyDate myDate) {
        return calcDay(myDate.getYear(), myDate.getMonth(), myDate.getDate());
    }
}
