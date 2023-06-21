package com.kakaotech.mysql_practice.domain.post.service;

import com.kakaotech.mysql_practice.domain.post.dto.CommentCommand;
import com.kakaotech.mysql_practice.domain.post.entity.Comment;
import com.kakaotech.mysql_practice.domain.post.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentWriteService {
    final private CommentRepository commentRepository;

    public Long create(CommentCommand commentCommand) {
        var comment = Comment.builder()
                .memberId(commentCommand.memberId())
                .postId(commentCommand.postId())
                .commentId(commentCommand.commentId())
                .contents(commentCommand.contents())
                .build();

        return commentRepository.save(comment).getId();
    }
}
