package com.kakaotech.mysql_practice.domain.post.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Comment {

    final private Long id;
    final private Long postId;
    final private Long commentId;
    final private Long memberId;
    final private String contents;
    final private LocalDateTime createdAt;

    @Builder
    public Comment(Long id, Long postId, Long commentId, Long memberId, String contents, LocalDateTime createdAt) {
        this.id = id;
        this.postId = Objects.requireNonNull(postId);
        this.commentId = commentId;
        this.memberId = Objects.requireNonNull(memberId);
        this.contents = Objects.requireNonNull(contents);
        this.createdAt = createdAt == null? LocalDateTime.now() : createdAt;
    }
}
