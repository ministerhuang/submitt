package com.yaya.submitt.service;
import com.yaya.submitt.pojo.AnalysisLog;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.databind.ObjectMapper;

@Converter
public class JsonConverter implements AttributeConverter<AnalysisLog, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(AnalysisLog analysisLog) {
        try {
            if (analysisLog == null) {
                return null;
            }
            return objectMapper.writeValueAsString(analysisLog);
        } catch (Exception e) {
            throw new RuntimeException("Error converting AnalysisLog to JSON", e);
        }
    }

    @Override
    public AnalysisLog convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null) {
                return null;
            }
            return objectMapper.readValue(dbData, AnalysisLog.class);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON to AnalysisLog", e);
        }
    }
}