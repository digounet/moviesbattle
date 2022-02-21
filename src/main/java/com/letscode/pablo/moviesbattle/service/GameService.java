package com.letscode.pablo.moviesbattle.service;

import com.letscode.pablo.moviesbattle.dataprovider.GameRepository;
import com.letscode.pablo.moviesbattle.dataprovider.UserRepository;
import com.letscode.pablo.moviesbattle.entity.Game;
import com.letscode.pablo.moviesbattle.infrastructure.exception.GameAlreadyStartedException;
import com.letscode.pablo.moviesbattle.infrastructure.exception.GameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    public Game startGame(int userId) throws GameAlreadyStartedException {
        var currentGame = gameRepository.getActiveGame(userId);

        if (currentGame.isEmpty()) {
            return gameRepository.startGame(userId);
        } else {
            throw new GameAlreadyStartedException("The user has an active game. Please finish the current game");
        }
    }

    public Game endGame(int userId) throws GameNotFoundException {
        var currentGame = gameRepository.getActiveGame(userId);

        if (currentGame.isPresent()) {
            return gameRepository.endGame(currentGame.get());
        } else {
            throw new GameNotFoundException("The user hasn't active games");
        }
    }
}
