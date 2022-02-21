package com.letscode.pablo.moviesbattle.entrypoint.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterResponseTest {

    @Test
    void testEquals() {
        var user1 = UserRegisterResponse.builder()
                .id(1)
                .username("dfsdfsdfs")
                .build();

        var user2 = UserRegisterResponse.builder()
                .id(1)
                .username("dfsdfsdfs")
                .build();

        assertEquals(user1, user2);
    }

    @Test
    void testNotEquals() {
        var user1 = UserRegisterResponse.builder()
                .id(1)
                .username("dfsdfsdfs")
                .build();

        var user2 = UserRegisterResponse.builder()
                .id(3)
                .username("dfsdfsdfs")
                .build();

        assertNotEquals(user1, user2);
    }

    @Test
    void builder() {
        var user1 = UserRegisterResponse.builder()
                .id(1)
                .username("dfsdfsdfs")
                .build();

        assertEquals(1, user1.getId());
        assertEquals("dfsdfsdfs", user1.getUsername());
    }
}