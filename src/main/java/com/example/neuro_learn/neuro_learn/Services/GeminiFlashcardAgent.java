package com.example.neuro_learn.neuro_learn.Services;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface GeminiFlashcardAgent {

    @SystemMessage("You are an expert learning assistant that creates Anki-style flashcards for a given topic.")
    String generateFlashcards(String prompt);
}