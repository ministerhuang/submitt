package com.yaya.submitt.pojo;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Integer answerId;

    @Column(name = "question_id", nullable = false)  // 外键，不能为空
    private Integer questionId;

    @Column(name = "user_ip", nullable = true)  // 可以为空
    private String userIp;

    @Column(name = "answer_user", nullable = true)  // 可以为空
    private Integer answerUser;

    @Column(name = "is_correct", nullable = true)  // 可以为空
    private Integer isCorrect;

    @Column(name = "answer_time", nullable = true)  // 可以为空
    @Temporal(TemporalType.TIMESTAMP)
    private Date answerTime;

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
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
        return "Answer{" +
                "answerId=" + answerId +
                ", questionId=" + questionId +
                ", userIp='" + userIp + '\'' +
                ", answerUser=" + answerUser +
                ", isCorrect=" + isCorrect +
                ", answerTime=" + answerTime +
                '}';
    }
}