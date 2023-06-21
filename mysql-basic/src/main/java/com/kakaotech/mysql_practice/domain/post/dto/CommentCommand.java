package com.kakaotech.mysql_practice.domain.post.dto;

public record CommentCommand (
        Long memberId,
        Long postId,
        Long commentId,
        String contents
) {
}
