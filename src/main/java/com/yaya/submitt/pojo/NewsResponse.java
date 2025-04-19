package com.yaya.submitt.pojo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewsResponse {
    @JsonProperty("text")
    private String text;

    @JsonProperty("score")
    private float score;

    @JsonProperty("label")
    private String label;

    // Getters 和 Setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}