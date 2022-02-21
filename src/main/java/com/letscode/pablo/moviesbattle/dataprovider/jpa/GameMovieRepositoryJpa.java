package com.letscode.pablo.moviesbattle.dataprovider.jpa;

import com.letscode.pablo.moviesbattle.entity.GameMovie;
import org.springframework.data.repository.CrudRepository;

public interface GameMovieRepositoryJpa extends CrudRepository<GameMovie, Integer> {
}
