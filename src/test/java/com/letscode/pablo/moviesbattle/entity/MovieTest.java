package com.letscode.pablo.moviesbattle.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    private final String id = "12345";
    private final String title = "Matrix";
    private final double rating = 1.5;
    private final int votes = 8;
    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie(id, title, rating, votes);
    }

    @Test
    void getId() {
        assertEquals(id, movie.getId());
    }

    @Test
    void getTitle() {
        assertEquals(title, movie.getTitle());
    }

    @Test
    void getImdbRating() {
        assertEquals(rating, movie.getImdbRating());
    }

    @Test
    void getImdbVotes() {
        assertEquals(votes, movie.getImdbVotes());
    }

    @Test
    void setId() {
        var newId = "dfsdfs";
        movie.setId(newId);
        assertEquals(newId, movie.getId());
    }

    @Test
    void setTitle() {
        var newTitle = "dfsdfs dsfs dfssdfs";
        movie.setTitle(newTitle);
        assertEquals(newTitle, movie.getTitle());
    }

    @Test
    void setImdbRating() {
        var newRating = 8.8;
        movie.setImdbRating(newRating);
        assertEquals(newRating, movie.getImdbRating());
    }

    @Test
    void setImdbVotes() {
        var newVoote = 343224;
        movie.setImdbVotes(newVoote);
        assertEquals(newVoote, movie.getImdbVotes());
    }

    @Test
    void testEquals() {
        var movie2 = new Movie();
        movie2.setId(id);
        movie2.setTitle(title);
        movie2.setImdbRating(rating);
        movie2.setImdbVotes(votes);

        assertEquals(movie, movie2);
    }

    @Test
    void testNotEquals() {
        var movie2 = new Movie();
        movie2.setId("rwerw");
        movie2.setTitle(title);
        movie2.setImdbRating(rating);
        movie2.setImdbVotes(votes);

        assertNotEquals(movie, movie2);
    }
}