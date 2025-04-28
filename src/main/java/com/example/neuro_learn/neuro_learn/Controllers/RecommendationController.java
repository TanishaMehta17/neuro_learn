package com.example.neuro_learn.neuro_learn.Controllers;

import com.example.neuro_learn.neuro_learn.DTO.TopicRequest;
import com.example.neuro_learn.neuro_learn.Services.RecommendationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recommend")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    // Endpoint for AI-powered text recommendations
    @PostMapping("/text")
    public String getTextRecommendations(@RequestBody TopicRequest request) {
        return recommendationService.fetchTextRecommendations(request.getTopic());
    }

    // Endpoint for YouTube video recommendations
    @PostMapping("/youtube")
    public String getYoutubeRecommendations(@RequestBody TopicRequest request) {
        return recommendationService.fetchYoutubeRecommendations(request.getTopic());
    }

    // Endpoint for course recommendations
    @PostMapping("/courses")
    public String getCourseRecommendations(@RequestBody TopicRequest request) {
        return recommendationService.fetchCourseRecommendations(request.getTopic());
    }
}
