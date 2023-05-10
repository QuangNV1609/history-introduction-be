package com.uet.quangnv.service;

import com.uet.quangnv.dto.ArticleDto;
import com.uet.quangnv.entities.Article;
import com.uet.quangnv.exception.domain.ResoureNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {
    Article saveArticle(String title, String content, MultipartFile coverImage, MultipartFile thumbnailImage,
                        String historyDay, Integer postType, Integer historicalPeriod, Long parentID);

    ArticleDto findArticleByID(Long articleID) throws ResoureNotFoundException;

    List<ArticleDto> findAllArticleByUsername();

    void censorship(Long articleId);

    void censorshipList(List<Long> articleIds);

    void deleteArticleByIDs(List<Long> articleIds);

    List<ArticleDto> findArticleIsCensorship(Boolean isCensorship);
    List<ArticleDto> searchArticle(Integer historicalPeriod, String historyDay);
}
