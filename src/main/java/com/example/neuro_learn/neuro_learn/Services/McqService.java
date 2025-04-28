package com.example.neuro_learn.neuro_learn.Services;

import org.springframework.stereotype.Service;

import com.example.neuro_learn.neuro_learn.DTO.MCQ;

import java.util.*;
import java.util.regex.*;

@Service
public class McqService {

    private final GeminiMcqAgent mcqAgent;

    public McqService(GeminiMcqAgent mcqAgent) {
        this.mcqAgent = mcqAgent;
    }

    public List<MCQ> generateMcqs(String topic) {
        String rawOutput = mcqAgent.generateMcqs(
            "Generate 15 multiple-choice questions (MCQs) for the topic: \"" + topic + "\".\n" +
            "Each question should include 4 options labeled A to D.\n" +
            "Clearly specify the correct answer for each question in the format:\n" +
            "Correct Answer: <Option Letter>\n" +
            "Make sure the questions cover key concepts and are useful for testing understanding."
        );

        List<MCQ> mcqs = new ArrayList<>();

        // Split by question block
        String[] blocks = rawOutput.split("(?=\\*\\*\\d+\\.\\s)");

        for (String block : blocks) {
            try {
                String questionText = "";
                List<String> options = new ArrayList<>();
                String answer = "";

                // Extract question
                Matcher qMatcher = Pattern.compile("\\*\\*\\d+\\.\\s(.*?)\\*\\*", Pattern.DOTALL).matcher(block);
                if (qMatcher.find()) {
                    questionText = qMatcher.group(1).trim();
                }

                // Extract options A-D
                Matcher oMatcher = Pattern.compile("([A-D])\\.\\s(.+)").matcher(block);
                while (oMatcher.find()) {
                    options.add(oMatcher.group(1) + ". " + oMatcher.group(2));
                }

                // Extract answer
                Matcher aMatcher = Pattern.compile("Correct Answer:\\s*([A-D])").matcher(block);
                if (aMatcher.find()) {
                    answer = aMatcher.group(1);
                }

                if (!questionText.isEmpty() && options.size() == 4 && !answer.isEmpty()) {
                    mcqs.add(new MCQ(questionText, options, answer));
                }

            } catch (Exception ignored) {}
        }

        return mcqs;
    }
}
