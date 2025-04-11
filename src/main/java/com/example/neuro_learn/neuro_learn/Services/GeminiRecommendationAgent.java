package com.example.neuro_learn.neuro_learn.Services;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface GeminiRecommendationAgent {
  @SystemMessage("You are a learning assistant that recommends online courses and video tutorials based on a topic. Provide results in a clean and concise format.")
    String generateRecommendations(String topic);
}
