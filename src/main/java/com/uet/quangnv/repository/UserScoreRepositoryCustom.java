package com.uet.quangnv.repository;

import com.uet.quangnv.dto.UserScoreDto;
import com.uet.quangnv.entities.UserScore;

import java.util.List;

public interface UserScoreRepositoryCustom {
    List<UserScoreDto> getHistoryUserScore(Integer historicalPeriod, Integer numOfQuestion, String username);
}
