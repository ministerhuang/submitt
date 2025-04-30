package com.yaya.submitt.pojo;

import jakarta.persistence.*;
        import java.util.Date;

@Table(name = "citizenship_testlog")
@Entity
public class CitizenshipTestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Integer testId;

    @Column(name = "user_ip", length = 50)
    private String userIp;

    @Column(name = "answer_1")
    private Byte answer1;

    @Column(name = "answer_2")
    private Byte answer2;

    @Column(name = "answer_3")
    private Byte answer3;

    @Column(name = "answer_4")
    private Byte answer4;

    @Column(name = "answer_5")
    private Byte answer5;

    @Column(name = "rating", length = 50)
    private String rating;

    @Column(name = "test_timestamp", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date testTimestamp;

    @Column(name = "total_score")
    private Integer totalScore;

    // Getters and setters
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

    public Byte getAnswer2() {
        return answer2;
    }

    public void setAnswer2(Byte answer2) {
        this.answer2 = answer2;
    }

    public Byte getAnswer3() {
        return answer3;
    }

    public void setAnswer3(Byte answer3) {
        this.answer3 = answer3;
    }

    public Byte getAnswer4() {
        return answer4;
    }

    public void setAnswer4(Byte answer4) {
        this.answer4 = answer4;
    }

    public Byte getAnswer5() {
        return answer5;
    }

    public void setAnswer5(Byte answer5) {
        this.answer5 = answer5;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Date getTestTimestamp() {
        return testTimestamp;
    }

    public void setTestTimestamp(Date testTimestamp) {
        this.testTimestamp = testTimestamp;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "CitizenshipTestLog{" +
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

