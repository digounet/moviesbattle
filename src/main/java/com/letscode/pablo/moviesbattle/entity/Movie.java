package com.letscode.pablo.moviesbattle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Movie",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id")
        })
public class Movie implements Serializable {

        @Id
        private String id;

        private String title;

        private double imdbRating;

        private double imdbVotes;
}
