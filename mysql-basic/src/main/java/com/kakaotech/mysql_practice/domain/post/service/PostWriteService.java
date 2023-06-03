package com.kakaotech.mysql_practice.domain.post.service;

import com.kakaotech.mysql_practice.domain.post.dto.PostCommand;
import com.kakaotech.mysql_practice.domain.post.entity.Post;
import com.kakaotech.mysql_practice.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostWriteService {
    final private PostRepository postRepository;

    public Long create(PostCommand command) {
        var post = Post
                .builder()
                .memberId(command.memberId())
                .contents(command.contents())

                .build();

        return postRepository.save(post).getId();
    }
}
