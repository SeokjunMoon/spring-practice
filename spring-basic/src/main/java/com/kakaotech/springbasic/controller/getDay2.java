package com.kakaotech.springbasic.controller;

import com.kakaotech.springbasic.classes.MyDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;


@Controller
public class getDay2 {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception exception, BindingResult result) {
		System.out.println("result = " + result);

		FieldError fieldError = result.getFieldError();

		System.out.println("code = " + fieldError.getCode());
		System.out.println("field = " + fieldError.getField());
		System.out.println("message = " + fieldError.getDefaultMessage());
		exception.printStackTrace();

		return "dayError";
	}

	@RequestMapping("/getDay2")
	public String main(MyDate myDate, Model model) {
		if (!isValid(myDate))
			return "dayError";

		char day = calcDay(myDate);
		model.addAttribute("day", day);

		return "day";
	}

	private boolean isValid(MyDate myDate) {
		return isValid(myDate.getYear(), myDate.getMonth(), myDate.getDate());
	}

	private boolean isValid(int year, int month, int date) {
		if (year == -1 || month == -1 || date == -1)
			return false;
		return (1 <= month && month <= 12) && (1 <= date && date <= 31);
	}

	private char calcDay(MyDate myDate) {
		return calcDay(myDate.getYear(), myDate.getMonth(), myDate.getDate());
	}

	private char calcDay(int year, int month, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return "일월화수목금토".charAt(dayOfWeek - 1);
	}
}