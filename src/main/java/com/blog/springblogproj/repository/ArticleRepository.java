package com.blog.springblogproj.repository;

import com.blog.springblogproj.domain.Article;
import com.blog.springblogproj.domain.ArticleResponse;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ArticleRepository {

    private final ArticleMapper articleMapper;

    // 모든 article 조회
    // 조회 결과인 Article 객체를 ArticleResponse 객체로 변환 후 반환.
    public List<ArticleResponse> selectAllArticles() {
        return articleMapper.selectAllArticles().stream()
            .map(ArticleResponse::convert).toList();
    }

    // id로 article 조회
    // 조회 결과인 Optional<Article> 객체를 ArticleResponse 객체로 변환 후 반환.
    // 조회 결과가 없으면(Null) 디폴트 ArticleResponse 객체로 변환 후 반환.
    public ArticleResponse selectArticleById(Long id) {
        Optional<Article> result = articleMapper.selectArticleById(id);
        return ArticleResponse.convert(result.orElse(createDefaultArticle()));
    }

    // article 등록
    public Integer saveArticle(Article article) {
        return articleMapper.saveArticle(article);
    }

    // 해당 article이 존재하는지 여부 확인
    public Boolean isExistsArticle(Article article) {
        return articleMapper.isExistsArticle(article);
    }

    // article 수정
    public Integer updateArticle(Article article) {
        return articleMapper.updateArticle(article);
    }

    // article 삭제
    public Integer deleteArticle(Long id) {
        return articleMapper.deleteArticle(id);
    }

    // 디폴트 ArticleResponse 생성
    private Article createDefaultArticle() {
        Article retVal = new Article();
        retVal.setId(-1L);
        retVal.setTitle("Not Found");
        retVal.setContent("No match ID");

        return retVal;
    }
}
