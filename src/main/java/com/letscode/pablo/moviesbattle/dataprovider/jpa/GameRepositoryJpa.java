package com.letscode.pablo.moviesbattle.dataprovider.jpa;

import com.letscode.pablo.moviesbattle.entity.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GameRepositoryJpa extends CrudRepository<Game, Integer> {
    Optional<Game> getByUserIdAndEndDateIsNull(int userId);
}
