package com.letscode.pablo.moviesbattle.entrypoint.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResponse {

    @JsonProperty("Title")
    private String title;
    private double imdbRating;
    private double imdbVotes;

    public double getScore() {
        return imdbRating * imdbVotes;
    }
}
