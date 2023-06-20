package com.kakaotech.mysql_practice.domain.post.service;

import com.kakaotech.mysql_practice.domain.post.entity.Timeline;
import com.kakaotech.mysql_practice.domain.post.repository.TimelineRepository;
import com.kakaotech.mysql_practice.util.CursorRequest;
import com.kakaotech.mysql_practice.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TimelineReadService {
    final private TimelineRepository timelineRepository;

//    public List<Timeline> getTimelines(Long memberId, CursorRequest cursorRequest) {
//        return findAllBy(memberId, cursorRequest);
//    }
    public PageCursor<Timeline> getTimelines(Long memberId, CursorRequest cursorRequest) {
        var timelines = findAllBy(memberId, cursorRequest);
        var nextKey = timelines.stream()
                .mapToLong(Timeline::getId)
                .min().orElse(CursorRequest.NONE_KEY);

        return new PageCursor(cursorRequest.next(nextKey), timelines);
    }

    private List<Timeline> findAllBy(Long memberId, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return timelineRepository.findAllByLessThanIdAndMemberIdAndOrderByIdDesc(cursorRequest.key(), memberId, cursorRequest.size());
        }

        return timelineRepository.findAllByMemberIdAndOrderByIdDesc(memberId, cursorRequest.size());
    }
}
