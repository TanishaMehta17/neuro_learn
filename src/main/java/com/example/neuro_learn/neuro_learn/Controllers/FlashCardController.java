package com.example.neuro_learn.neuro_learn.Controllers;

import com.example.neuro_learn.neuro_learn.DTO.FlashCard;
import com.example.neuro_learn.neuro_learn.Services.FlashCardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flashcards")
public class FlashCardController {

    private final FlashCardService flashCardService;

    public FlashCardController(FlashCardService flashCardService) {
        this.flashCardService = flashCardService;
    }

    @PostMapping
    public List<FlashCard> getFlashcards(@RequestBody String topic) {
        return flashCardService.generateFlashcards(topic);
    }
}
