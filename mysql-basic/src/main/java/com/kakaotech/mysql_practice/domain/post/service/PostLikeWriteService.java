package com.kakaotech.mysql_practice.domain.post.service;

import com.kakaotech.mysql_practice.domain.member.dto.MemberDto;
import com.kakaotech.mysql_practice.domain.post.entity.Post;
import com.kakaotech.mysql_practice.domain.post.entity.PostLike;
import com.kakaotech.mysql_practice.domain.post.repository.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostLikeWriteService {
    final private PostLikeRepository postLikeRepository;

    public void create(Post post, MemberDto memberDto) {
        var postLike = PostLike.builder()
                .postId(post.getId())
                .memberId(memberDto.id())
                .build();

        postLikeRepository.save(postLike);
    }
}
