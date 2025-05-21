package com.yaya.submitt.pojo;

public class ImageResponse {
    private float confidence;
    private int predicted_class;
    private String predicted_label;

    // Getter 和 Setter

    public int getPredicted_class() {
        return predicted_class;
    }

    public void setPredicted_class(int predicted_class) {
        this.predicted_class = predicted_class;
    }

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }

    public String getpredicted_label() {
        return predicted_label;
    }

    public void setpredicted_label(String predicted_label) {
        this.predicted_label = predicted_label;
    }

    @Override
    public String toString() {
        return "ImageResponse{" +
                "predicted_class=" + predicted_class +
                ", confidence=" + confidence +
                ", pridected_label='" + predicted_label + '\'' +
                '}';
    }
}