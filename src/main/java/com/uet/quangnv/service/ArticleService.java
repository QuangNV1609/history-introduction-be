package com.uet.quangnv.service;

import com.uet.quangnv.entities.Article;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public interface ArticleService {
    Article save(String title, String content, MultipartFile coverImage, MultipartFile thumbnailImage,
                 String historyDay, Integer postType, Long parentID);
}
