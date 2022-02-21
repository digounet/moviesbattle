package com.letscode.pablo.moviesbattle.dataprovider.impl;

import com.letscode.pablo.moviesbattle.dataprovider.jpa.MovieRepositoryJpa;
import com.letscode.pablo.moviesbattle.entity.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MoviesRepositoryImplTest {
    @Mock
    MovieRepositoryJpa movieRepositoryJpa;

    @InjectMocks
    MoviesRepositoryImpl moviesRepository;

    @Test
    void testSaveSuccess() {
        var movie = new Movie("asdasd", "Matrix", 1.8, 233);

        when(movieRepositoryJpa.save(Mockito.any())).thenReturn(movie);

        var savedMovie = moviesRepository.save(movie);

        assertNotNull(savedMovie);
        assertEquals(movie, savedMovie);
    }

    @Test
    void testFindById() {
        var movie = new Movie("asdasd", "Matrix", 1.8, 5677);

        when(movieRepositoryJpa.findById(Mockito.any())).thenReturn(Optional.of(movie));

        var userResponse = moviesRepository.findById(movie.getId());

        assertNotNull(userResponse);
        assertTrue(userResponse.isPresent());
        assertEquals(movie, userResponse.get());

    }

}