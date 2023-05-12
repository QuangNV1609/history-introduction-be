package com.uet.quangnv.repository.impl;

import com.uet.quangnv.dto.ArticleDto;
import com.uet.quangnv.repository.ArticleRepositoryCustom;
import com.uet.quangnv.ultis.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleRepositoryImpl implements ArticleRepositoryCustom {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ArticleDto> getAllByUserLogin(String username, Boolean isAdmin) {
        StringBuilder sql = new StringBuilder("SELECT \n" +
                "  article.id, \n" +
                "  article.title, \n" +
                "  null as content, \n" +
                "  article.history_day, \n" +
                "  article.status, \n" +
                "  article.post_type, \n" +
                "  article.historical_period, \n" +
                "  article.thumbnail_image, \n" +
                "  article.cover_image,\n" +
                "  user.username,\n" +
                "  user_info.last_name + user_info.first_name as author\n" +
                "FROM \n" +
                "  article \n" +
                "  LEFT JOIN user ON article.create_by = user.username \n" +
                "  LEFT JOIN user_info ON user.username = user_info.user_id \n");
        Map<String, Object> params = new HashMap<>();
        if (!isAdmin) {
            sql.append("WHERE user.username = :username \n");
            params.put("username", username);
        }
        sql.append("ORDER BY article.create_by DESC");
        Query query = entityManager.createNativeQuery(sql.toString(), "ArticleDto");
        Utils.setParamQuery(query, params);
        return query.getResultList();
    }

    @Override
    public ArticleDto getByArticleID(Long articleID, String username, Boolean isAdmin) {
        StringBuilder sql = new StringBuilder("SELECT \n" +
                "  article.id, \n" +
                "  article.title, \n" +
                "  article.content, \n" +
                "  article.history_day, \n" +
                "  article.status, \n" +
                "  article.post_type, \n" +
                "  article.historical_period, \n" +
                "  article.thumbnail_image, \n" +
                "  article.cover_image,\n" +
                "  user.username,\n" +
                "  user_info.last_name + user_info.first_name as author\n" +
                "FROM \n" +
                "  article \n" +
                "  LEFT JOIN user ON article.create_by = user.username \n" +
                "  LEFT JOIN user_info ON user.username = user_info.user_id \n" +
                "WHERE \n" +
                "  article.id = :articleId \n");
        Map<String, Object> params = new HashMap<>();
        params.put("articleId", articleID);
        if (!isAdmin) {
            sql.append("AND (article.status = 1 or user.username = :username)");
            params.put("username", username);
        }
        Query query = entityManager.createNativeQuery(sql.toString(), "ArticleDto");
        Utils.setParamQuery(query, params);
        return (ArticleDto) query.getSingleResult();
    }

    @Override
    public List<ArticleDto> getByArticleIsCensorship(Boolean isCensorship, String username) {
        StringBuilder sql = new StringBuilder("SELECT \n" +
                "  article.id, \n" +
                "  article.title, \n" +
                "  article.content, \n" +
                "  article.history_day, \n" +
                "  article.status, \n" +
                "  article.post_type, \n" +
                "  article.historical_period, \n" +
                "  article.thumbnail_image, \n" +
                "  article.cover_image,\n" +
                "  user.username,\n" +
                "  user_info.last_name + user_info.first_name as author\n" +
                "FROM \n" +
                "  article \n" +
                "  LEFT JOIN user ON article.create_by = user.username \n" +
                "  LEFT JOIN user_info ON user.username = user_info.user_id \n" +
                "WHERE \n");
        if (isCensorship) {
            sql.append("article.status = 1");
        } else {
            sql.append("article.status = 0");
        }
        Query query = entityManager.createNativeQuery(sql.toString(), "ArticleDto");
        return query.getResultList();
    }

    @Override
    public List<ArticleDto> searchArticle(Boolean isAdmin, Integer historicalPeriod, String historyDay) {
        StringBuilder sql = new StringBuilder("SELECT \n" +
                "  article.id, \n" +
                "  article.title, \n" +
                "  article.content, \n" +
                "  article.history_day, \n" +
                "  article.status, \n" +
                "  article.post_type, \n" +
                "  article.historical_period, \n" +
                "  article.thumbnail_image, \n" +
                "  article.cover_image,\n" +
                "  user.username,\n" +
                "  user_info.last_name + user_info.first_name as author\n" +
                "FROM \n" +
                "  article \n" +
                "  LEFT JOIN user ON article.create_by = user.username \n" +
                "  LEFT JOIN user_info ON user.username = user_info.user_id \n" +
                "WHERE 1 =1\n");
        Map<String, Object> params = new HashMap<>();
        if (!isAdmin) {
            sql.append("and article.status = 1\n");
        }
        if (historicalPeriod != null) {
            sql.append("and article.historical_period = :historicalPeriod \n");
            params.put("historicalPeriod", historicalPeriod);
        }
        if (historyDay != null) {
            sql.append("and DAY(article.history_day) = DAY( :historyDay ) and MONTH( :historyDay ) \n");
            params.put("historyDay", historyDay);
        }
        Query query = entityManager.createNativeQuery(sql.toString(), "ArticleDto");
        Utils.setParamQuery(query, params);
        return query.getResultList();
    }

    @Override
    public void deleteArticleByIDs(List<Long> ids, String username) {
        StringBuilder sql = new StringBuilder("Delete from article where article.id in :ids");
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        if (username != null) {
            sql.append(" and article.create_by = :createBy and article.status = 1");
            params.put("createBy", username);
        }
        Query query = entityManager.createNativeQuery(sql.toString(), "ArticleDto");
        Utils.setParamQuery(query, params);
        query.executeUpdate();
    }
}
