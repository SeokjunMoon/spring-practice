package com.kakaotech.mysql_practice.domain.post.dto;

public record PostCommand(
        Long memberId,
        String contents
) {
}
