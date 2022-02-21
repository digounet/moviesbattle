package com.letscode.pablo.moviesbattle.infrastructure.constants;

public interface HttpResourcesPaths {
    String AUTH_RESOURCE = "/auth";
    String REGISTER_RESOURCE = "/register";
    String GAME_RESOURCE = "/game";

    String MOVIE_API_RESOURCE = "http://www.omdbapi.com/?i=%s&apikey=%s";
}
