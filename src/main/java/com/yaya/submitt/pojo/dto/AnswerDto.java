package com.yaya.submitt.pojo.dto;
import java.util.Date;

public class AnswerDto {
    private Integer answerId;
    private Integer questionId;
    private String userIp;
    private Integer answerUser;
    private Integer isCorrect;
    private Date answerTime;

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public Integer getAnswerUser() {
        return answerUser;
    }

    public void setAnswerUser(Integer answerUser) {
        this.answerUser = answerUser;
    }

    public Integer getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Integer correct) {
        isCorrect = correct;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    @Override
    public String toString() {
        return "AnswerDto{" +
                "answerId=" + answerId +
                ", questionId=" + questionId +
                ", userIp='" + userIp + '\'' +
                ", answerUser=" + answerUser +
                ", isCorrect=" + isCorrect +
                ", answerTime=" + answerTime +
                '}';
    }
}
