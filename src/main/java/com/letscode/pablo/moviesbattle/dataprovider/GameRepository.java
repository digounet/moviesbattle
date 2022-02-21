package com.letscode.pablo.moviesbattle.dataprovider;

import com.letscode.pablo.moviesbattle.entity.Game;

import java.util.Optional;

public interface GameRepository {
    Game startGame(int userId);
    Game endGame(Game game);
    Optional<Game> getActiveGame(int userId);
}
