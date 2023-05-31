package com.kakaotech.mysql_practice.domain.follow.dto;

import java.time.LocalDateTime;

public record FollowDto(
        Long id,
        Long fromMemberId,
        Long toMemberId,
        LocalDateTime createdAt
) {
    public Long getToMemberId() {
        return toMemberId;
    }
}
