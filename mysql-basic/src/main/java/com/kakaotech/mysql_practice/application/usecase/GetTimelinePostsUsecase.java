package com.kakaotech.mysql_practice.application.usecase;

import com.kakaotech.mysql_practice.domain.follow.dto.FollowDto;
import com.kakaotech.mysql_practice.domain.follow.service.FollowReadService;
import com.kakaotech.mysql_practice.domain.post.entity.Post;
import com.kakaotech.mysql_practice.domain.post.entity.Timeline;
import com.kakaotech.mysql_practice.domain.post.service.PostReadService;
import com.kakaotech.mysql_practice.domain.post.service.TimelineReadService;
import com.kakaotech.mysql_practice.util.CursorRequest;
import com.kakaotech.mysql_practice.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetTimelinePostsUsecase {
    final private FollowReadService followReadService;
    final private PostReadService postReadService;
    final private TimelineReadService timelineReadService;

    public PageCursor<Post> execute(Long memberId, CursorRequest cursorRequest) {
        var followings = followReadService.getFollowings(memberId);
        var followingMemberIds = followings.stream().map(FollowDto::getToMemberId).toList();
        return postReadService.getPosts(followingMemberIds, cursorRequest);
    }

    public PageCursor<Post> executeByTimeline(Long memberId, CursorRequest cursorRequest) {
//        var followings = followReadService.getFollowings(memberId);
//        var followingMemberIds = followings.stream().map(FollowDto::getToMemberId).toList();
        var timelines = timelineReadService.getTimelines(memberId, cursorRequest);
        var postIds = timelines.body().stream().map(Timeline::getPostId).toList();
        var posts = postReadService.getPosts(postIds);

        return new PageCursor(timelines.nextCursorRequest(), posts);
//        return postReadService.getPosts(followingMemberIds, cursorRequest);
    }
}
