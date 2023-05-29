package com.kakaotech.mysql_practice.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Objects;

@Getter
public class Member {
    final private Long id;
    private String nickname;
    final private String email;
    final private LocalDate birthday;
    final private LocalDate createdAt;

    final private static Long NAME_MAX_LENGTH = 10L;
    @Builder
    public Member(Long id, String nickname, String email, LocalDate birthday, LocalDate createdAt) {
        this.id = id;
        this.email = Objects.requireNonNull(email);
        this.birthday = Objects.requireNonNull(birthday);

        validNickname(nickname);
        this.nickname = Objects.requireNonNull(nickname);

        this.createdAt = createdAt == null ? LocalDate.now() : createdAt;
    }

    void validNickname(String nickname) {
        Assert.isTrue(nickname.length() <= NAME_MAX_LENGTH, "최대 길이를 초과하였습니다.");
    }
}
