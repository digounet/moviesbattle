package com.letscode.pablo.moviesbattle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "GameMovie", uniqueConstraints={
        @UniqueConstraint(columnNames = {"movieA", "movieB"})
})
public class GameMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="gameId", nullable=false)
    private Game game;

    private String movieA;

    private String movieB;

    private Integer userScore;
}
