package com.kakaotech.mysql_practice.domain.member;

import com.kakaotech.mysql_practice.domain.member.entity.Member;
import com.kakaotech.mysql_practice.domain.member.repository.MemberRepository;
import com.kakaotech.mysql_practice.util.MemberFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberBulkInsertTest {
    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("멤버 추가 테스트")
    @Test
    public void bulkInsert() {
        var easyRandom = MemberFixtureFactory.create(
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2023, 6, 24)
        );

        var stopWatch = new StopWatch();
        stopWatch.start();

        var members = IntStream.range(0, 1000000)
                .parallel()
                .mapToObj(i -> easyRandom.nextObject(Member.class))
                .toList();

        stopWatch.stop();
        System.out.println("객체 생성 시간 : " + stopWatch.getTotalTimeSeconds());

        StopWatch queryStopWatch = new StopWatch();
        queryStopWatch.start();

        memberRepository.bulkInsert(members);

        queryStopWatch.stop();
        System.out.println("sql 삽입 시간 : " + queryStopWatch.getTotalTimeSeconds());
    }
}
