package com.example.neuro_learn.neuro_learn.Services;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface GeminiMcqAgent {

    @SystemMessage("You are an expert exam assistant that generates multiple-choice questions (MCQs).")
    String generateMcqs(String topic);
}