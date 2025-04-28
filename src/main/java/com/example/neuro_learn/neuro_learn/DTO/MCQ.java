package com.example.neuro_learn.neuro_learn.DTO;

import java.util.List;

public class MCQ {
    private String questionText;
    private List<String> options;
    private String answer;

    // Constructor
    public MCQ(String questionText, List<String> options, String answer) {
        this.questionText = questionText;
        this.options = options;
        this.answer = answer;
    }

    // Getters and Setters
    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
