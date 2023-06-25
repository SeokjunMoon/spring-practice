package com.kakaotech.mysql_practice.domain.post;

import com.kakaotech.mysql_practice.application.usecase.CreatePostUsecase;
import com.kakaotech.mysql_practice.domain.post.dto.PostCommand;
import com.kakaotech.mysql_practice.domain.post.entity.Post;
import com.kakaotech.mysql_practice.domain.post.repository.PostRepository;
import com.kakaotech.mysql_practice.util.PostFixtureFactory;
import org.junit.jupiter.api.DisplayName;
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

    @Autowired
    private CreatePostUsecase createPostUsecase;

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

        posts.stream().parallel().forEach(post -> {
            createPostUsecase.execute(new PostCommand(post.getMemberId(), post.getContents()));
        });

        queryStopWatch.stop();
        System.out.println("sql 삽입 시간 : " + queryStopWatch.getTotalTimeSeconds());

    }

    /*
    2023-06-25 :
    1차 : 9.861507959 초
    2차 : 9.697758916 초
    3차 : 9.517366292 초
    4차 : 9.513005333 초
    5차 : 9.377053166 초
     */
    @DisplayName("100만 팔로워의 포스트 추가 테스트")
    @Test
    public void insertPostTest() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        createPostUsecase.execute(new PostCommand(1L, "test post"));

        stopWatch.stop();
        System.out.println("작업 시간 : " + stopWatch.getTotalTimeSeconds());
    }
}
