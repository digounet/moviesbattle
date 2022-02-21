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

import java.util.*;

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

    public List<Set<String>> getRandomMoviesPairs() {
        var response = new ArrayList<Set<String>>();

        Collections.shuffle(moviesIds);

        var interation = 1;
        for (int i = 0; i < moviesIds.size(); i++) {
            for (int j = interation; j < moviesIds.size(); j++) {
                var moviePair = new HashSet<String>();
                moviePair.add(moviesIds.get(i));
                moviePair.add(moviesIds.get(j));

                response.add(moviePair);
            }

            interation++;
        }

        return response;
    }
}
