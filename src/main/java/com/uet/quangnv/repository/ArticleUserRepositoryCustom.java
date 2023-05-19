package com.uet.quangnv.repository;

import com.uet.quangnv.dto.ArticleUserDto;

import java.util.List;

public interface ArticleUserRepositoryCustom {
    List<ArticleUserDto> getListArticleByUser(String username);

    List<ArticleUserDto> getListArticleManyViews();

    ArticleUserDto getArticleViews(Long articleID);
}
