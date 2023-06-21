package com.kakaotech.mysql_practice.application.controller;

import com.kakaotech.mysql_practice.domain.member.dto.MemberDto;
import com.kakaotech.mysql_practice.domain.member.dto.MemberNicknameHistoryDto;
import com.kakaotech.mysql_practice.domain.member.dto.RegisterMemberCommand;
import com.kakaotech.mysql_practice.domain.member.entity.Member;
import com.kakaotech.mysql_practice.domain.member.service.MemberReadService;
import com.kakaotech.mysql_practice.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {
    final private MemberWriteService memberWriteService;
    final private MemberReadService memberReadService;

    @PostMapping
    public MemberDto register(@RequestBody RegisterMemberCommand command) {
        Member member = memberWriteService.register(command);
        return memberReadService.toDto(member);
    }

    /*
        컨트롤러에서 domain의 가장 깊숙한 곳에 있는 Member를 사용하면 불필요한 정보들도 다 전달되고
        컨트롤러를 통해 presentation layer에 까지 나가버리면 해당 요구사항에 따라 엔티티 정보가
        바뀔 수 있는 등 매우 강한 결합이 이루어짐

        -> dto에 도입하자
     */
    @GetMapping("/{id}")
    public MemberDto getMember(@PathVariable Long id) {
        return memberReadService.getMember(id);
    }

    @PostMapping("/{id}/name")
    public MemberDto changeNickname(@PathVariable Long id, @RequestBody String nickname) {
        memberWriteService.changeNickname(id, nickname);
        return memberReadService.getMember(id);
    }

    @GetMapping("/{memberId}/nickname-histories")
    public List<MemberNicknameHistoryDto> getNicknameHistories(@PathVariable Long memberId) {
        return memberReadService.getNicknameHistories(memberId);
    }

    @PostMapping("/{id}/password")
    public MemberDto changeUserPassword(@PathVariable Long id, @RequestBody String password) {
        memberWriteService.changeUserPassword(id, password);
        return memberReadService.getMember(id);
    }
}
