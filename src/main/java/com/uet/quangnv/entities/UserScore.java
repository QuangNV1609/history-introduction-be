package com.uet.quangnv.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "userScore")
@EntityListeners(AuditingEntityListener.class)
public class UserScore extends BaseEntity {
    @Id
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "score")
    private Double score;
    @Column(name = "numOfQuestion")
    private Integer numOfQuestion;
    @Column(name = "historicalPeriod")
    private Integer historicalPeriod;

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
}
