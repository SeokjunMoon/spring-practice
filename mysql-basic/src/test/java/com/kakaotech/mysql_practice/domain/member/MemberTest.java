package com.kakaotech.mysql_practice.domain.member;

import com.kakaotech.mysql_practice.domain.member.entity.Member;
import com.kakaotech.mysql_practice.util.MemberFixtureFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.LongStream;

public class MemberTest {

    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    @Test
    public  void testChangeName() {
//        LongStream.range(0, 10)
//                .mapToObj(i -> MemberFixtureFactory.create(i))
//                .forEach(member -> {
//                    System.out.println(member.getNickname());
//                });
        var member = MemberFixtureFactory.create();
        var expected = "pnu";

        member.changeNickname(expected);

        Assertions.assertEquals(expected, member.getNickname());
    }

    @DisplayName("회원의 닉네임은 10자를 초과할 수 없다.")
    @Test
    public  void testNicknameLength() {
//        LongStream.range(0, 1000000)
//                .mapToObj(MemberFixtureFactory::create)
//                .forEach(member -> {
//                    System.out.println(member.getNickname());
//                });
//        var member = MemberFixtureFactory.create();
//        var overMaxLengthName = "pnupnupnupnu";
//
//        Assertions.assertThrows(
//                IllegalArgumentException.class,
//                () -> member.changeNickname(overMaxLengthName)
//        );
    }

    @DisplayName("회원 추가 테스트")
    @Test
    public void testInsertMember() {
        LongStream.range(0, 1000000)
                .mapToObj(MemberFixtureFactory::create)
                .forEach(member -> {
                    System.out.println(member.getNickname());
                });
    }
}
