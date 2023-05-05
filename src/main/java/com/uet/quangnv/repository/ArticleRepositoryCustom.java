package com.uet.quangnv.repository;

import com.uet.quangnv.dto.ArticleDto;

import java.util.List;

public interface ArticleRepositoryCustom {
    List<ArticleDto> getAllByUserLogin(String username, Boolean isAdmin);

    ArticleDto getByArticleID(Long articleID, String username, Boolean isAdmin);

    List<ArticleDto> getByArticleIsCensorship(Boolean isCensorship);
}
