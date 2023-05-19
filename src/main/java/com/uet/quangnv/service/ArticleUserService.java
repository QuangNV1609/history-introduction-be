package com.uet.quangnv.service;

import com.uet.quangnv.dto.ArticleUserDto;

import java.util.List;

public interface ArticleUserService {
    void addViewArticle(Long articleID);

    List<ArticleUserDto> getListArticleByUser();

    List<ArticleUserDto> getListArticleManyView();

    ArticleUserDto getArticleView(Long articleId);
}
