package com.letscode.pablo.moviesbattle.dataprovider.impl;

import com.letscode.pablo.moviesbattle.dataprovider.jpa.GameRepositoryJpa;
import com.letscode.pablo.moviesbattle.entity.Game;
import com.letscode.pablo.moviesbattle.entity.GameMovie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
class GameRepositoryImplTest {

    @Mock
    GameRepositoryJpa gameRepositoryJpa;

    @InjectMocks
    GameRepositoryImpl gameRepository;

    @Captor
    ArgumentCaptor<Game> gameArgumentCaptor;

    @Test
    void testSaveSuccess() {
        var movieList = new ArrayList<Set<String>>();
        var pair = new HashSet<String>();
        pair.add("dfdsd");
        pair.add("dfded");
        movieList.add(pair);

        var game = new Game(1, 1, new Date(), null, 0, 0, new ArrayList<>());

        when(gameRepositoryJpa.save(Mockito.any())).thenReturn(game);

        var savedGame = gameRepository.startGame(1, movieList);

        assertNotNull(savedGame);
        assertEquals(game, savedGame);
    }

    @Test
    void testEndGame() {

        var game = new Game(1, 1, new Date(), null, 0, 0, new ArrayList<>());

        when(gameRepositoryJpa.save(Mockito.any())).thenReturn(game);

        var savedGame = gameRepository.endGame(game);

        assertNotNull(savedGame);
        assertEquals(game, savedGame);
        assertNotNull(savedGame.getEndDate());
    }

    @Test
    void testGetActiveGame() {
        var game = new Game(1, 1, new Date(), null, 0, 0, new ArrayList<>());

        when(gameRepositoryJpa.getByUserIdAndEndDateIsNull(eq(1))).thenReturn(Optional.of(game));

        var activeGame = gameRepository.getActiveGame(1);

        assertNotNull(activeGame);
        assertTrue(activeGame.isPresent());
        assertNotNull(activeGame.get());
    }

    @Test
    void testUpdate() {
        var game = new Game();

        when(gameRepositoryJpa.save(any())).thenReturn(game);

        gameRepository.update(game);

        verify(gameRepositoryJpa, times(1)).save(gameArgumentCaptor.capture());

    }
}