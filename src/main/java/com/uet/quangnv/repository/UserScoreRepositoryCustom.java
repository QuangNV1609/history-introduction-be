package com.uet.quangnv.repository;

import com.uet.quangnv.entities.UserScore;

import java.util.List;

public interface UserScoreRepositoryCustom {
    List<UserScore> getHistoryUserScore(Integer historicalPeriod, Integer numOfQuestion, String username);
}
