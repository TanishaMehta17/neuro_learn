package com.example.neuro_learn.neuro_learn.Services;

import org.springframework.stereotype.Service;

import com.example.neuro_learn.neuro_learn.DTO.FlashCard;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlashCardService {

    private final GeminiFlashcardAgent flashcardAgent;

    public FlashCardService(GeminiFlashcardAgent flashcardAgent) {
        this.flashcardAgent = flashcardAgent;
    }

    public List<FlashCard> generateFlashcards(String topic) {
        String prompt = "Generate a set of Anki-style flashcards for the topic: \"" + topic + "\".\n" +
                        "Each flashcard should be in the format:\n" +
                        "Q: <question>\n" +
                        "A: <answer>\n" +
                        "Include at least 10 flashcards that cover the key concepts, facts, or definitions from this topic.";

        String response = flashcardAgent.generateFlashcards(prompt);

        // Parse the response into list of FlashCard objects
        List<FlashCard> flashcards = new ArrayList<>();
        String[] lines = response.split("\n");

        String question = null;
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("Q:")) {
                question = line.substring(2).trim();
            } else if (line.startsWith("A:") && question != null) {
                String answer = line.substring(2).trim();
                flashcards.add(new FlashCard(question, answer));
                question = null;
            }
        }

        return flashcards;
    }
}
