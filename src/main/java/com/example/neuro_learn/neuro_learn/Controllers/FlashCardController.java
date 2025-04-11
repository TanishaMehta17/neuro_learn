package com.example.neuro_learn.neuro_learn.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.neuro_learn.neuro_learn.Services.FlashCardService;

@RestController
@RequestMapping("/api/flashcards")
public class FlashCardController {
     private final FlashCardService flashCardService;

  
    public  FlashCardController(FlashCardService flashCardService) {
        this.flashCardService = flashCardService;
    }

    @PostMapping
    public String getMcq(@RequestBody String topic) {
        return flashCardService.generateFlashcards(topic);
    }
}
