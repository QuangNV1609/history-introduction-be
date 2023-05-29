package com.uet.quangnv.service.impl;

import com.uet.quangnv.dto.UserDto;
import com.uet.quangnv.dto.UserScoreDto;
import com.uet.quangnv.entities.UserScore;
import com.uet.quangnv.repository.UserScoreRepository;
import com.uet.quangnv.service.UserScoreService;
import com.uet.quangnv.ultis.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserScoreServiceImpl implements UserScoreService {
    @Autowired
    private UserScoreRepository userScoreRepository;

    @Override
    public UserScore save(UserScore userScore) {
        UserDto userDto = Utils.getCurrentUserLogin();
        userScore.setUsername(userDto.getUsername());
        userScore.setTimeExam(userScore.getTimeExamEnd().getTime() - userScore.getTimeExamStart().getTime());
        userScore = userScoreRepository.save(userScore);
        return userScore;
    }

    @Override
    public List<UserScore> getTopUserScore(Integer historicalPeriod, Integer numOfQuestion) {
        List<UserScore> userScores = userScoreRepository.getTopUserScore(historicalPeriod, numOfQuestion);
        return userScores;
    }

    @Override
    public List<UserScoreDto> getHistoryUserScore(Integer historicalPeriod, Integer numOfQuestion) {
        UserDto userDto = Utils.getCurrentUserLogin();
        return userScoreRepository.getHistoryUserScore(historicalPeriod, numOfQuestion, userDto.getUsername());
    }
}
