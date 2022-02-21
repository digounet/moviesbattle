package com.letscode.pablo.moviesbattle.entrypoint.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RankingResponse {
    private String username;
    private Integer totalGames;
    private Integer totalHits;
    private Integer totalMistakes;
}
