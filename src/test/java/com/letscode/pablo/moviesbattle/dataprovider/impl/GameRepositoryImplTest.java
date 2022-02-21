package com.letscode.pablo.moviesbattle.dataprovider.impl;

import com.letscode.pablo.moviesbattle.dataprovider.jpa.GameRepositoryJpa;
import com.letscode.pablo.moviesbattle.entity.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class GameRepositoryImplTest {

    @Mock
    GameRepositoryJpa gameRepositoryJpa;

    @InjectMocks
    GameRepositoryImpl gameRepository;

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
}