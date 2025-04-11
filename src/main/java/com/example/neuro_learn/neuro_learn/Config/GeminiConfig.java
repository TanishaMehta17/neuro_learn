package com.example.neuro_learn.neuro_learn.Config;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeminiConfig {

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    @Bean
    public GoogleAiGeminiChatModel geminiChatModel() {
        return GoogleAiGeminiChatModel.builder()
            .apiKey(geminiApiKey)
            .modelName("gemini-1.5-flash")
            .build();
    }
}
