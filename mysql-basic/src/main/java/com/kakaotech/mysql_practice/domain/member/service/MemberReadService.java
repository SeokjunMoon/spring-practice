package com.kakaotech.mysql_practice.domain.member.service;

import com.kakaotech.mysql_practice.domain.member.dto.MemberDto;
import com.kakaotech.mysql_practice.domain.member.dto.MemberNicknameHistoryDto;
import com.kakaotech.mysql_practice.domain.member.entity.Member;
import com.kakaotech.mysql_practice.domain.member.entity.MemberNicknameHistory;
import com.kakaotech.mysql_practice.domain.member.repository.MemberNicknameHistoryRepository;
import com.kakaotech.mysql_practice.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberReadService {
    final private MemberRepository memberRepository;

    final private MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    public MemberDto getMember(Long id) {
        return toDto(memberRepository.findById(id).orElseThrow());
    }

    public List<MemberDto> getMembers(List<Long> ids) {
        var members = memberRepository.findAllByIdIn(ids);
        return members.stream().map(member -> toDto(member)).toList();
    }

    public List<MemberNicknameHistoryDto> getNicknameHistories(Long memberId) {
        return memberNicknameHistoryRepository
                .findAllByMemberId(memberId)
                .stream()
                .map(history -> toDto(history))
                .toList();
    }

    public List<MemberNicknameHistory> getNicknameHistory(Long memberId) {
        return memberNicknameHistoryRepository.findAllByMemberId(memberId);
    }

    public MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getEmail(), member.getNickname(), member.getBirthday());
    }

    private MemberNicknameHistoryDto toDto(MemberNicknameHistory history) {
        return new MemberNicknameHistoryDto(history.getId(), history.getMemberId(), history.getNickname(), history.getCreatedAt());
    }
}
