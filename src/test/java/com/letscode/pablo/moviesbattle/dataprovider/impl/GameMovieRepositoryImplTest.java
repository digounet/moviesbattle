package com.letscode.pablo.moviesbattle.dataprovider.impl;

import com.letscode.pablo.moviesbattle.dataprovider.jpa.GameMovieRepositoryJpa;
import com.letscode.pablo.moviesbattle.entity.GameMovie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class GameMovieRepositoryImplTest {

    @Mock
    GameMovieRepositoryJpa movieRepositoryJpa;

    @InjectMocks
    GameMovieRepositoryImpl gameMovieRepository;

    @Captor
    ArgumentCaptor<GameMovie> gameMovieArgumentCaptor;

    @Test
    void testDelete() {
        var movie = new GameMovie();
        doNothing().when(movieRepositoryJpa).delete(Mockito.any());

        gameMovieRepository.delete(movie);

        verify(movieRepositoryJpa, times(1)).delete(gameMovieArgumentCaptor.capture());
    }
}