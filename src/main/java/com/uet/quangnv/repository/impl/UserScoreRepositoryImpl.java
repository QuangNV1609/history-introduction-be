package com.uet.quangnv.repository.impl;

import com.uet.quangnv.dto.UserScoreDto;
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
    public List<UserScoreDto> getHistoryUserScore(Integer historicalPeriod, Integer numOfQuestion, String username) {
        StringBuilder sql = new StringBuilder("Select user_score.id,\n" +
                "    user_score.username,\n" +
                "    user_score.score,\n" +
                "    user_score.num_of_question,\n" +
                "    user_score.historical_period,\n" +
                "    user_score.time_exam_start,\n" +
                "    user_score.time_exam_end,\n" +
                "    user_score.time_exam\n" +
                "    from user_score " +
                "    Where user_score.username = :username\n");
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
        Query query = entityManager.createNativeQuery(sql.toString() + " Order by time_exam_start Desc", "UserScoreDto");
        Utils.setParamQuery(query, params);
        return query.getResultList();
    }
}
