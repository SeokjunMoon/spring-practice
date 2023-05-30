package com.kakaotech.mysql_practice.util;

import com.kakaotech.mysql_practice.domain.member.entity.Member;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class MemberFixtureFactory {
    static public Member create() {
        var param = new EasyRandomParameters().seed(1);
        return new EasyRandom(param).nextObject(Member.class);
    }

    static public Member create(Long seed) {
        var param = new EasyRandomParameters().seed(seed);
        return new EasyRandom(param).nextObject(Member.class);
    }
}
