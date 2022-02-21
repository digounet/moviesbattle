package com.letscode.pablo.moviesbattle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    private Integer hits;

    private Integer mistakes;

    @OneToMany(mappedBy = "game", cascade=CascadeType.ALL)
    private List<GameMovie> movieList;
}
