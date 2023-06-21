package com.kakaotech.mysql_practice.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Member {
    final private Long id;
    private String nickname;
    final private String email;
    final private LocalDate birthday;
    final private LocalDateTime createdAt;
    final private String userId;
    private String userPassword;

    final private static Long NAME_MAX_LENGTH = 10L;
    @Builder
    public Member(Long id, String nickname, String email, LocalDate birthday, LocalDateTime createdAt, String userId, String userPassword) {
        this.id = id;
        this.email = Objects.requireNonNull(email);
        this.birthday = Objects.requireNonNull(birthday);

        validNickname(nickname);
        this.nickname = Objects.requireNonNull(nickname);

        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;

        this.userId = Objects.requireNonNull(userId);
        this.userPassword = Objects.requireNonNull(userPassword);
    }

    public void changeNickname(String to) {
        Objects.requireNonNull(to);
        validNickname(to);
        nickname = to;
    }

    public void changePassword(String to) {
        Objects.requireNonNull(to);
        valid(to);
        userPassword = to;
    }

    public void validNickname(String nickname) {
        Assert.isTrue(nickname.length() <= NAME_MAX_LENGTH, "최대 길이를 초과하였습니다.");
    }

    public void valid(String str) {
        Assert.isTrue(str.length() <= NAME_MAX_LENGTH, "최대 길이를 초과하였습니다.");
    }
}
