package com.uet.quangnv.service.impl;

import com.uet.quangnv.entities.Article;
import com.uet.quangnv.entities.File;
import com.uet.quangnv.repository.ArticleRepository;
import com.uet.quangnv.service.ArticleService;
import com.uet.quangnv.service.Constant;
import com.uet.quangnv.service.FileService;
import com.uet.quangnv.ultis.Ultis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private FileService fileService;
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article save(String title, String content, MultipartFile coverImage, MultipartFile thumbnailImage,
                        String historyDay, Integer postType, Long parentID) {
        Article article = new Article(title, content, Ultis.convertStringToDate(historyDay, Ultis.DateFormat.YYYYMMDD), postType, parentID);
        article.setStatus(0);
        article = articleRepository.save(article);
        File coverImageSaved = fileService.saveFile(coverImage, title, Constant.FileType.IMAGE,
                Constant.FileReferenceType.ARTICLE, article.getId());
        File thumbnailImageSave = fileService.saveFile(thumbnailImage, title, Constant.FileType.IMAGE,
                Constant.FileReferenceType.ARTICLE, article.getId());
        article.setCoverImage(coverImageSaved.getId());
        article.setThumbnailImage(thumbnailImageSave.getId());
        articleRepository.updateCoverImageAndThumbnailImage(article.getId(), coverImageSaved.getId(), thumbnailImageSave.getId());
        return article;
    }
}
