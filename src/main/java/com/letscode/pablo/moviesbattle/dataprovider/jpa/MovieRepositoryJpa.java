package com.letscode.pablo.moviesbattle.dataprovider.jpa;

import com.letscode.pablo.moviesbattle.entity.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepositoryJpa extends CrudRepository<Movie, String> {
}
