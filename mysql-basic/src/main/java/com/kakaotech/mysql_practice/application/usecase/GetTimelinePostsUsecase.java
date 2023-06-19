package com.kakaotech.mysql_practice.application.usecase;

import com.kakaotech.mysql_practice.domain.follow.dto.FollowDto;
import com.kakaotech.mysql_practice.domain.follow.service.FollowReadService;
import com.kakaotech.mysql_practice.domain.post.entity.Post;
import com.kakaotech.mysql_practice.domain.post.service.PostReadService;
import com.kakaotech.mysql_practice.util.CursorRequest;
import com.kakaotech.mysql_practice.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetTimelinePostsUsecase {
    final private FollowReadService followReadService;
    final private PostReadService postReadService;

    public PageCursor<Post> execute(Long memberId, CursorRequest cursorRequest) {
        var followings = followReadService.getFollowings(memberId);
        var followingMemberIds = followings.stream().map(FollowDto::getToMemberId).toList();
        return postReadService.getPosts(followingMemberIds, cursorRequest);
    }
}
