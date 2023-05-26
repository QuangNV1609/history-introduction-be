package com.uet.quangnv.service;

import com.uet.quangnv.entities.UserScore;

import java.util.List;

public interface UserScoreService {
    UserScore save(UserScore userScore);

    List<UserScore> getTopUserScore(Integer historicalPeriod, Integer numOfQuestion);
}
