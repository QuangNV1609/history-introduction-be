package com.uet.quangnv.dto;

import javax.persistence.Column;
import java.util.List;

public class QuestionDto {
    private Long id;
    private String content;
    private Long articleId;
    private Integer status;

    private Long articleTitle;
    private List<AnswerDto> answerDtos;

    public QuestionDto() {
    }

    public QuestionDto(Long id, String content, Integer status, Long articleId, Long articleTitle) {
        this.id = id;
        this.content = content;
        this.articleId = articleId;
        this.status = status;
        this.articleTitle = articleTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public List<AnswerDto> getAnswerDtos() {
        return answerDtos;
    }

    public void setAnswerDtos(List<AnswerDto> answerDtos) {
        this.answerDtos = answerDtos;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(Long articleTitle) {
        this.articleTitle = articleTitle;
    }
}
