package com.letscode.pablo.moviesbattle.dataprovider;

import com.letscode.pablo.moviesbattle.entity.Movie;

import java.util.Optional;

public interface MovieRepository {
    Optional<Movie> findById(String id);

    Movie save(Movie movie);
}
