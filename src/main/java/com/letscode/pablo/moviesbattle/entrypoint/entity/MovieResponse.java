package com.letscode.pablo.moviesbattle.entrypoint.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.letscode.pablo.moviesbattle.infrastructure.deserializer.CommaFloatDeserializer;
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

    @JsonDeserialize(using = CommaFloatDeserializer.class)
    private float imdbRating;

    @JsonDeserialize(using = CommaFloatDeserializer.class)
    private float imdbVotes;

    public double getScore() {
        return imdbRating * imdbVotes;
    }
}
