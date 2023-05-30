package com.kakaotech.mysql_practice.domain.member.service;

import com.kakaotech.mysql_practice.domain.member.dto.MemberDto;
import com.kakaotech.mysql_practice.domain.member.entity.Member;
import com.kakaotech.mysql_practice.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberReadService {
    final private MemberRepository memberRepository;

    public Member getMember(Long id) {
        return memberRepository.findById(id).orElseThrow();
    }

    public MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getEmail(), member.getNickname(), member.getBirthday());
    }
}
