package com.letscode.pablo.moviesbattle.entrypoint.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuizzResponseTest {

    @Test
    void testEquals() {
        var response1 = QuizzResponse.builder()
                .movieA("dfsdfs")
                .movieAId("dsfsdfs")
                .MovieB("fdgfdgd")
                .movieBId("gfgdfgd")
                .build();

        var response2 = QuizzResponse.builder()
                .movieA("dfsdfs")
                .movieAId("dsfsdfs")
                .MovieB("fdgfdgd")
                .movieBId("gfgdfgd")
                .build();

        assertEquals(response1, response2);
    }

    @Test
    void testNotEquals() {
        var response1 = QuizzResponse.builder()
                .movieA("dfsdfs")
                .movieAId("dsfsdfs")
                .MovieB("fdgfdgd")
                .movieBId("gfgdfgd")
                .build();

        var response2 = QuizzResponse.builder()
                .movieA("dfsdfs")
                .movieAId("dsfsdfs")
                .MovieB("fdgfdgdz")
                .movieBId("gfgdfgd")
                .build();

        assertNotEquals(response1, response2);
    }
}