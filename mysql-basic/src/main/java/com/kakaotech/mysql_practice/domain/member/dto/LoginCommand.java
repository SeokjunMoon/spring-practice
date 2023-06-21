package com.kakaotech.mysql_practice.domain.member.dto;

public record LoginCommand(
        String id,
        String password
) {
}
