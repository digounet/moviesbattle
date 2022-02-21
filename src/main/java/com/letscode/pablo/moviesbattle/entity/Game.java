package com.letscode.pablo.moviesbattle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Date startDate;

    private Date endDate;

    private Integer score;

    @OneToMany(mappedBy = "game")
    private Set<GameMovie> movieList;
}
