package com.uet.quangnv.service.impl;

import com.uet.quangnv.dto.AnswerDto;
import com.uet.quangnv.dto.QuestionDto;
import com.uet.quangnv.dto.UserDto;
import com.uet.quangnv.entities.Answer;
import com.uet.quangnv.entities.Question;
import com.uet.quangnv.exception.domain.DataFormatWrong;
import com.uet.quangnv.repository.AnswerRepository;
import com.uet.quangnv.repository.QuestionRepository;
import com.uet.quangnv.service.QuestionService;
import com.uet.quangnv.ultis.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public QuestionDto saveQuestionDto(QuestionDto questionDto) throws DataFormatWrong {
        UserDto currentUserLogin = Utils.getCurrentUserLogin();
        if (questionDto.getArticleId() != null) {
            if (questionDto.getAnswerDtos() != null && questionDto.getAnswerDtos().size() > 1) {
                if (questionDto.getId() != null) {
                    questionRepository.deleteById(questionDto.getId());
                } else {
                    Question question = new Question();
                    question.setContent(questionDto.getContent());
                    question.setArticleId(questionDto.getArticleId());
                    if (currentUserLogin.getRoleName().equals("ROLE_ADMIN")) {
                        question.setStatus(1);
                    }
                    question = questionRepository.save(question);
                    List<Answer> answerList = new ArrayList<>();
                    List<Long> answerIds = new ArrayList<>();
                    for (AnswerDto answerDto : questionDto.getAnswerDtos()) {
                        Answer answer = new Answer(answerDto.getContent(), answerDto.getAnswerTrue(), question.getId());
                        answerList.add(answer);
                        if (answerDto.getId() != null) {
                            answerIds.add(answerDto.getId());
                        }
                    }
                    if (answerIds.size() > 0) {
                        answerRepository.deleteAllByListIds(answerIds);
                    }
                }
            } else {
                throw new DataFormatWrong("Câu hỏi có ít hơn 2 câu trả lời nên không được lưu!");
            }
        } else {
            throw new DataFormatWrong("Câu hỏi không thuộc bài viết nào nên không được lưu!");
        }
        return questionDto;
    }

    @Override
    public List<QuestionDto> saveListQuestionDto(List<QuestionDto> questionDtos) throws DataFormatWrong {
        for (QuestionDto questionDto : questionDtos) {
            saveQuestionDto(questionDto);
        }
        return questionDtos;
    }

    @Override
    public List<QuestionDto> searchQuestionDto(Integer historicalPeriod, Integer status, Integer size) {
        return questionRepository.searchQuestions(historicalPeriod, status, size);
    }

    @Override
    public void censorshipListQuestion(List<Long> questionIds) {
        questionRepository.censorshipListQuestion(questionIds);
    }
}
