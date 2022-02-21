package com.letscode.pablo.moviesbattle.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameMovieTest {

    private final int ID = 2312;
    private final String MOVIEA = "ASDF";
    private final String MOVIEB = "LKJHG";

    private GameMovie gameMovie;

    @BeforeEach
    void setUp() {
        gameMovie = new GameMovie(ID, null, MOVIEA, MOVIEB);
    }

    @Test
    void getId() {
        assertEquals(ID, gameMovie.getId());
    }

    @Test
    void getGame() {
        assertNull(gameMovie.getGame());
    }

    @Test
    void getMovieA() {
        assertEquals(MOVIEA, gameMovie.getMovieA());
    }

    @Test
    void getMovieB() {
        assertEquals(MOVIEB, gameMovie.getMovieB());
    }

    @Test
    void setId() {
        var change = 53453;
        gameMovie.setId(change);
        assertEquals(change, gameMovie.getId());
    }

    @Test
    void setGame() {
        var change = new Game();
        gameMovie.setGame(change);
        assertEquals(change, gameMovie.getGame());
    }

    @Test
    void setMovieA() {
        var change = "sdfdf";
        gameMovie.setMovieA(change);
        assertEquals(change, gameMovie.getMovieA());
    }

    @Test
    void setMovieB() {
        var change = "sdfdfsdasd";
        gameMovie.setMovieB(change);
        assertEquals(change, gameMovie.getMovieB());
    }

    @Test
    void testEquals() {
        var movieGame2 = new GameMovie(ID, null, MOVIEA, MOVIEB);
        assertEquals(gameMovie, movieGame2);
    }

    @Test
    void testNotEquals() {
        var movieGame2 = new GameMovie(987, null, MOVIEA, MOVIEB);
        assertNotEquals(gameMovie, movieGame2);
    }
}