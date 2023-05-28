package com.uet.quangnv.dto;

import com.uet.quangnv.entities.Answer;

import java.util.List;

public class QuestionDto {
    private Long id;
    private String content;
    private Long articleId;
    private Integer status;

    private String articleTitle;
    private List<AnswerDto> answers;

    public QuestionDto() {
    }

    public QuestionDto(Long id, String content, Integer status, Long articleId, String articleTitle) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }
}
