package com.kakaotech.mysql_practice.application.controller;

import com.kakaotech.mysql_practice.application.usecase.CreatePostLikeUsecase;
import com.kakaotech.mysql_practice.application.usecase.CreatePostUsecase;
import com.kakaotech.mysql_practice.application.usecase.GetTimelinePostsUsecase;
import com.kakaotech.mysql_practice.domain.post.dto.*;
import com.kakaotech.mysql_practice.domain.post.entity.Comment;
import com.kakaotech.mysql_practice.domain.post.entity.Post;
import com.kakaotech.mysql_practice.domain.post.service.CommentReadService;
import com.kakaotech.mysql_practice.domain.post.service.CommentWriteService;
import com.kakaotech.mysql_practice.domain.post.service.PostReadService;
import com.kakaotech.mysql_practice.domain.post.service.PostWriteService;
import com.kakaotech.mysql_practice.util.CursorRequest;
import com.kakaotech.mysql_practice.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    final private PostWriteService postWriteService;
    final private PostReadService postReadService;
    final private GetTimelinePostsUsecase getTimelinePostsUsecase;
    final private CreatePostUsecase createPostUsecase;
    final private CreatePostLikeUsecase createPostLikeUsecase;
    final private CommentReadService commentReadService;
    final private CommentWriteService commentWriteService;

    @PostMapping("")
    public Long create(PostCommand command) {
        return createPostUsecase.execute(command);
    }

//    @PostMapping("/daily-post-counts")
    @GetMapping("/daily-post-counts")
    public List<DailyPostCount> getDailyPostCounts(@RequestBody DailyPostCountRequest request) {
        return postReadService.getDailyPostCounts(request);
    }

    @GetMapping("/members/{memberId}")
    public Page<PostDto> getPosts(
            @PathVariable Long memberId,
            Pageable pageable
    ) {
        return postReadService.getPosts(memberId, pageable);
    }

    @GetMapping("/members/{memberId}/by-cursor")
    public PageCursor<Post> getPostsByCursor(
            @PathVariable Long memberId,
            CursorRequest cursorRequest
    ) {
        return postReadService.getPosts(memberId, cursorRequest);
    }

    @GetMapping("/member/{memberId}/timeline")
    public PageCursor<Post> getTimeline(
            @PathVariable Long memberId,
            CursorRequest cursorRequest
    ) {
//        return getTimelinePostsUsecase.execute(memberId, cursorRequest);
        return getTimelinePostsUsecase.executeByTimeline(memberId, cursorRequest);
    }

    @PostMapping("/{postId}/v1")
    public void likePost(@PathVariable Long postId) {
//        postWriteService.likePost(postId);
        postWriteService.likePostByOptimisticLock(postId);
    }

    @PostMapping("/{postId}/v2")
    public void likePost2(@PathVariable Long postId, @RequestParam Long memberId) {
        createPostLikeUsecase.execute(postId, memberId);
    }

    @PostMapping("/comments/")
    public Long create(CommentCommand commend) {
        return commentWriteService.create(commend);
    }

    @GetMapping("/comments/{postId}/by-cursor")
    public PageCursor<Comment> getComments(
            @PathVariable Long postId,
            CursorRequest cursorRequest
    ) {
        return commentReadService.getComments(postId, cursorRequest);
    }
}
