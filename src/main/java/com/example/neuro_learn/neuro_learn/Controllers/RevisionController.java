package com.example.neuro_learn.neuro_learn.Controllers;

import com.example.neuro_learn.neuro_learn.DTO.RevisionRequest;
import com.example.neuro_learn.neuro_learn.DTO.RevisionResponse;
import com.example.neuro_learn.neuro_learn.Services.RevisionService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/revision")
public class RevisionController {

    private final RevisionService revisionService;

    public RevisionController(RevisionService revisionService) {
        this.revisionService = revisionService;
    }

    @PostMapping
    public List<RevisionResponse> getRevision(@RequestBody RevisionRequest request) {
        return revisionService.generateRevision(request.getTopic());
    }
}
