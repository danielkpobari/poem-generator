package com.spring_ai.Spring_AI;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/poems")
public class PoemController {

    private final PoetryService poetryService;

    public PoemController(PoetryService poetryService) {
        this.poetryService = poetryService;
    }

    @PostMapping
    public ResponseEntity<Poem> generate(
             @Valid @RequestBody PoemGenerationRequest request) {

        return ResponseEntity.ok(
                poetryService.generate(
                        request.genre(),
                        request.theme()
                )
        );
    }
}
