package com.yaya.submitt.pojo;
import java.util.List;

public class AnswerRequest {

    private String userIp;
    private List<Integer> questionList;
    private List<Integer> questionAnswer;
    private List<Integer> questionCorrect;

    // Getters and Setters
    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public List<Integer> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Integer> questionList) {
        this.questionList = questionList;
    }

    public List<Integer> getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(List<Integer> questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public List<Integer> getQuestionCorrect() {
        return questionCorrect;
    }

    public void setQuestionCorrect(List<Integer> questionCorrect) {
        this.questionCorrect = questionCorrect;
    }
}