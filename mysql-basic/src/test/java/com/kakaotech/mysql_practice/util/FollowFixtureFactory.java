package com.kakaotech.mysql_practice.util;

import com.kakaotech.mysql_practice.domain.follow.entity.Follow;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.randomizers.range.LongRangeRandomizer;

import static org.jeasy.random.FieldPredicates.*;

public class FollowFixtureFactory {
    public static EasyRandom get() {
        var idPredicate = named("id")
                .and(ofType(Long.class))
                .and(inClass(Follow.class));

        EasyRandomParameters parameters = new EasyRandomParameters()
                .excludeField(idPredicate)
                .randomize(Long.class, new LongRangeRandomizer(1L, 1000001L));

        return new EasyRandom(parameters);
    }
}
