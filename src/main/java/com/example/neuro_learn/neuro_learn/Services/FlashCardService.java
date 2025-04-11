package com.example.neuro_learn.neuro_learn.Services;

import org.springframework.stereotype.Service;

@Service
public class FlashCardService {

    private final GeminiFlashcardAgent flashcardAgent;

    public FlashCardService(GeminiFlashcardAgent flashcardAgent) {
        this.flashcardAgent = flashcardAgent;
    }

    public String generateFlashcards(String topic) {
        String prompt = "Generate a set of Anki-style flashcards for the topic: \"" + topic + "\".\n" +
                        "Each flashcard should be in the format:\n" +
                        "Q: <question>\n" +
                        "A: <answer>\n" +
                        "Include at least 10 flashcards that cover the key concepts, facts, or definitions from this topic.";

        return flashcardAgent.generateFlashcards(prompt);
    }
}
