package com.letscode.pablo.moviesbattle.dataprovider.impl;

import com.letscode.pablo.moviesbattle.dataprovider.MovieRepository;
import com.letscode.pablo.moviesbattle.dataprovider.jpa.MovieRepositoryJpa;
import com.letscode.pablo.moviesbattle.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MoviesRepositoryImpl implements MovieRepository {

    @Autowired
    private MovieRepositoryJpa movieRepositoryJpa;

    @Override
    public Optional<Movie> findById(String id) {
        return movieRepositoryJpa.findById(id);
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepositoryJpa.save(movie);
    }
}
