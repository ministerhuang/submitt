package com.yaya.submitt.pojo.dto;

import jakarta.persistence.Column;

public class QuestionDto {
    private Integer questionId;
    private String questionTitle;
    private Integer questionAnswer;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public Integer getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(Integer questionAnswer) {
        this.questionAnswer = questionAnswer;
    }
}
