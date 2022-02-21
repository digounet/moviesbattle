package com.letscode.pablo.moviesbattle.entrypoint.controller;

import com.letscode.pablo.moviesbattle.infrastructure.constants.HttpResourcesPaths;
import com.letscode.pablo.moviesbattle.service.MovieService;
import com.letscode.pablo.moviesbattle.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController extends BaseController {

    @Autowired
    TokenService tokenService;

    @Autowired
    MovieService movieService;

    @Value("#{'${movies.imdpids}'.split(',')}")
    List<String> moviesIds;

    @GetMapping(path = HttpResourcesPaths.GAME_RESOURCE)
    public ResponseEntity<String> hello() {
        var usernamed = getLoggedUsername();

        return ResponseEntity.ok("Olá " + usernamed + " " + movieService.getMovieById(moviesIds.get(0)));
    }
}
