package com.letscode.pablo.moviesbattle.entrypoint.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankingResponse {
    private String username;
    private Integer totalGames;
    private Integer totalHits;
    private Integer totalMistakes;
}
