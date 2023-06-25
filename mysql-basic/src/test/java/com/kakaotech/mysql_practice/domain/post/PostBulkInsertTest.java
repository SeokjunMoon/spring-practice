package com.kakaotech.mysql_practice.domain.post;

import com.kakaotech.mysql_practice.domain.post.entity.Post;
import com.kakaotech.mysql_practice.domain.post.repository.PostRepository;
import com.kakaotech.mysql_practice.util.PostFixtureFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
public class PostBulkInsertTest  {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void bulkInsert() {
//        var easyRandom = PostFixtureFactory.get(
//                6L,
//                LocalDate.of(2000, 1, 1),
//                LocalDate.of(2023, 12, 31)
//        );

        var easyRandom = PostFixtureFactory.get(
                1L, 1000000L,
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2023, 6, 25)
        );

        var stopWatch = new StopWatch();
        stopWatch.start();

        var posts = IntStream.range(0, 10000 * 100 * 3)
                .parallel()
                .mapToObj(i -> easyRandom.nextObject(Post.class))
                .toList();

        stopWatch.stop();
        System.out.println("객체 생성 시간 : " + stopWatch.getTotalTimeSeconds());

        StopWatch queryStopWatch = new StopWatch();
        queryStopWatch.start();

        postRepository.bulkInsert(posts);

        queryStopWatch.stop();
        System.out.println("sql 삽입 시간 : " + queryStopWatch.getTotalTimeSeconds());

    }
}
