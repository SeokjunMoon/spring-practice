package com.kakaotech.mysql_practice.domain.member.service;

import com.kakaotech.mysql_practice.domain.member.dto.RegisterMemberCommand;
import com.kakaotech.mysql_practice.domain.member.entity.Member;
import com.kakaotech.mysql_practice.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberWriteService {
    final private MemberRepository memberRepository;

    public void create(RegisterMemberCommand command) {
        Member member = Member.builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthday(command.birthday())
                .build();
        memberRepository.save(member);
    }
}
