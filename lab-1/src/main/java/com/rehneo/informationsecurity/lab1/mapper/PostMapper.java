package com.rehneo.informationsecurity.lab1.mapper;

import com.rehneo.informationsecurity.lab1.domain.Post;
import com.rehneo.informationsecurity.lab1.dto.PostDto;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

@Component
public class PostMapper {
    public PostDto map(Post post) {
        return new PostDto(
                post.getId(),
                HtmlUtils.htmlEscape(post.getTitle()),
                HtmlUtils.htmlEscape(post.getContent()),
                HtmlUtils.htmlEscape(post.getCreatedAt().toString())
        );
    }
}
