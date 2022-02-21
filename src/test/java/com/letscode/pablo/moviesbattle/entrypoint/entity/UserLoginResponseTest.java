package com.letscode.pablo.moviesbattle.entrypoint.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginResponseTest {

    @Test
    void testEquals() {
        var user1 = UserLoginResponse.builder()
                .token("dfdfsd")
                .type("dfdfsd")
                .build();

        var user2 = UserLoginResponse.builder()
                .token("dfdfsd")
                .type("dfdfsd")
                .build();

        assertEquals(user1, user2);
    }

    @Test
    void testNotEquals() {
        var user1 = UserLoginResponse.builder()
                .token("dfdfsd")
                .type("dfdfsd")
                .build();

        var user2 = UserLoginResponse.builder()
                .token("dfdfeesd")
                .type("dfdfsd")
                .build();

        assertNotEquals(user1, user2);
    }

    @Test
    void builder() {
        var user1 = UserLoginResponse.builder()
                .token("dfdfsd")
                .type("dfdfsd")
                .build();

        assertEquals("dfdfsd", user1.getToken());
        assertEquals("dfdfsd", user1.getToken());
    }
}