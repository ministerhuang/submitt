package com.yaya.submitt.pojo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

public class CitizenshipTestLogDto {
    private Integer testId;

    private String userIp;

    private Byte answer1;

    private Byte answer2;

    private Byte answer3;

    private Byte answer4;

    private Byte answer5;

    private String rating;

    private Date testTimestamp;

    private Integer totalScore;

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public Byte getAnswer1() {
        return answer1;
    }

    public void setAnswer1(Byte answer1) {
        this.answer1 = answer1;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Date getTestTimestamp() {
        return testTimestamp;
    }

    public void setTestTimestamp(Date testTimestamp) {
        this.testTimestamp = testTimestamp;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Byte getAnswer5() {
        return answer5;
    }

    public void setAnswer5(Byte answer5) {
        this.answer5 = answer5;
    }

    public Byte getAnswer4() {
        return answer4;
    }

    public void setAnswer4(Byte answer4) {
        this.answer4 = answer4;
    }

    public Byte getAnswer3() {
        return answer3;
    }

    public void setAnswer3(Byte answer3) {
        this.answer3 = answer3;
    }

    public Byte getAnswer2() {
        return answer2;
    }

    public void setAnswer2(Byte answer2) {
        this.answer2 = answer2;
    }

    @Override
    public String toString() {
        return "CitizenshipTestLogDto{" +
                "testId=" + testId +
                ", userIp='" + userIp + '\'' +
                ", answer1=" + answer1 +
                ", answer2=" + answer2 +
                ", answer3=" + answer3 +
                ", answer4=" + answer4 +
                ", answer5=" + answer5 +
                ", rating='" + rating + '\'' +
                ", testTimestamp=" + testTimestamp +
                ", totalScore=" + totalScore +
                '}';
    }
}
