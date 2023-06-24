package com.kakaotech.mysql_practice.domain.follow;

import com.kakaotech.mysql_practice.domain.follow.entity.Follow;
import com.kakaotech.mysql_practice.domain.follow.repository.FollowRepository;
import com.kakaotech.mysql_practice.util.FollowFixtureFactory;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.stream.IntStream;

@SpringBootTest
public class FollowBulkInsertTest {
    @Autowired
    private FollowRepository followRepository;

    @DisplayName("팔로우 추가 테스트")
    @Test
    public void bulkInsert() {
        EasyRandom easyRandom = FollowFixtureFactory.get();

        var stopWatch = new StopWatch();
        stopWatch.start();

        var follows = IntStream.range(1, 1000000)
                .parallel()
                .mapToObj(i -> easyRandom.nextObject(Follow.class))
                .toList();

        stopWatch.stop();
        System.out.println("객체 생성 시간 : " + stopWatch.getTotalTimeSeconds());

        var queryStopWatch = new StopWatch();
        queryStopWatch.start();

        followRepository.bulkInsert(follows);

        queryStopWatch.stop();
        System.out.println("sql 삽입 시간 : " + queryStopWatch.getTotalTimeSeconds());
    }
}
