package com.uet.quangnv.repository.impl;

import com.uet.quangnv.dto.ArticleUserDto;
import com.uet.quangnv.repository.ArticleUserRepositoryCustom;
import com.uet.quangnv.ultis.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleUserRepositoryImpl implements ArticleUserRepositoryCustom {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ArticleUserDto> getListArticleByUser(String username) {
        StringBuilder sql = new StringBuilder("SELECT\n" +
                "    article_user.username,\n" +
                "    article_user.article_id,\n" +
                "    article.title,\n" +
                "    article_user.quantity\n" +
                "FROM\n" +
                "    article_user\n" +
                "JOIN article ON article_user.article_id = article.id\n" +
                "WHERE\n" +
                "    article_user.username = :username \n" +
                "ORDER BY\n" +
                "    article_user.quantity DESC");
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        Query query = entityManager.createNativeQuery(sql.toString(), "ArticleUserDto");
        Utils.setParamQuery(query, params);
        return query.getResultList();
    }

    @Override
    public List<ArticleUserDto> getListArticleManyViews() {
        StringBuilder sql = new StringBuilder("SELECT\n" +
                "    article_user.article_id,\n" +
                "    article.title,\n" +
                "    COUNT(*) AS quantity\n" +
                "FROM\n" +
                "    article_user\n" +
                "JOIN article ON article_user.article_id = article.id\n" +
                "GROUP BY\n" +
                "    article_user.article_id,\n" +
                "    article.title\n" +
                "ORDER BY\n" +
                "    article_user.quantity\n" +
                "DESC\n" +
                "    ");
        Map<String, Object> params = new HashMap<>();
        Query query = entityManager.createNativeQuery(sql.toString(), "ArticleUserDto");
        Utils.setParamQuery(query, params);
        return query.getResultList();
    }
}
