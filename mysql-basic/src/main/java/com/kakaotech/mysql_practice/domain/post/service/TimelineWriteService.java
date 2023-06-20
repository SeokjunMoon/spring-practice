package com.kakaotech.mysql_practice.domain.post.service;

import com.kakaotech.mysql_practice.domain.post.entity.Timeline;
import com.kakaotech.mysql_practice.domain.post.repository.TimelineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TimelineWriteService {
    final private TimelineRepository timelineRepository;

    public void deliveryToMemberIds(Long postId, List<Long> toMemberIds) {
        var timelines = toMemberIds.stream()
                .map((memberId) -> toTimeline(postId, memberId))
                .toList();

        timelineRepository.bulkInsert(timelines);
    }

    private static Timeline toTimeline(Long postId, Long memberId) {
        return Timeline
                .builder()
                .memberId(memberId)
                .postId(postId)
                .build();
    }
}
