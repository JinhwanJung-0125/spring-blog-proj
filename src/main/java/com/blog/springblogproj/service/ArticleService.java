package com.blog.springblogproj.service;

import com.blog.springblogproj.domain.Article;
import com.blog.springblogproj.domain.ArticleResponse;
import com.blog.springblogproj.repository.ArticleRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    // 모든 article 조회
    public List<ArticleResponse> getAllArticles() {
        return articleRepository.selectAllArticles();
    }

    // id로 article 조회
    public ArticleResponse getArticleById(Long id) {
        return articleRepository.selectArticleById(id);
    }

    // article 등록
    public String saveArticle(Article article) {
        // title, content 둘 중 하나라도 null이면 "필수 정보 누락"
        if (!checkParams(article)) {
            return "필수 정보가 누락되었습니다.";
        }

        // 동일한 title이 이미 존재하면 "중복되는 제목 포스팅 존재"
        if (articleRepository.isExistsArticle(article)) {
            return "중복되는 제목의 포스팅이 존재합니다.";
        }

        return articleRepository.saveArticle(article).equals(1) ? "정상적으로 포스팅 되었습니다."
            : "포스팅 도중 문제가 발생했습니다.";
    }

    // Article 수정
    public String updateArticle(Article article) {
        // title, content 둘 다 null이면 "필수 정보 누락"
        if (article.getTitle() == null && article.getContent() == null) {
            return "필수 정보가 누락되었습니다.";
        }

        // id에 해당하는 article이 없으면 "해당 포스팅 미존재"
        if (!articleRepository.isExistsArticle(article)) {
            return "해당 포스팅을 찾을 수 없습니다.";
        }

        // 변경 내용 체크
        if (!checkChanges(article)) {
            return "변경할 내용이 없습니다.";
        }

        return articleRepository.updateArticle(article).equals(1) ? "정상적으로 수정되었습니다."
            : "수정 도중 문제가 발생했습니다.";
    }

    // article 삭제
    public String deleteArticle(Long id) {
        // id에 해당하는 article이 없으면 "해당 포스팅 미존재"
        if (!articleRepository.isExistsArticle(Article.builder().id(id).build())) {
            return "해당 포스팅을 찾을 수 없습니다.";
        }

        return articleRepository.deleteArticle(id).equals(1) ? "정상적으로 삭제되었습니다."
            : "삭제 도중 문제가 발생했습니다.";
    }

    // 필수 요구 Parameter인 title과 content 둘 중 하나라도 없으면 false
    private boolean checkParams(Article article) {
        return article.getTitle() != null && article.getContent() != null;
    }

    // 변경 내용 체크
    private boolean checkChanges(Article article) {
        // 똑같은 Article 조회
        ArticleResponse currentArticle = articleRepository.selectArticleById(article.getId());

        int count = 0;

        // 변경하려는 title이 없거나 빈 칸이거나 기존 내용과 똑같으면 +1
        if (article.getTitle() == null || (article.getTitle().equals(currentArticle.getTitle()) || article.getTitle().isBlank())) {
            count++;
        }

        // 변경하려는 content가 없거나 기존 내용과 똑같으면 +1
        if (article.getContent() == null || article.getContent().equals(currentArticle.getContent())) {
            count++;
        }

        // 위의 두가지 모두에 해당하면 변경점이 없음
        return count != 2;
    }
}
