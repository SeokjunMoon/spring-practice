package com.example.springapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

@Controller
public class getDay {
    @RequestMapping("/getDay")
    public String main(int year, int month, int date, Model model) {
        if (!isValid(year, month, date))
            return "dayError";

        char day = calcDay(year, month, date);

        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("date", date);
        model.addAttribute("day", day);

        return "getDay";
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
}
