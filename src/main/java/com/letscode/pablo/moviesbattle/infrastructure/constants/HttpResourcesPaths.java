package com.letscode.pablo.moviesbattle.infrastructure.constants;

public interface HttpResourcesPaths {
    String AUTH_RESOURCE = "/auth";
    String REGISTER_RESOURCE = "/register";
    String START_GAME_RESOURCE = "/startgame";
    String END_GAME_RESOURCE = "/endgame";

    String MOVIE_API_RESOURCE = "http://www.omdbapi.com/?i=%s&apikey=%s";

    String QUIZZ_RESOURCE = "/quizz";
    String QUIZZ_RESPONSE_RESOURCE = "/quizz/response";
    String GAME_RANKING_RESOURCE = "/ranking";
}
