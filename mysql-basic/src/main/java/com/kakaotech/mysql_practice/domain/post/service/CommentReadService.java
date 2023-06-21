package com.kakaotech.mysql_practice.domain.post.service;

import com.kakaotech.mysql_practice.domain.post.dto.CommentDto;
import com.kakaotech.mysql_practice.domain.post.entity.Comment;
import com.kakaotech.mysql_practice.domain.post.repository.CommentRepository;
import com.kakaotech.mysql_practice.util.CursorRequest;
import com.kakaotech.mysql_practice.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentReadService {
    final private CommentRepository commentRepository;

    public PageCursor<Comment> getComments(Long postId, CursorRequest cursorRequest) {
        var comments = findAllBy(postId, cursorRequest);
        var nextKey = getNextKey(comments);
        return new PageCursor<>(cursorRequest.next(nextKey), comments);
    }

    public List<Comment> findAllBy(Long postId, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return commentRepository.findAllByLessThanIdAndPostIdAndOrderByIdDesc(cursorRequest.key(), postId, cursorRequest.size());
        }
        return commentRepository.findAllByPostIdAndOrderByIdDesc(postId, cursorRequest.size());
    }

    private CommentDto toDto(Comment comment) {
        return new CommentDto(
                comment.getPostId(),
                comment.getCommentId(),
                comment.getMemberId(),
                comment.getContents(),
                comment.getCreatedAt()
        );
    }

    private static Long getNextKey(List<Comment> comments) {
        return comments.stream()
                .mapToLong(Comment::getId)
                .min()
                .orElse(CursorRequest.NONE_KEY);
    }
}
