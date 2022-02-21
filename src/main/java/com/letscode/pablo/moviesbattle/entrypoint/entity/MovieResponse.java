package com.letscode.pablo.moviesbattle.entrypoint.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.letscode.pablo.moviesbattle.infrastructure.deserializer.CommaIntegerDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResponse {

    @JsonProperty("Title")
    private String title;

    private float imdbRating;

    @JsonDeserialize(using = CommaIntegerDeserializer.class)
    private int imdbVotes;

    public double getScore() {
        return imdbRating * imdbVotes;
    }
}
