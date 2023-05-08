package com.uet.quangnv.service.impl;

import com.uet.quangnv.dto.ArticleDto;
import com.uet.quangnv.dto.UserDto;
import com.uet.quangnv.entities.Article;
import com.uet.quangnv.entities.File;
import com.uet.quangnv.exception.domain.ResoureNotFoundException;
import com.uet.quangnv.repository.ArticleRepository;
import com.uet.quangnv.service.ArticleService;
import com.uet.quangnv.service.Constant;
import com.uet.quangnv.service.FileService;
import com.uet.quangnv.ultis.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private FileService fileService;
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article saveArticle(String title, String content, MultipartFile coverImage, MultipartFile thumbnailImage,
                               String historyDay, Integer postType, Integer historicalPeriod, Long parentID) {
        Article article = new Article(title, content, Utils.convertStringToDate(historyDay, Utils.DateFormat.YYYYMMDD), postType, historicalPeriod, parentID);
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

    @Override
    public ArticleDto findArticleByID(Long articleID) throws ResoureNotFoundException {
        UserDto currentUserLogin = Utils.getCurrentUserLogin();
        ArticleDto articleDto;
        if (currentUserLogin.getRoleName().contains("ROLE_ADMIN")) {
            articleDto = articleRepository.getByArticleID(articleID, currentUserLogin.getUsername(), true);
        } else {
            articleDto = articleRepository.getByArticleID(articleID, currentUserLogin.getUsername(), false);
        }
        if (articleDto != null) {
            return articleDto;
        } else {
            throw new ResoureNotFoundException("Không tìm thấy bài viết!");
        }
    }

    @Override
    public List<ArticleDto> findAllArticleByUsername() {
        UserDto currentUserLogin = Utils.getCurrentUserLogin();
        List<ArticleDto> articleDtoList;
        if (currentUserLogin.getRoleName().contains("ROLE_ADMIN")) {
            articleDtoList = articleRepository.getAllByUserLogin(currentUserLogin.getUsername(), true);
        } else {
            articleDtoList = articleRepository.getAllByUserLogin(currentUserLogin.getUsername(), false);
        }
        return articleDtoList;
    }

    @Override
    public void censorship(Long articleId) {
        articleRepository.updateStatus(articleId);
    }

    @Override
    public void censorshipList(List<Long> articleIds) {
        articleRepository.updateStatusByListId(articleIds);
    }

    @Override
    public void deleteArticleByIDs(List<Long> articleIds) {
        UserDto currentUserLogin = Utils.getCurrentUserLogin();
        String username = null;
        if (!currentUserLogin.getRoleName().contains("ROLE_ADMIN")) {
            username = currentUserLogin.getUsername();
        }
        articleRepository.deleteArticleByIDs(articleIds, username);
    }

    @Override
    public List<ArticleDto> findArticleIsCensorship(Boolean isCensorship) {
        UserDto currentUserLogin = Utils.getCurrentUserLogin();
        String username = null;
        if (!currentUserLogin.getRoleName().contains("ROLE_ADMIN")) {
            username = currentUserLogin.getUsername();
        }
        return articleRepository.getByArticleIsCensorship(isCensorship, username);
    }

    @Override
    public List<ArticleDto> searchArticle(Integer historicalPeriod) {
        UserDto currentUserLogin = Utils.getCurrentUserLogin();
        boolean isAdmin = currentUserLogin.getRoleName().contains("ROLE_ADMIN");
        return articleRepository.searchArticle(isAdmin, historicalPeriod);
    }
}
