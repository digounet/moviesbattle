package com.letscode.pablo.moviesbattle.entrypoint.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RankingResponseTest {

    @Test
    void testEquals() {
        var r1 = RankingResponse.builder()
                .totalGames(1)
                .totalHits(2)
                .totalMistakes(3)
                .username("dfdsfs")
                .build();

        var r2 = RankingResponse.builder()
                .totalGames(1)
                .totalHits(2)
                .totalMistakes(3)
                .username("dfdsfs")
                .build();

        assertEquals(r1, r2);
    }

    @Test
    void testNotEquals() {
        var r1 = RankingResponse.builder()
                .totalGames(1)
                .totalHits(2)
                .totalMistakes(3)
                .username("dfdsfs")
                .build();

        var r2 = RankingResponse.builder()
                .totalGames(1)
                .totalHits(2)
                .totalMistakes(3)
                .username("dfdsfrs")
                .build();

        assertNotEquals(r1, r2);
    }
}