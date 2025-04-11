package com.example.neuro_learn.neuro_learn.Services;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface GeminiRevisionAgent {

    @SystemMessage("You are an expert AI that helps students prepare for exams with concise and structured revision notes.")
    String generateRevision(String prompt);
}
