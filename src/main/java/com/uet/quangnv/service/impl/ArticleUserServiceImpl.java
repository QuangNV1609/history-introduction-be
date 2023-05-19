package com.uet.quangnv.service.impl;

import com.uet.quangnv.dto.ArticleUserDto;
import com.uet.quangnv.dto.UserDto;
import com.uet.quangnv.entities.Article;
import com.uet.quangnv.entities.ArticleUser;
import com.uet.quangnv.repository.ArticleRepository;
import com.uet.quangnv.repository.ArticleUserRepository;
import com.uet.quangnv.service.ArticleUserService;
import com.uet.quangnv.ultis.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArticleUserServiceImpl implements ArticleUserService {

    @Autowired
    private ArticleUserRepository articleUserRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void addViewArticle(Long articleID) {
        UserDto currentUserLogin = Utils.getCurrentUserLogin();
        Optional<Article> optionalArticle = articleRepository.findById(articleID);
        if (optionalArticle.isPresent() && optionalArticle.get().getStatus() == 1) {
            Optional<ArticleUser> optional = articleUserRepository.findByArticleIdAndUser(currentUserLogin.getUsername(), articleID);
            if (optional.isPresent()) {
                articleUserRepository.addViewForArticle(optional.get().getQuantity() + 1, currentUserLogin.getUsername(), articleID);
            } else {
                ArticleUser articleUser = new ArticleUser(currentUserLogin.getUsername(), articleID, 1L);
                articleUserRepository.save(articleUser);
            }
        }
    }

    @Override
    public List<ArticleUserDto> getListArticleByUser() {
        UserDto currentUserLogin = Utils.getCurrentUserLogin();
        return articleUserRepository.getListArticleByUser(currentUserLogin.getUsername());
    }

    @Override
    public List<ArticleUserDto> getListArticleManyView() {
        return articleUserRepository.getListArticleManyViews();
    }
}
