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

        private int imdbVotes;

        public boolean isSelectedTheWinner(String movieId, Movie movieToCompare) {
                var currentScore = imdbRating * imdbVotes;
                var targetScore = movieToCompare.getImdbRating() * movieToCompare.getImdbVotes();

                if ((currentScore >= targetScore) && (this.id.equals(movieId))) {
                        return true;
                } else return movieToCompare.id.equals(movieId);
        }
}
