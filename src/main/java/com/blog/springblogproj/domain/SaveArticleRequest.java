package com.blog.springblogproj.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveArticleRequest {
    private String title;
    private String content;

    public Article mapper() {
        return new Article(title, content);
    }
}
