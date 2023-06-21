package com.kakaotech.mysql_practice.domain.post.repository;

import com.kakaotech.mysql_practice.domain.post.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CommentRepository {
    public static String TABLE = "Comment";
    final private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    final private static RowMapper<Comment> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Comment.builder()
            .id(resultSet.getLong("id"))
            .postId(resultSet.getLong("postId"))
            .commentId(resultSet.getLong("commentId"))
            .memberId(resultSet.getLong("memberId"))
            .contents(resultSet.getString("contents"))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();

    private Long getCount(Long postId) {
        var sql = String.format("SELECT count(id) FROM %s WHERE postId = :postId", TABLE);
        var params = new MapSqlParameterSource().addValue("postId", postId);
        return namedParameterJdbcTemplate.queryForObject(sql, params, Long.class);
    }

    public List<Comment> findAllByPostIdAndOrderByIdDesc(Long postId, int size) {
        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE postId = :postId
                ORDER BY id desc
                LIMIT :size
                """, TABLE);

        var params = new MapSqlParameterSource().addValue("postId", postId).addValue("size", size);
        return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
    }

    public List<Comment> findAllByLessThanIdAndPostIdAndOrderByIdDesc(Long id, Long postId, int size) {
        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE postId = :postId and id < :id
                ORDER BY id desc
                LIMIT :size""", TABLE);

        var params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("postId", postId)
                .addValue("size", size);

        return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
    }

    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            return insert(comment);
        }
        throw new UnsupportedOperationException("댓글은 수정을 지원하지 않습니다.");
    }

    private Comment insert(Comment comment) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");

        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(comment);
        var id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

        return Comment.builder()
                .id(id)
                .memberId(comment.getMemberId())
                .postId(comment.getPostId())
                .commentId(comment.getCommentId())
                .contents(comment.getContents())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
