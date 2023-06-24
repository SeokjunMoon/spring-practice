package com.kakaotech.mysql_practice.domain.member.repository;

import com.kakaotech.mysql_practice.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberRepository {
    final private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    final static private String TABLE = "member";

    /*
        여기서 BeanPropertyRowMapper를 사용하려면 Member에서 Setter를 다 열어줘야 하는데
        그러면 어디서든 멤버의 값을 다 바꿀 수 있어서 규모가 커질수록 사이드 이팩트를 무시할 수 없음
        따라서 Setter를 여는건 진짜진짜 필요할 때만 열어주자
        그래서 번거롭지만 RowMapper를 사용함
     */
    RowMapper<Member> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Member
            .builder()
            .id(resultSet.getLong("id"))
            .email(resultSet.getString("email"))
            .nickname(resultSet.getString("nickname"))
            .birthday(resultSet.getObject("birthday", LocalDate.class))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .userId(resultSet.getString("userId"))
            .userPassword(resultSet.getString("userPassword"))
            .build();

    public Optional<Member> findById(Long id) {
        var sql = String.format("SELECT * FROM %s WHERE id = :id", TABLE);
        var params = new MapSqlParameterSource()
                .addValue("id", id);

        /*
            아래 코드는 이거랑 동일함
            List<Member> members = namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
            Member nullableMember = DataAccessUtils.singleResult(members);
            return Optional.ofNullable(nullableMember);
         */
        var member = namedParameterJdbcTemplate.queryForObject(sql, params, ROW_MAPPER);
        return Optional.ofNullable(member);
    }

    public Optional<Member> findByUserId(String userId) {
        var sql = String.format("SELECT * FROM %s WHERE userId = :userId", TABLE);
        var params = new MapSqlParameterSource().addValue("userId", userId);
        Member member = null;

        try {
            member = namedParameterJdbcTemplate.queryForObject(sql, params, ROW_MAPPER);
        }
        catch (Exception error) {}

        return Optional.ofNullable(member);
    }

    public List<Member> findAllByIdIn(List<Long> ids) {
        if (ids.isEmpty()) {
            return List.of();
        }

        var sql = String.format("SELECT * FROM %s WHERE id in (:ids)", TABLE);
        var params = new MapSqlParameterSource().addValue("ids", ids);
        return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
    }

    public Member save(Member member) {
        if (member.getId() == null) {
            return insert(member);
        }
        return update(member);
    }

    public void bulkInsert(List<Member> members) {
        var sql = String.format("""
                INSERT INTO %s (email, nickname, birthday, createdAt, userId, userPassword)
                VALUES (:email, :nickname, :birthday, :createdAt, :userId, :userPassword)
                """, TABLE);

        SqlParameterSource[] parameterSources = members.stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(SqlParameterSource[]::new);

        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
    }

    private Member insert(Member member) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("Member")
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();

        return Member
                .builder()
                .id(id)
                .email(member.getEmail())
                .nickname(member.getNickname())
                .birthday(member.getBirthday())
                .createdAt(member.getCreatedAt())
                .userId(member.getUserId())
                .userPassword(member.getUserPassword())
                .build();
    }

    private Member update(Member member) {
        var sql = String.format("""
            UPDATE %s set
                email = :email,
                nickname = :nickname,
                birthday = :birthday,
                userId = :userId,
                userPassword = :userPassword
            WHERE id = :id""", TABLE);
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        namedParameterJdbcTemplate.update(sql, params);
        return member;
    }
}
