package com.kakaotech.mysql_practice.application.usecase;

import com.kakaotech.mysql_practice.domain.follow.service.FollowReadService;
import com.kakaotech.mysql_practice.domain.member.dto.MemberDto;
import com.kakaotech.mysql_practice.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetFollowingMembersUsecase {
    final private MemberReadService memberReadService;
    final private FollowReadService followReadService;

    public List<MemberDto> execute(Long memberId) {
        var followings = followReadService.getFollowings(memberId);
        var followingMemberIds = followings.stream().map(followDto -> followDto.getToMemberId()).toList();
        return memberReadService.getMembers(followingMemberIds);
    }
}
