package com.kakaotech.mysql_practice.util;

import com.kakaotech.mysql_practice.domain.member.entity.Member;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.randomizers.text.StringRandomizer;

import java.time.LocalDate;

import static org.jeasy.random.FieldPredicates.*;

public class MemberFixtureFactory {
    static public Member create() {
        var param = new EasyRandomParameters().seed(1);
        return new EasyRandom(param).nextObject(Member.class);
    }

    static public Member create(Long seed) {
        var param = new EasyRandomParameters().seed(seed);
        return new EasyRandom(param).nextObject(Member.class);
    }

    static public EasyRandom create(LocalDate start, LocalDate end) {
        var idPredicate = named("id")
                .and(ofType(Long.class))
                .and(inClass(Member.class));

        var params = new EasyRandomParameters()
                .excludeField(idPredicate)
                .dateRange(start, end)
                .stringLengthRange(5, 20)
                .randomize(named("nickname"), new StringRandomizer(10));

        return new EasyRandom(params);
    }
}
