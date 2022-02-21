package com.letscode.pablo.moviesbattle.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "GameMovie", uniqueConstraints={
        @UniqueConstraint(columnNames = {"gameId", "movieA", "movieB"})
})
public class GameMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="gameId")
    private Game game;

    private String movieA;

    private String movieB;

    private Integer userScore;
}
