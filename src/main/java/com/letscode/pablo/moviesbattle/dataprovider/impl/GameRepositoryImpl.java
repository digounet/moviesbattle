package com.letscode.pablo.moviesbattle.dataprovider.impl;

import com.letscode.pablo.moviesbattle.dataprovider.GameRepository;
import com.letscode.pablo.moviesbattle.dataprovider.jpa.GameRepositoryJpa;
import com.letscode.pablo.moviesbattle.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class GameRepositoryImpl implements GameRepository {

    @Autowired
    private GameRepositoryJpa gameRepository;

    @Override
    public Game startGame(int userId) {
        var game = new Game();
        game.setUserId(userId);
        game.setStartDate(new Date());
        game.setHits(0);
        game.setMistakes(0);
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
