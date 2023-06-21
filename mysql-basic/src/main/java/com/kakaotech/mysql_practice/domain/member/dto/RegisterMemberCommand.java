package com.kakaotech.mysql_practice.domain.member.dto;

import java.time.LocalDate;

public record RegisterMemberCommand(
        String email,
        String nickname,
        LocalDate birthday,
        String userId,
        String userPassword
) {
}
