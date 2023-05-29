package com.kakaotech.mysql_practice.controller;

import com.kakaotech.mysql_practice.domain.member.dto.RegisterMemberCommand;
import com.kakaotech.mysql_practice.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {
    final private MemberWriteService memberWriteService;

    @PostMapping("/members")
    public void register(@RequestBody RegisterMemberCommand command) {
        memberWriteService.create(command);
    }
}
