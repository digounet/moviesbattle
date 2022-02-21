package com.letscode.pablo.moviesbattle.service;

import com.letscode.pablo.moviesbattle.dataprovider.MovieRepository;
import com.letscode.pablo.moviesbattle.entity.Movie;
import com.letscode.pablo.moviesbattle.entrypoint.entity.MovieResponse;
import com.letscode.pablo.moviesbattle.infrastructure.exception.MovieNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MovieServiceTest {
    @Mock
    MovieRepository movieRepository;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    MovieService movieService;


    @BeforeEach
    void setUp() {
        var movies =  new ArrayList<String>();
        movies.add("AAAAA");
        movies.add("BBBBB");
        movies.add("CCCCC");
        movies.add("DDDDD");
        ReflectionTestUtils.setField(movieService, "moviesIds", movies);
        ReflectionTestUtils.setField(movieService, "apiKey", "sdasds");
    }

    @Test
    void testGetMovieByIdFromDB() throws MovieNotFoundException {
        var movie = new Movie();
        when(movieRepository.findById(any())).thenReturn(Optional.of(movie));

        var movieResponse = movieService.getMovieById("dassda");

        assertEquals(movie, movieResponse);
    }

    @Test
    void testGetMovieByIdFromAPI() throws MovieNotFoundException {
        var movieResponse = MovieResponse.builder()
                .imdbRating(4.5f)
                .imdbVotes(1000)
                .title("sdasdas")
                .build();
        var movie = new Movie("dassda", movieResponse.getTitle(), movieResponse.getImdbRating(), movieResponse.getImdbVotes());

        when(movieRepository.findById(any())).thenReturn(Optional.empty());
        when(restTemplate.getForObject("http://www.omdbapi.com/?i=dassda&apikey=sdasds", MovieResponse.class)).thenReturn(movieResponse);
        when(movieRepository.save(any())).thenReturn(movie);

        var callResponse = movieService.getMovieById("dassda");

        assertEquals(movie, callResponse);
    }

    @Test
    void testGetMovieByIdFromAPINotFound() {
        when(movieRepository.findById(any())).thenReturn(Optional.empty());
        when(restTemplate.getForObject("http://www.omdbapi.com/?i=dassda&apikey=sdasds", MovieResponse.class)).thenReturn(null);

        MovieNotFoundException exception = assertThrows(MovieNotFoundException.class, () -> movieService.getMovieById("dassda"));

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Movie not found"));
    }

    @Test
    void testGetGameMovieList() {
        var movie = new Movie();
        when(movieRepository.findById(any())).thenReturn(Optional.of(movie));

        var movies = movieService.getRandomMoviesPairs();

        assertEquals(6, movies.size());
    }
}