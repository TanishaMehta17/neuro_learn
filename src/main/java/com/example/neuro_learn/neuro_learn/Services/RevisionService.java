package com.example.neuro_learn.neuro_learn.Services;

import org.springframework.stereotype.Service;

@Service
public class RevisionService {

    private final GeminiRevisionAgent revisionAgent;

    public RevisionService(GeminiRevisionAgent revisionAgent) {
        this.revisionAgent = revisionAgent;
    }

    public String generateRevision(String topic) {
        String prompt = "I am preparing for an exam and need to revise the topic: \"" + topic + "\". Please provide the following:\n"
                + "1. A list of the most important subtopics or concepts that must be studied under this topic.\n"
                + "2. A detailed yet concise revision summary for each of the listed subtopics to help me understand and recall them easily.\n"
                + "Make the summaries clear, structured, and suitable for quick revision.";

        return revisionAgent.generateRevision(prompt);
    }
}
