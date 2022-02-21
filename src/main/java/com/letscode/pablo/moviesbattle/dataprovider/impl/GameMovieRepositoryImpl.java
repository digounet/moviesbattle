package com.letscode.pablo.moviesbattle.dataprovider.impl;

import com.letscode.pablo.moviesbattle.dataprovider.GameMovieRepository;
import com.letscode.pablo.moviesbattle.dataprovider.jpa.GameMovieRepositoryJpa;
import com.letscode.pablo.moviesbattle.entity.GameMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameMovieRepositoryImpl implements GameMovieRepository {

    @Autowired
    private GameMovieRepositoryJpa movieRepositoryJpa;

    @Override
    public void delete(GameMovie gameMovie) {
        movieRepositoryJpa.delete(gameMovie);
    }
}
