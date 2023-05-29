package com.uet.quangnv.service.impl;

import com.uet.quangnv.dto.AnswerDto;
import com.uet.quangnv.dto.QuestionDto;
import com.uet.quangnv.dto.UserDto;
import com.uet.quangnv.entities.Answer;
import com.uet.quangnv.entities.Article;
import com.uet.quangnv.entities.Question;
import com.uet.quangnv.exception.domain.DataFormatWrong;
import com.uet.quangnv.exception.domain.ResoureNotFoundException;
import com.uet.quangnv.repository.AnswerRepository;
import com.uet.quangnv.repository.ArticleRepository;
import com.uet.quangnv.repository.QuestionRepository;
import com.uet.quangnv.service.QuestionService;
import com.uet.quangnv.ultis.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public QuestionDto saveQuestionDto(QuestionDto questionDto) throws DataFormatWrong, ResoureNotFoundException {
        UserDto currentUserLogin = Utils.getCurrentUserLogin();
        if (questionDto.getArticleId() != null) {
            if (questionDto.getAnswers() != null && questionDto.getAnswers().size() > 1) {
                if (questionDto.getId() != null) {
                    questionRepository.deleteById(questionDto.getId());
                }
                Question question = new Question();
                question.setContent(questionDto.getContent());
                question.setArticleId(questionDto.getArticleId());
                if (currentUserLogin.getRoleName().get(0).equals("ROLE_ADMIN")) {
                    question.setStatus(1);
                } else {
                    question.setStatus(0);
                }
                Optional<Article> optional = articleRepository.findById(question.getArticleId());
                if (!optional.isPresent()) {
                    throw new ResoureNotFoundException("Id bài viết không đúng, vui lòng chọn lại!");
                }
                question = questionRepository.save(question);
                List<Answer> answerList = new ArrayList<>();
                List<Long> answerIds = new ArrayList<>();
                for (AnswerDto answerDto : questionDto.getAnswers()) {
                    Answer answer = new Answer(answerDto.getContent(), answerDto.getAnswerTrue(), question.getId());
                    answerList.add(answer);
                    if (answerDto.getId() != null) {
                        answerIds.add(answerDto.getId());
                    }
                }
                if (answerIds.size() > 0) {
                    answerRepository.deleteAllByListIds(answerIds);
                }
                answerRepository.saveAll(answerList);
            } else {
                throw new DataFormatWrong("Câu hỏi có ít hơn 2 câu trả lời nên không được lưu!");
            }
        } else {
            throw new DataFormatWrong("Câu hỏi không thuộc bài viết nào nên không được lưu!");
        }
        return questionDto;
    }

    @Override
    public List<QuestionDto> saveListQuestionDto(List<QuestionDto> questionDtos) throws DataFormatWrong, ResoureNotFoundException {
        for (QuestionDto questionDto : questionDtos) {
            saveQuestionDto(questionDto);
        }
        return questionDtos;
    }

    @Override
    public List<QuestionDto> searchQuestionDto(Integer historicalPeriod, Integer status, Integer size) {
        UserDto currentUserLogin = Utils.getCurrentUserLogin();
        String username = null;
        Boolean isAdmin = true;
        if (!currentUserLogin.getRoleName().get(0).equals("ROLE_ADMIN")) {
            username = currentUserLogin.getUsername();
            isAdmin = false;
        }
        List<QuestionDto> questionDtos = questionRepository.searchQuestions(historicalPeriod, status, size, isAdmin, username);
        questionDtos.forEach(item -> {
            item.setAnswers(convertListAnswerToDto(answerRepository.findByQuestionId(item.getId())));
        });
        return questionDtos;
    }

    @Override
    public List<QuestionDto> getQuestionForExam(Integer historicalPeriod, Integer size) {
        List<QuestionDto> questionDtos = questionRepository.searchQuestions(historicalPeriod, 1, size, false, null);
        questionDtos.forEach(item -> {
            item.setAnswers(convertListAnswerToDto(answerRepository.findByQuestionId(item.getId())));
        });
        return questionDtos;
    }

    @Override
    public void censorshipListQuestion(List<Long> questionIds) {
        questionRepository.censorshipListQuestion(questionIds);
    }

    @Override
    public void deleteListQuestion(List<Long> questionIds) {
        questionRepository.deleteListQuestion(questionIds);
    }

    private List<AnswerDto> convertListAnswerToDto(List<Answer> answers) {
        List<AnswerDto> answerDtos = new ArrayList<>();
        answers.forEach(item -> {
            AnswerDto answerDto = new AnswerDto();
            answerDto.setId(item.getId());
            answerDto.setAnswerTrue(item.getAnswerTrue());
            answerDto.setQuestionId(item.getQuestionId());
            answerDto.setContent(item.getContent());
            answerDtos.add(answerDto);
        });
        return answerDtos;
    }
}
