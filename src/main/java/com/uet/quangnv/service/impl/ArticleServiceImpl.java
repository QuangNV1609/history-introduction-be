package com.uet.quangnv.service.impl;

import com.uet.quangnv.dto.ArticleDto;
import com.uet.quangnv.dto.UserDto;
import com.uet.quangnv.entities.Article;
import com.uet.quangnv.entities.ArticleUser;
import com.uet.quangnv.entities.File;
import com.uet.quangnv.entities.User;
import com.uet.quangnv.exception.domain.ResoureNotFoundException;
import com.uet.quangnv.repository.ArticleRepository;
import com.uet.quangnv.repository.ArticleUserRepository;
import com.uet.quangnv.repository.UserRepository;
import com.uet.quangnv.service.ArticleService;
import com.uet.quangnv.service.ArticleUserService;
import com.uet.quangnv.service.Constant;
import com.uet.quangnv.service.FileService;
import com.uet.quangnv.ultis.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private FileService fileService;
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleUserRepository articleUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Article saveArticle(String title, String content, MultipartFile coverImage, MultipartFile thumbnailImage, String historyDay, Integer postType, Integer historicalPeriod, Long parentID) throws ResoureNotFoundException {
        Article article = new Article(title, content, (historyDay != null && !historyDay.trim().isEmpty() && !historyDay.equals("null")) ? Utils.convertStringToDate(historyDay, Utils.DateFormat.YYYYMMDD) : null, postType, historicalPeriod, parentID);
        Date createAt = null;
        if (parentID != null) {
            Optional<Article> articleParent = articleRepository.findById(parentID);
            if (articleParent.isPresent()) {
                if (articleParent.get().getStatus() == 0) {
                    if (coverImage != null) {
                        File coverImageSaved = fileService.saveFile(coverImage, title, Constant.FileType.IMAGE, Constant.FileReferenceType.ARTICLE, parentID);
                        article.setCoverImage(coverImageSaved.getId());
                    }
                    if (thumbnailImage != null) {
                        File thumbnailImageSave = fileService.saveFile(thumbnailImage, title, Constant.FileType.IMAGE, Constant.FileReferenceType.ARTICLE, parentID);
                        article.setThumbnailImage(thumbnailImageSave.getId());
                    }
                    article.setId(parentID);
                    article.setVersion(articleParent.get().getVersion() == null ? 0 : articleParent.get().getVersion() + 1);
                    articleRepository.updateArticle(article);
                    return article;
                } else {
                    createAt = articleParent.get().getCreateAt();
                }
                article.setCoverImage(articleParent.get().getCoverImage());
                article.setThumbnailImage(articleParent.get().getThumbnailImage());
            } else {
                throw new ResoureNotFoundException("Bài viết cha không tồn tại!");
            }
        } else {
            article.setVersion(0);
        }
        article.setStatus(0);
        article = articleRepository.save(article);
        if (coverImage != null) {
            File coverImageSaved = fileService.saveFile(coverImage, title, Constant.FileType.IMAGE, Constant.FileReferenceType.ARTICLE, parentID);
            article.setCoverImage(coverImageSaved.getId());
        }
        if (thumbnailImage != null) {
            File thumbnailImageSave = fileService.saveFile(thumbnailImage, title, Constant.FileType.IMAGE, Constant.FileReferenceType.ARTICLE, parentID);
            article.setThumbnailImage(thumbnailImageSave.getId());
        }
        article.setCreateAt(createAt);
        articleRepository.updateArticle(article);
        return article;
    }

    @Override
    public ArticleDto findArticleByID(Long articleID) throws ResoureNotFoundException {
        UserDto currentUserLogin = Utils.getCurrentUserLogin();
        ArticleDto articleDto;
        if (currentUserLogin.getRoleName().size() > 0 && currentUserLogin.getRoleName().get(0).equals("ROLE_ADMIN")) {
            articleDto = articleRepository.getByArticleID(articleID, currentUserLogin.getUsername(), true);
        } else {
            articleDto = articleRepository.getByArticleID(articleID, currentUserLogin.getUsername(), false);
        }
        if (articleDto != null) {
            Optional<Article> optionalArticle = articleRepository.findById(articleID);
            if (optionalArticle.isPresent() && optionalArticle.get().getStatus() == 1) {
                Optional<User> optionalUser = userRepository.findById(currentUserLogin.getUsername());
                if (optionalUser.isPresent()) {
                    Optional<ArticleUser> optional = articleUserRepository.findByArticleIdAndUser(currentUserLogin.getUsername(), articleID);
                    if (optional.isPresent()) {
                        articleUserRepository.addViewForArticle(optional.get().getQuantity() + 1, currentUserLogin.getUsername(), articleID);
                    } else {
                        ArticleUser articleUser = new ArticleUser(currentUserLogin.getUsername(), articleID, 1L);
                        articleUserRepository.save(articleUser);
                    }
                    articleUserRepository.updateLastDateView(new Date(), currentUserLogin.getUsername(), articleID);
                } else {
                    Optional<ArticleUser> optional = articleUserRepository.findByArticleIdAndUserIsNull(articleID);
                    if (optional.isPresent()) {
                        articleUserRepository.addViewForArticleAndUserIsNull(optional.get().getQuantity() + 1, articleID);
                    } else {
                        ArticleUser articleUser = new ArticleUser(null, articleID, 1L);
                        articleUserRepository.save(articleUser);
                    }
                }
            }
            return articleDto;
        } else {
            throw new ResoureNotFoundException("Không tìm thấy bài viết!");
        }
    }

    @Override
    public List<ArticleDto> findAllArticleByUsername() {
        UserDto currentUserLogin = Utils.getCurrentUserLogin();
        List<ArticleDto> articleDtoList;
        if (currentUserLogin.getRoleName().size() > 0 && currentUserLogin.getRoleName().get(0).equals("ROLE_ADMIN")) {
            articleDtoList = articleRepository.getAllByUserLogin(currentUserLogin.getUsername(), true);
        } else {
            articleDtoList = articleRepository.getAllByUserLogin(currentUserLogin.getUsername(), false);
        }
        return articleDtoList;
    }

    @Override
    public void censorship(Long articleId) throws ResoureNotFoundException {
        Optional<Article> optional = articleRepository.findById(articleId);
        if (optional.isPresent()) {
            articleRepository.updateStatus(articleId);
            if (optional.get().getParentID() != null) {
                articleRepository.updateStatusByListParentId(List.of(optional.get().getParentID()));
            }
        } else {
            throw new ResoureNotFoundException("Không tìm thấy bài viết!");
        }

    }

    @Override
    public void censorshipList(List<Long> articleIds) {
        List<Long> parentIDs = articleRepository.getArticleById(articleIds);
        articleRepository.updateStatusByListId(articleIds);
        articleRepository.updateStatusByListParentId(parentIDs);
    }

    @Override
    public void deleteArticleByIDs(List<Long> articleIds) {
        UserDto currentUserLogin = Utils.getCurrentUserLogin();
        String username = null;
        if (!currentUserLogin.getRoleName().get(0).equals("ROLE_ADMIN")) {
            username = currentUserLogin.getUsername();
        }
        articleRepository.deleteArticleByIDs(articleIds, username);
    }

    @Override
    public List<ArticleDto> findArticleIsCensorship(Boolean isCensorship) {
        UserDto currentUserLogin = Utils.getCurrentUserLogin();
        String username = null;
        if (!currentUserLogin.getRoleName().get(0).equals("ROLE_ADMIN")) {
            username = currentUserLogin.getUsername();
        }
        return articleRepository.getByArticleIsCensorship(isCensorship, username);
    }

    @Override
    public List<ArticleDto> searchArticle(Integer historicalPeriod, String historyDay, Integer status, Integer postType, Integer content, Boolean isUsername) {
        UserDto currentUserLogin = Utils.getCurrentUserLogin();
        boolean isAdmin = false;
        if (currentUserLogin.getRoleName().size() > 0) {
            isAdmin = currentUserLogin.getRoleName().get(0).equals("ROLE_ADMIN");
        }
        return articleRepository.searchArticle(isAdmin, historicalPeriod, historyDay, status, postType, content, (!isAdmin && isUsername) ? currentUserLogin.getUsername() : null);
    }
}
