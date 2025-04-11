package com.example.neuro_learn.neuro_learn.Controllers;

import com.example.neuro_learn.neuro_learn.Services.RevisionService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/revision")
public class RevisionController {

    private final RevisionService revisionService;

  
    public RevisionController(RevisionService revisionService) {
        this.revisionService = revisionService;
    }

    @PostMapping
    public String getRevision(@RequestBody String topic) {
        return revisionService.generateRevision(topic);
    }
}

