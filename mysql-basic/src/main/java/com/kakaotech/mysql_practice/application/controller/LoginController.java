package com.kakaotech.mysql_practice.application.controller;

import com.kakaotech.mysql_practice.domain.member.dto.LoginCommand;
import com.kakaotech.mysql_practice.domain.member.dto.MemberDto;
import com.kakaotech.mysql_practice.domain.member.service.LoginService;
import com.kakaotech.mysql_practice.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LoginController {
    final private LoginService loginService;

    @PostMapping("/login")
    public MemberDto login(@RequestBody LoginCommand loginCommand) {
        return loginService.login(loginCommand);
    }
}
