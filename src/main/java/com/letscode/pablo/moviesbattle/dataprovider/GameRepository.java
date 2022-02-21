package com.letscode.pablo.moviesbattle.dataprovider;

import com.letscode.pablo.moviesbattle.entity.Game;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface GameRepository {
    Game startGame(int userId, List<Set<String>> movies);
    Game endGame(Game game);
    Optional<Game> getActiveGame(int userId);
}
