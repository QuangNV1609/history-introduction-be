package com.uet.quangnv.service;

import com.uet.quangnv.dto.QuestionDto;
import com.uet.quangnv.exception.domain.DataFormatWrong;

import java.util.List;

public interface QuestionService {
    QuestionDto saveQuestionDto(QuestionDto questionDto) throws DataFormatWrong;

    List<QuestionDto> saveListQuestionDto(List<QuestionDto> questionDtos) throws DataFormatWrong;

    List<QuestionDto> searchQuestionDto(Integer historicalPeriod, Integer status, Integer size);

    void censorshipListQuestion(List<Long> questionIds);

    void deleteListQuestion(List<Long> questionIds);
}
