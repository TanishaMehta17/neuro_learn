package com.example.neuro_learn.neuro_learn.DTO;

public class RevisionResponse {

    private String topic;
    private String explanation;

    public RevisionResponse(String topic, String explanation) {
        this.topic = topic;
        this.explanation = explanation;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
