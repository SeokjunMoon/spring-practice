package com.kakaotech.mysql_practice.domain.member.service;

import com.kakaotech.mysql_practice.domain.member.dto.RegisterMemberCommand;
import com.kakaotech.mysql_practice.domain.member.entity.Member;
import com.kakaotech.mysql_practice.domain.member.entity.MemberNicknameHistory;
import com.kakaotech.mysql_practice.domain.member.repository.MemberNicknameHistoryRepository;
import com.kakaotech.mysql_practice.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberWriteService {
    final private MemberRepository memberRepository;

    final private MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    @Transactional
    public Member register(RegisterMemberCommand command) {
        Member member = Member.builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthday(command.birthday())
                .userId(command.userId())
                .userPassword(command.userPassword())
                .build();

        if (isValidUserId(command.userId())) {
            var savedMember = memberRepository.save(member);
            saveMemberNicknameHistory(savedMember);
            return savedMember;
        }
        else throw new DuplicateKeyException("중복된 아이디 입니다.");
    }

    @Transactional
    public void changeNickname(Long memberId, String nickname) {
        var member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);
        memberRepository.save(member);

        saveMemberNicknameHistory(member);
    }

    private void saveMemberNicknameHistory(Member member) {
        var history = MemberNicknameHistory
                .builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();

        memberNicknameHistoryRepository.save(history);
    }

    public boolean isValidUserId(String id) {
        return memberRepository.findByUserId(id).isEmpty();
    }

    @Transactional
    public void changeUserPassword(Long memberId, String password) {
        var member = memberRepository.findById(memberId).orElseThrow();
        member.changePassword(password);
        memberRepository.save(member);
    }
}
