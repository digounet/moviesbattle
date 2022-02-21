package com.letscode.pablo.moviesbattle.entrypoint.controller;

import com.letscode.pablo.moviesbattle.infrastructure.constants.HttpResourcesPaths;
import com.letscode.pablo.moviesbattle.infrastructure.exception.MovieNotFoundException;
import com.letscode.pablo.moviesbattle.service.MovieService;
import com.letscode.pablo.moviesbattle.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController extends BaseController {

    @Autowired
    TokenService tokenService;

    @Autowired
    MovieService movieService;

    @GetMapping(path = HttpResourcesPaths.GAME_RESOURCE)
    public ResponseEntity<String> game() {
        var usernamed = getLoggedUsername();

        var movies = movieService.pickNRandomElements();
        return ResponseEntity.ok("Olá " + usernamed + " " + movies.get(0) + " " + movies.get(1));
    }
}
