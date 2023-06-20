package com.kakaotech.mysql_practice.domain.follow.service;

import com.kakaotech.mysql_practice.domain.follow.dto.FollowDto;
import com.kakaotech.mysql_practice.domain.follow.entity.Follow;
import com.kakaotech.mysql_practice.domain.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowReadService {
    final private FollowRepository followRepository;

    public List<FollowDto> getFollowings(Long memberId) {
        return followRepository
                .findAllByFromMemberId(memberId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<FollowDto> getFollowers(Long memberId) {
        return followRepository
                .findAllByToMemberId(memberId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    private FollowDto toDto(Follow follow) {
        return new FollowDto(follow.getId(), follow.getFromMemberId(), follow.getToMemberId(), follow.getCreatedAt());
    }
}
