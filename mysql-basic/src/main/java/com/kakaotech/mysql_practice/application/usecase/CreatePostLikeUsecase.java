package com.kakaotech.mysql_practice.application.usecase;

import com.kakaotech.mysql_practice.domain.member.service.MemberReadService;
import com.kakaotech.mysql_practice.domain.post.service.PostLikeWriteService;
import com.kakaotech.mysql_practice.domain.post.service.PostReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatePostLikeUsecase {
    final private PostReadService postReadService;
    final private MemberReadService memberReadService;
    final private PostLikeWriteService postLikeWriteService;

    public void execute(Long postId, Long memberId) {
        var post = postReadService.getPost(postId);
        var member = memberReadService.getMember(memberId);
        postLikeWriteService.create(post, member);
    }
}
