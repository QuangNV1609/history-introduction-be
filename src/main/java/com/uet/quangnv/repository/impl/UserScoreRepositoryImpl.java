package com.uet.quangnv.repository.impl;

import com.uet.quangnv.entities.UserScore;
import com.uet.quangnv.repository.UserScoreRepositoryCustom;
import com.uet.quangnv.ultis.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserScoreRepositoryImpl implements UserScoreRepositoryCustom {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserScore> getHistoryUserScore(Integer historicalPeriod, Integer numOfQuestion, String username) {
        StringBuilder sql = new StringBuilder("Select * from user_score " +
                "Where username = :username\n");
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        if (historicalPeriod != null) {
            sql.append("And historical_period = :historicalPeriod\n");
            params.put("historicalPeriod", historicalPeriod);
        }
        if (numOfQuestion != null) {
            sql.append("And historical_period = :numOfQuestion\n");
            params.put("numOfQuestion", numOfQuestion);
        }
        Query query = entityManager.createNativeQuery(sql.toString() + " Order by time_exam_start Desc", "UserDto");
        Utils.setParamQuery(query, params);
        return query.getResultList();
    }
}
