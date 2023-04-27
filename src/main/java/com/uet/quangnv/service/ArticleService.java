package com.uet.quangnv.service;

import com.uet.quangnv.dto.ArticleDto;
import com.uet.quangnv.entities.Article;
import com.uet.quangnv.exception.domain.ResoureNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {
    Article saveArticle(String title, String content, MultipartFile coverImage, MultipartFile thumbnailImage,
                        String historyDay, Integer postType, Long parentID);

    ArticleDto findArticleByID(Long articleID) throws ResoureNotFoundException;

    List<ArticleDto> findAllArticleByUsername();
}