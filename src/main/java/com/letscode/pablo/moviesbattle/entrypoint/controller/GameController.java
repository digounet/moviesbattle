package com.letscode.pablo.moviesbattle.entrypoint.controller;

import com.letscode.pablo.moviesbattle.entity.Game;
import com.letscode.pablo.moviesbattle.entrypoint.entity.BaseResponse;
import com.letscode.pablo.moviesbattle.infrastructure.constants.HttpResourcesPaths;
import com.letscode.pablo.moviesbattle.infrastructure.exception.*;
import com.letscode.pablo.moviesbattle.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
public class GameController extends BaseController {

    @Autowired
    GameService gameService;

    @GetMapping(path = HttpResourcesPaths.START_GAME_RESOURCE)
    public ResponseEntity<?> startGame() {

        var loggedUser = getLoggedUserId();

        try {
            var game = gameService.startGame(loggedUser);
            return ResponseEntity.ok(new BaseResponse<>("Game started! check the game details", game));
        } catch (GameAlreadyStartedException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseResponse<>(e.getMessage(), null));
        }
    }

    @GetMapping(path = HttpResourcesPaths.END_GAME_RESOURCE)
    public ResponseEntity<?> endGame() {

        var loggedUser = getLoggedUserId();

        try {
            var game = gameService.endGame(loggedUser);
            return ResponseEntity.ok(new BaseResponse<>("Game over! check the game details", game));
        } catch (GameNotFoundException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseResponse<>(e.getMessage(), null));
        }
    }

    @GetMapping(path = HttpResourcesPaths.QUIZZ_RESOURCE)
    public ResponseEntity<?> quizz() {

        var loggedUser = getLoggedUserId();

        try {
            var nextPair = gameService.quizz(loggedUser);
            return ResponseEntity.ok(new BaseResponse<>("Who's the winner?", nextPair));
        } catch (GameNotFoundException | MovieNotFoundException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseResponse<>(e.getMessage(), null));
        } catch (GameOverException e) {
            return ResponseEntity
                    .ok(new BaseResponse<>(e.getMessage(), null));
        }
    }

    @GetMapping(path = HttpResourcesPaths.QUIZZ_RESPONSE_RESOURCE)
    public ResponseEntity<?> quizzResponse(@RequestParam @NotNull String movieId) {

        var loggedUser = getLoggedUserId();

        try {
            var response = gameService.quizzResponse(loggedUser, movieId);
            return ResponseEntity.ok(new BaseResponse<>("Correct answer?", response));
        } catch (GameNotFoundException | MovieNotFoundException | WrongAnswerException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseResponse<>(e.getMessage(), null));
        } catch (GameOverException e) {
            return ResponseEntity
                    .ok(new BaseResponse<>(e.getMessage(), null));
        }
    }

    @GetMapping(path = HttpResourcesPaths.GAME_RANKING_RESOURCE)
    public ResponseEntity<?> ranking() {
        var ranking = gameService.loadRanking();
        return ResponseEntity.ok(new BaseResponse<>("Best players", ranking));
    }
}
