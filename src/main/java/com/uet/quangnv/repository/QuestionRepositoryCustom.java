package com.uet.quangnv.repository;

import com.uet.quangnv.dto.QuestionDto;

import java.util.List;

public interface QuestionRepositoryCustom {
    List<QuestionDto> searchQuestions(Integer historicalPeriod, Integer status, Integer size);
}
