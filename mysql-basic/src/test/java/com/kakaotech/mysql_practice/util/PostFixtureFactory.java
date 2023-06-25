package com.kakaotech.mysql_practice.util;

import com.kakaotech.mysql_practice.domain.post.dto.DailyPostCountRequest;
import com.kakaotech.mysql_practice.domain.post.entity.Post;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.randomizers.number.LongRandomizer;
import org.jeasy.random.randomizers.range.LongRangeRandomizer;

import java.time.LocalDate;

import static org.jeasy.random.FieldPredicates.*;

public class PostFixtureFactory {
    static public EasyRandom get(Long memberId, LocalDate firstDate, LocalDate lastDate) {
        var idPredicate = named("id")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var memberIdPredicate = named("memberId")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var params = new EasyRandomParameters()
                .excludeField(idPredicate)
                .dateRange(firstDate, lastDate)
                .randomize(memberIdPredicate, () -> memberId);

        return new EasyRandom(params);
    }

    static public EasyRandom get(LocalDate start, LocalDate end) {
        var idPredicate = named("id")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var params = new EasyRandomParameters()
                .excludeField(idPredicate)
                .dateRange(start, end)
                .randomize(named("memberId"), new LongRangeRandomizer(1L, 1000001L));

        return new EasyRandom(params);
    }

    static public EasyRandom get(Long startId, Long endId, LocalDate startDate, LocalDate endDate) {
        var idPredicate = named("id")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var memberIdPredicate = named("memberId")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var params = new EasyRandomParameters()
                .excludeField(idPredicate)
                .dateRange(startDate, endDate)
                .randomize(memberIdPredicate, new LongRangeRandomizer(startId, endId));

        return new EasyRandom(params);
    }
}
