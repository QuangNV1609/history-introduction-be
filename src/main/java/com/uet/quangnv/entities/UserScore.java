package com.uet.quangnv.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userScore")
@EntityListeners(AuditingEntityListener.class)
public class UserScore {
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

    @Column(name = "timeExamStart")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeExamStart;

    @Column(name = "timeExamEnd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeExamEnd;

    @Column(name = "timeExam")
    private Long timeExam;

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
