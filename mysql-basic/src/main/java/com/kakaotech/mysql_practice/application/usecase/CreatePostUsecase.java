package com.kakaotech.mysql_practice.application.usecase;

import com.kakaotech.mysql_practice.domain.follow.dto.FollowDto;
import com.kakaotech.mysql_practice.domain.follow.service.FollowReadService;
import com.kakaotech.mysql_practice.domain.post.dto.PostCommand;
import com.kakaotech.mysql_practice.domain.post.service.PostWriteService;
import com.kakaotech.mysql_practice.domain.post.service.TimelineWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreatePostUsecase {
    final private PostWriteService postWriteService;

    final private FollowReadService followReadService;

    final private TimelineWriteService timelineWriteService;

    /*
    이 경우 팔로워 수가 많아질수록 트렌젝션 길이가 매우 길어져서
    성능이 떨어질 수 있다
    따라서 대용량 시스템에선 이러한 점도 고려해서
    트렌젝션을 사용해야함
    따라서 트렌젝션은 항상 길이가 짧아야 한다
     */
    @Transactional
    public Long execute(PostCommand postCommand) {
        var postId = postWriteService.create(postCommand);
        var followerMemberIds = followReadService.getFollowers(postCommand.memberId()).stream()
                .map(FollowDto::fromMemberId).toList();

        timelineWriteService.deliveryToMemberIds(postId, followerMemberIds);

        return postId;
    }
}
