package com.uet.quangnv.repository;

import com.uet.quangnv.dto.ArticleDto;
import com.uet.quangnv.entities.Article;

import java.util.List;

public interface ArticleRepositoryCustom {
    List<ArticleDto> getAllByUserLogin(String username, Boolean isAdmin);

    ArticleDto getByArticleID(Long articleID, String username, Boolean isAdmin);

    List<ArticleDto> getByArticleIsCensorship(Boolean isCensorship, String username);

    List<ArticleDto> searchArticle(Boolean isAdmin, Integer historicalPeriod, String historyDay, Integer status, Integer postType, Integer content, String username);

    void deleteArticleByIDs(List<Long> ids, String username);

    void updateArticle(Article article);
}
