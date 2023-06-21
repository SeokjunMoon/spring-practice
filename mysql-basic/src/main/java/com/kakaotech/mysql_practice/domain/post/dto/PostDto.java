package com.kakaotech.mysql_practice.domain.post.dto;

import java.time.LocalDateTime;

public record PostDto (
        Long id,
        String contents,
        LocalDateTime createdAt,
        Long likeCount
) {
}
