package com.kakaotech.mysql_practice.domain.post.dto;

import java.time.LocalDateTime;

public record CommentDto (
        Long postId,
        Long commentId,
        Long memberId,
        String contents,
        LocalDateTime createdAt
) {
}
