package com.kakaotech.springbasic.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {
    @GetMapping("/list")
    public String list(HttpServletRequest request) {
        if (!loginCheck(request)) {
            return "redirect:/login/login?toURL=" + request.getRequestURL();
        }
        return "boardList";
    }

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("id") != null;
    }
}
