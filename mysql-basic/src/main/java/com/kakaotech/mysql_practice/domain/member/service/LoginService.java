package com.kakaotech.mysql_practice.domain.member.service;

import com.kakaotech.mysql_practice.domain.member.dto.LoginCommand;
import com.kakaotech.mysql_practice.domain.member.dto.MemberDto;
import com.kakaotech.mysql_practice.domain.member.entity.Member;
import com.kakaotech.mysql_practice.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.openmbean.InvalidKeyException;

@RequiredArgsConstructor
@Service
public class LoginService {
    final private MemberRepository memberRepository;

    public MemberDto login(LoginCommand loginCommand) {
        var member = memberRepository.findByUserId(loginCommand.id()).orElseThrow(() -> new InvalidKeyException("회원가입 이력이 없습니다."));
        if (member.getUserPassword().equals(loginCommand.password())) {
            return toDto(member);
        }
        else throw new InvalidKeyException("비밀번호가 일치하지 않습니다.");
    }

    private MemberDto toDto(Member member) {
        return new MemberDto(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getBirthday(),
                member.getUserId()
        );
    }
}
