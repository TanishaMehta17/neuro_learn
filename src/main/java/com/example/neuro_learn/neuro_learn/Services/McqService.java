package com.example.neuro_learn.neuro_learn.Services;

import org.springframework.stereotype.Service;

@Service
public class McqService {

    private final GeminiMcqAgent mcqAgent;

    public McqService(GeminiMcqAgent mcqAgent) {
        this.mcqAgent = mcqAgent;
    }

    public String generateMcqs(String topic) {
        return mcqAgent.generateMcqs(
            "Generate 15 multiple-choice questions (MCQs) for the topic: \"" + topic + "\".\n" +
            "Each question should include 4 options labeled A to D.\n" +
            "Clearly specify the correct answer for each question in the format:\n" +
            "Correct Answer: <Option Letter>\n" +
            "Make sure the questions cover key concepts and are useful for testing understanding."
        );
    }
}
