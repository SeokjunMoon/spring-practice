package com.kakaotech.mysql_practice.domain.post.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class PostLike {
    final private Long id;
    final private Long memberId;
    final private Long postId;
    final private LocalDateTime createdAt;

    @Builder
    public PostLike(Long id, Long memberId, Long postId, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = Objects.requireNonNull(memberId);
        this.postId = Objects.requireNonNull(postId);
        // 생각해보니 createdAt은 id가 최초 할당되는 시점에서만 업데이트 되어야 함
        this.createdAt = createdAt == null? LocalDateTime.now() : createdAt;
    }
}
