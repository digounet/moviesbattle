package com.letscode.pablo.moviesbattle.entrypoint.controller;

import com.letscode.pablo.moviesbattle.infrastructure.constants.HttpResourcesPaths;
import com.letscode.pablo.moviesbattle.infrastructure.exception.GameAlreadyStartedException;
import com.letscode.pablo.moviesbattle.infrastructure.exception.GameNotFoundException;
import com.letscode.pablo.moviesbattle.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController extends BaseController {

    @Autowired
    GameService gameService;

    @GetMapping(path = HttpResourcesPaths.START_GAME_RESOURCE)
    public ResponseEntity<?> startGame() {

        var loggedUser = getLoggedUserId();

        try {
            var game = gameService.startGame(loggedUser);
            return ResponseEntity.ok(game);
        } catch (GameAlreadyStartedException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping(path = HttpResourcesPaths.END_GAME_RESOURCE)
    public ResponseEntity<?> endGame() {

        var loggedUser = getLoggedUserId();

        try {
            var game = gameService.endGame(loggedUser);
            return ResponseEntity.ok(game);
        } catch (GameNotFoundException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
