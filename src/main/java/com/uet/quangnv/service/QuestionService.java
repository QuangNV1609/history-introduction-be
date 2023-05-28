package com.uet.quangnv.service;

import com.uet.quangnv.dto.QuestionDto;
import com.uet.quangnv.exception.domain.DataFormatWrong;
import com.uet.quangnv.exception.domain.ResoureNotFoundException;

import java.util.List;

public interface QuestionService {
    QuestionDto saveQuestionDto(QuestionDto questionDto) throws DataFormatWrong, ResoureNotFoundException;

    List<QuestionDto> saveListQuestionDto(List<QuestionDto> questionDtos) throws DataFormatWrong, ResoureNotFoundException;

    List<QuestionDto> searchQuestionDto(Integer historicalPeriod, Integer status, Integer size);

    List<QuestionDto> getQuestionForExam(Integer historicalPeriod, Integer size);

    void censorshipListQuestion(List<Long> questionIds);

    void deleteListQuestion(List<Long> questionIds);
}
