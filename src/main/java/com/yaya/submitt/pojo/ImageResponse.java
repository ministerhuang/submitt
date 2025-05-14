package com.yaya.submitt.pojo;

public class ImageResponse {
    private int predicted_class;
    private float confidence;
    private String pridected_label;

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

    public String getPridected_label() {
        return pridected_label;
    }

    public void setPridected_label(String pridected_label) {
        this.pridected_label = pridected_label;
    }

    @Override
    public String toString() {
        return "ImageResponse{" +
                "predicted_class=" + predicted_class +
                ", confidence=" + confidence +
                ", pridected_label='" + pridected_label + '\'' +
                '}';
    }
}