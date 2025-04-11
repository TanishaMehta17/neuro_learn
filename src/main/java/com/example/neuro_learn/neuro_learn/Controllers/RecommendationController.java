package com.example.neuro_learn.neuro_learn.Controllers;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.neuro_learn.neuro_learn.DTO.TopicRequest;
import com.example.neuro_learn.neuro_learn.Services.RecommendationService;

@RestController
@RequestMapping("/api/recommend")
public class RecommendationController {
    private final RecommendationService recommendationService;
    
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }
    
   
@PostMapping
public String getRecommendations(@RequestBody TopicRequest request) {
    return recommendationService.fetchRecommendations(request.getTopic());
}
}
