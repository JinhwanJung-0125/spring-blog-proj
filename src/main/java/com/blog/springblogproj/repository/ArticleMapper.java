package com.blog.springblogproj.repository;

import com.blog.springblogproj.domain.Article;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {
    // 모든 article 조회
    List<Article> selectAllArticles();

    // id로 article 조회
    Optional<Article> selectArticleById(Long id);

    // article 등록
    Integer saveArticle(Article article);

    // 존재하는 article인지 확인
    Boolean isExistsArticle(Article article);

    // article 수정
    Integer updateArticle(Article article);

    // article 삭제
    Integer deleteArticle(Long id);
}
