package com.letscode.pablo.moviesbattle.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;
    private final int ID = 1;
    private final int USERID = 1;
    private final Date STARTDATE = new Date();
    private final Date ENDDATE = new Date();
    private final int SCORE = 10;
    private final List<GameMovie> MOVIES = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MOVIES.add(new GameMovie());
        game = new Game(ID, USERID, STARTDATE, ENDDATE, SCORE, MOVIES);
    }

    @Test
    void getId() {
        assertEquals(ID, game.getId());
    }

    @Test
    void getUserId() {
        assertEquals(USERID, game.getUserId());
    }

    @Test
    void getStartDate() {
        assertEquals(STARTDATE, game.getStartDate());
    }

    @Test
    void getEndDate() {
        assertEquals(ENDDATE, game.getEndDate());
    }

    @Test
    void getScore() {
        assertEquals(SCORE, game.getScore());
    }

    @Test
    void getMovieList() {
        assertEquals(MOVIES.size(), game.getMovieList().size());
    }

    @Test
    void setId() {
        var change = 33231;
        game.setId(change);
        assertEquals(change, game.getId());
    }

    @Test
    void setUserId() {
        var change = 2342;
        game.setUserId(change);
        assertEquals(change, game.getUserId());
    }

    @Test
    void setStartDate() {
        var change = new Date();
        game.setStartDate(change);
        assertEquals(change, game.getStartDate());
    }

    @Test
    void setEndDate() {
        var change = new Date();
        game.setEndDate(change);
        assertEquals(change, game.getEndDate());
    }

    @Test
    void setScore() {
        var change = 56;
        game.setScore(change);
        assertEquals(change, game.getScore());
    }

    @Test
    void setMovieList() {
        var change = new ArrayList<GameMovie>();
        game.setMovieList(change);
        assertEquals(change.size(), game.getMovieList().size());
    }

    @Test
    void testEquals() {
        var game2 = new Game(ID, USERID, STARTDATE, ENDDATE, SCORE, MOVIES);

        assertEquals(game, game2);
    }

    @Test
    void testNotEquals() {
        var game2 = new Game(7867, USERID, STARTDATE, ENDDATE, SCORE, MOVIES);

        assertNotEquals(game, game2);
    }
}