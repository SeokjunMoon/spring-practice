package com.kakaotech.mysql_practice.domain.post.service;

import com.kakaotech.mysql_practice.domain.post.dto.DailyPostCount;
import com.kakaotech.mysql_practice.domain.post.dto.DailyPostCountRequest;
import com.kakaotech.mysql_practice.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostReadService {
    final private PostRepository postRepository;

    public List<DailyPostCount> getDailyPostCounts(DailyPostCountRequest request) {
        /*
            반환값 = [작성 일자, 작성 회원, 작성 게시물 개수] 의 리스트
            select createdDate, memberId, count(id)
            from POST
            where memberId = :memberId and createdDate between :firstDate and :lastDate
            group by createdDate, memberId
         */
        return postRepository.groupByCreatedDate(request);
    }
}
