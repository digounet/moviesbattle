package com.letscode.pablo.moviesbattle.service;

import com.letscode.pablo.moviesbattle.dataprovider.MovieRepository;
import com.letscode.pablo.moviesbattle.entity.Movie;
import com.letscode.pablo.moviesbattle.entrypoint.entity.MovieResponse;
import com.letscode.pablo.moviesbattle.infrastructure.constants.HttpResourcesPaths;
import com.letscode.pablo.moviesbattle.infrastructure.exception.MovieNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MovieService {

    @Value("${movies.apikey}")
    private String apiKey;

    @Value("#{'${movies.imdpids}'.split(',')}")
    List<String> moviesIds;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    RestTemplate restTemplate;


    public Movie getMovieById(String id) throws MovieNotFoundException {
        var movie = movieRepository.findById(id);

        if (movie.isEmpty()) {
            var uri = String.format(HttpResourcesPaths.MOVIE_API_RESOURCE, id, apiKey);
            var movieResponse = restTemplate.getForObject(uri, MovieResponse.class);

            if (movieResponse != null) {
                return movieRepository.save(new Movie(id, movieResponse.getTitle(), movieResponse.getImdbRating(), movieResponse.getImdbVotes()));
            } else {
                throw new MovieNotFoundException("Movie not found");
            }
        }

        return movie.get();
    }

    public List<String> pickNRandomElements() {
        return pickNRandomElements(moviesIds, ThreadLocalRandom.current());
    }

    private <E> List<E> pickNRandomElements(List<E> list, Random r) {
        final int MOVIES_NUMBER = 2;

        int length = list.size();

        if (length < MOVIES_NUMBER) return null;

        for (int i = length - 1; i >= length - MOVIES_NUMBER; --i) {
            Collections.swap(list, i , r.nextInt(i + 1));
        }

        return list.subList(length - MOVIES_NUMBER, length);
    }
}
