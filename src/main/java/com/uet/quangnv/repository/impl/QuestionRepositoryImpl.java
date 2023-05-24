package com.uet.quangnv.repository.impl;

import com.uet.quangnv.dto.QuestionDto;
import com.uet.quangnv.repository.QuestionRepositoryCustom;
import com.uet.quangnv.ultis.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionRepositoryImpl implements QuestionRepositoryCustom {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<QuestionDto> searchQuestions(Integer historicalPeriod, Integer status, Integer size) {
        StringBuilder sqlSearch = new StringBuilder("SELECT\n" +
                "    question.id,\n" +
                "    question.content,\n" +
                "    question.status,\n" +
                "    question.article_id,\n" +
                "    article.title\n" +
                "FROM\n" +
                "    questionn\n" +
                "JOIN article ON question.article_id = article.id\n" +
                "WHERE 1 = 1\n");
        Map<String, Object> params = new HashMap<>();
        if (historicalPeriod != null) {
            sqlSearch.append("AND article.historical_period = :historicalPeriod\n");
            params.put("historicalPeriod", historicalPeriod);
        }
        if (historicalPeriod != null) {
            sqlSearch.append("AND article.status = :status\n");
            params.put("status", status);
        }
        Query query = entityManager.createNativeQuery(sqlSearch.toString(), "QuestionDto");
        Utils.setParamQuery(query, params);
        return null;
    }
}
