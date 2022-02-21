package com.letscode.pablo.moviesbattle.entrypoint.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieResponseTest {

    @Test
    void testEquals() {
        var movies1 = MovieResponse.builder()
                .imdbRating(10.5F)
                .imdbVotes(12345)
                .title("sadasdasd")
                .build();

        var movies2 = MovieResponse.builder()
                .imdbRating(10.5F)
                .imdbVotes(12345)
                .title("sadasdasd")
                .build();

        assertEquals(movies1, movies2);
    }

    @Test
    void testEmptyEquals() {
        var movies1 = new MovieResponse();

        var movies2 = new MovieResponse();

        assertEquals(movies1, movies2);
    }

    @Test
    void testNotEquals() {
        var movies1 = MovieResponse.builder()
                .imdbRating(10.5F)
                .imdbVotes(12345)
                .title("sadasdasd")
                .build();

        var movies2 = MovieResponse.builder()
                .imdbRating(10.5F)
                .imdbVotes(12345)
                .title("sadassdasd")
                .build();

        assertNotEquals(movies1, movies2);
    }

    @Test
    void testBuilder() {
        var movie = MovieResponse.builder()
                .imdbRating(10.5F)
                .imdbVotes(12345)
                .title("sadasdasd")
                .build();

        assertEquals(10.5F, movie.getImdbRating());
        assertEquals(12345, movie.getImdbVotes());
        assertEquals("sadasdasd", movie.getTitle());
    }

    @Test
    void testScore() {
        var movie = MovieResponse.builder()
                .imdbRating(10.5F)
                .imdbVotes(2)
                .title("sadasdasd")
                .build();

        assertEquals(21, movie.getScore());
    }
}