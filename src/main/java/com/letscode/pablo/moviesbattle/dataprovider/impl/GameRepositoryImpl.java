package com.letscode.pablo.moviesbattle.dataprovider.impl;

import com.letscode.pablo.moviesbattle.dataprovider.GameRepository;
import com.letscode.pablo.moviesbattle.dataprovider.jpa.GameRepositoryJpa;
import com.letscode.pablo.moviesbattle.entity.Game;
import com.letscode.pablo.moviesbattle.entity.GameMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GameRepositoryImpl implements GameRepository {

    @Autowired
    private GameRepositoryJpa gameRepository;

    @Override
    public Game startGame(int userId, List<Set<String>> movies) {
        var game = new Game();
        game.setUserId(userId);
        game.setStartDate(new Date());
        game.setHits(0);
        game.setMistakes(0);
        game.setMovieList(new ArrayList<>());

        for (var gameMovies : movies) {
            var moviePair = gameMovies.stream().toList();

            var gameMovie = new GameMovie();
            gameMovie.setGame(game);
            gameMovie.setUserScore(0);
            gameMovie.setMovieA(moviePair.get(0));
            gameMovie.setMovieB(moviePair.get(1));
            game.getMovieList().add(gameMovie);
        }

        return gameRepository.save(game);
    }

    @Override
    public Game endGame(Game game) {
        game.setEndDate(new Date());
        return gameRepository.save(game);
    }

    @Override
    public Optional<Game> getActiveGame(int userId) {
        return gameRepository.getByUserIdAndEndDateIsNull(userId);
    }
}
