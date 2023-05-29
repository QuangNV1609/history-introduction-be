package com.uet.quangnv.dto;

import java.util.Date;

public class UserScoreDto {
    private Long id;
    private String username;
    private Double score;
    private Integer numOfQuestion;
    private Integer historicalPeriod;
    private Date timeExamStart;
    private Date timeExamEnd;
    private Long timeExam;

    public UserScoreDto() {
    }

    public UserScoreDto(Long id, String username, Double score, Integer numOfQuestion, Integer historicalPeriod, Date timeExamStart, Date timeExamEnd, Long timeExam) {
        this.id = id;
        this.username = username;
        this.score = score;
        this.numOfQuestion = numOfQuestion;
        this.historicalPeriod = historicalPeriod;
        this.timeExamStart = timeExamStart;
        this.timeExamEnd = timeExamEnd;
        this.timeExam = timeExam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getNumOfQuestion() {
        return numOfQuestion;
    }

    public void setNumOfQuestion(Integer numOfQuestion) {
        this.numOfQuestion = numOfQuestion;
    }

    public Integer getHistoricalPeriod() {
        return historicalPeriod;
    }

    public void setHistoricalPeriod(Integer historicalPeriod) {
        this.historicalPeriod = historicalPeriod;
    }

    public Date getTimeExamStart() {
        return timeExamStart;
    }

    public void setTimeExamStart(Date timeExamStart) {
        this.timeExamStart = timeExamStart;
    }

    public Date getTimeExamEnd() {
        return timeExamEnd;
    }

    public void setTimeExamEnd(Date timeExamEnd) {
        this.timeExamEnd = timeExamEnd;
    }

    public Long getTimeExam() {
        return timeExam;
    }

    public void setTimeExam(Long timeExam) {
        this.timeExam = timeExam;
    }
}
