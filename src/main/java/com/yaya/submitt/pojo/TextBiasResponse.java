package com.yaya.submitt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TextBiasResponse {

    @JsonProperty("document_analysis")
    private PartialDocumentAnalysis documentAnalysis;

    @JsonProperty("biased_sentences")
    private List<PartialBiasedSentence> biasedSentences;

    public PartialDocumentAnalysis getDocumentAnalysis() {
        return documentAnalysis;
    }

    public void setDocumentAnalysis(PartialDocumentAnalysis documentAnalysis) {
        this.documentAnalysis = documentAnalysis;
    }

    public List<PartialBiasedSentence> getBiasedSentences() {
        return biasedSentences;
    }

    public void setBiasedSentences(List<PartialBiasedSentence> biasedSentences) {
        this.biasedSentences = biasedSentences;
    }

    public float computeBiasScore() {
        if (this.documentAnalysis != null) {
            String biasType = this.documentAnalysis.getBiasType();
            Double biasProbability = this.documentAnalysis.getBiasProbability();

            if (biasProbability == null) {
                return 0f;
            }

            if (biasType == null || biasType.trim().isEmpty()) {
                return (float) ((1 - biasProbability) * 100);
            } else {
                return (float) (biasProbability * 100);
            }
        }
        return 0f; // 如果 documentAnalysis 为 null，也返回 0
    }
}

// 文档级分析，只包含 bias_probability 和 bias_type
@JsonIgnoreProperties(ignoreUnknown = true)
class PartialDocumentAnalysis {

    @JsonProperty("bias_probability")
    private Double biasProbability;

    @JsonProperty("bias_type")
    private String biasType;

    public Double getBiasProbability() {
        return biasProbability;
    }

    public void setBiasProbability(Double biasProbability) {
        this.biasProbability = biasProbability;
    }

    public String getBiasType() {
        return biasType;
    }

    public void setBiasType(String biasType) {
        this.biasType = biasType;
    }
}

// 单个偏见句子，只保留 3 个字段
@JsonIgnoreProperties(ignoreUnknown = true)
class PartialBiasedSentence {

    @JsonProperty("bias_probability")
    private Double biasProbability;

    @JsonProperty("bias_type")
    private String biasType;

    @JsonProperty("text")
    private String text;

    public Double getBiasProbability() {
        return biasProbability;
    }

    public void setBiasProbability(Double biasProbability) {
        this.biasProbability = biasProbability;
    }

    public String getBiasType() {
        return biasType;
    }

    public void setBiasType(String biasType) {
        this.biasType = biasType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}