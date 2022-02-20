package com.letscode.pablo.moviesbattle.entrypoint.controller;

import com.letscode.pablo.moviesbattle.infrastructure.constants.HttpResourcesPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @GetMapping(path = HttpResourcesPaths.GAME_RESOURCE)
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Olá");
    }

}
