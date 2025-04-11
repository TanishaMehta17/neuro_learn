package com.example.neuro_learn.neuro_learn.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.neuro_learn.neuro_learn.Services.McqService;

@RestController
@RequestMapping("/api/mcq")
public class McqController {
    
     private final McqService mcqService;

  
    public McqController(McqService mcqService) {
        this.mcqService = mcqService;
    }

    @PostMapping
    public String getMcq(@RequestBody String topic) {
        return mcqService.generateMcqs(topic);
    }
}
