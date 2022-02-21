package com.letscode.pablo.moviesbattle.service;

import com.letscode.pablo.moviesbattle.dataprovider.MovieRepository;
import com.letscode.pablo.moviesbattle.entity.Movie;
import com.letscode.pablo.moviesbattle.entrypoint.entity.MovieResponse;
import com.letscode.pablo.moviesbattle.infrastructure.constants.HttpResourcesPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService {

    @Value("${movies.apikey}")
    private String apiKey;

    @Autowired
    MovieRepository movieRepository;

    public Movie getMovieById(String id) {
        var movie = movieRepository.findById(id);

        if (movie.isEmpty()) {
            var uri = String.format(HttpResourcesPaths.MOVIE_API_RESOURCE, id, apiKey);
            RestTemplate restTemplate = new RestTemplate();
            var movieResponse = restTemplate.getForObject(uri, MovieResponse.class);

            if (movieResponse != null) {
                return movieRepository.save(new Movie(id, movieResponse.getTitle(), movieResponse.getImdbRating(), movieResponse.getImdbVotes()));
            } else {
                return null;
            }
        }

        return movie.get();
    }
}
