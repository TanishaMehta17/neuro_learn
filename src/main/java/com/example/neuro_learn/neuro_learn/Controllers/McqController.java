package com.example.neuro_learn.neuro_learn.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.neuro_learn.neuro_learn.DTO.MCQ;
import com.example.neuro_learn.neuro_learn.Services.McqService;

@RestController
@RequestMapping("/api/mcq")
public class McqController {
    
     private final McqService mcqService;

  
    public McqController(McqService mcqService) {
        this.mcqService = mcqService;
    }

    @PostMapping
public ResponseEntity<List<MCQ>> generateMcqs(@RequestBody Map<String, String> request) {
    String topic = request.get("topic");
    List<MCQ> mcqs = mcqService.generateMcqs(topic);
    return ResponseEntity.ok(mcqs);
}

}
