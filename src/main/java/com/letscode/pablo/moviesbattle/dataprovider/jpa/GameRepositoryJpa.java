package com.letscode.pablo.moviesbattle.dataprovider.jpa;

import com.letscode.pablo.moviesbattle.entity.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepositoryJpa extends CrudRepository<Game, Integer> {
}
