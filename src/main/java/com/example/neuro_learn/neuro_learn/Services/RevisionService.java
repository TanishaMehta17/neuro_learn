package com.example.neuro_learn.neuro_learn.Services;

import com.example.neuro_learn.neuro_learn.DTO.RevisionResponse;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class RevisionService {

    private final GeminiRevisionAgent revisionAgent;
    private static final Logger logger = LoggerFactory.getLogger(RevisionService.class);

    public RevisionService(GeminiRevisionAgent revisionAgent) {
        this.revisionAgent = revisionAgent;
    }

    public List<RevisionResponse> generateRevision(String topic) {
        String prompt = "Give me a structured list of the most important subtopics with their explanations for the topic: \"" + topic + "\". " +
                "Return them in the format:\n" +
                "* Subtopic Name - Explanation";

        String rawRevision = revisionAgent.generateRevision(prompt);
        logger.info("Raw Revision Response: " + rawRevision);

        return parseRevision(rawRevision);
    }

    private List<RevisionResponse> parseRevision(String rawRevision) {
        List<RevisionResponse> responses = new ArrayList<>();
        if (rawRevision == null || rawRevision.trim().isEmpty()) return responses;

        String[] lines = rawRevision.split("\n");

        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("*")) {
                String[] parts = line.substring(1).split(" - ", 2);
                if (parts.length == 2) {
                    String topic = parts[0].trim();
                    String explanation = parts[1].trim();
                    responses.add(new RevisionResponse(topic, explanation));
                }
            }
        }

        return responses;
    }
}
